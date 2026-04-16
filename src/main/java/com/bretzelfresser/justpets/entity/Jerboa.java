package com.bretzelfresser.justpets.entity;

import java.util.Objects;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.bretzelfresser.justpets.Config;
import com.bretzelfresser.justpets.jerboavariants.JerboaVariant;
import com.bretzelfresser.justpets.registries.ModEntities;
import com.bretzelfresser.justpets.registries.ModJerboaVariants;
import com.bretzelfresser.justpets.registries.ModSounds;
import com.bretzelfresser.justpets.registries.ModTags;
import com.bretzelfresser.justpets.rendering.animation.SmoothAnimationState;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.ShoulderRidingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

public class Jerboa extends ShoulderRidingEntity implements VariantHolder<JerboaVariant> {

    public static final EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(Jerboa.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(Jerboa.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> ANIMATION_TICK = SynchedEntityData.defineId(Jerboa.class, EntityDataSerializers.INT);

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6f)
                .add(Attributes.MOVEMENT_SPEED, 0.4f);
    }
    
    public final SmoothAnimationState idleAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState runAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState sitAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState rollingAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState funnyAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState flipAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState backflipAnimationState = new SmoothAnimationState();

    public final JerboaAlarm alarm;
    
    public Jerboa(EntityType<? extends ShoulderRidingEntity> entityType, Level level) {
        super(entityType, level);
        this.alarm = new JerboaAlarm(this, 400, 4);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D) {
        	@Override
        	public boolean canUse() {
        		return super.canUse() && !Jerboa.this.isInSittingPose();
        	}
        	
        	@Override
        	public void start() {
        		super.start();
        		this.mob.setSprinting(true);
        	}
        	
        	@Override
        	public void stop() {
        		super.stop();
        		this.mob.setSprinting(false);
        	}
        });
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.2D) {
        	@Override
        	public boolean canUse() {
        		return super.canUse() && !Jerboa.this.isInSittingPose();
        	}
        });
        this.goalSelector.addGoal(3, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 0.8D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.7));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, "brown_variant");
        this.entityData.define(ANIMATION_STATE, 0);
        this.entityData.define(ANIMATION_TICK, 0);
    }
    
    @Override
    public void tick() {
    	super.tick();
    	if(this.level().isClientSide) {
    		this.idleAnimationState.updateWhen(this.getAnimationState() == 0 && !this.isInSittingPose(), this.tickCount);
    		this.runAnimationState.updateWhen(this.isSprinting(), this.tickCount);
    		this.sitAnimationState.updateWhen(this.isInSittingPose(), this.tickCount);
    		this.rollingAnimationState.updateWhen(this.getAnimationState() == 1 && !this.isSprinting() && !this.isInSittingPose(), this.tickCount);
    		this.funnyAnimationState.updateWhen(this.getAnimationState() == 2 && !this.isSprinting() && !this.isInSittingPose(), this.tickCount);
    		this.flipAnimationState.updateWhen(this.getAnimationState() == 3 && !this.isSprinting() && !this.isInSittingPose(), this.tickCount);
    		this.backflipAnimationState.updateWhen(this.getAnimationState() == 4 && !this.isSprinting() && !this.isInSittingPose(), this.tickCount);
    	}
    	else {
        	if(this.getAnimationTick() > 0) {
        		this.setAnimationTick(this.getAnimationTick() - 1);
        		this.getNavigation().stop();
        	}
        	else if(this.getAnimationState() != 0) {
        		this.setAnimationState(0);
        	}
        	
        	if(this.getNavigation().isDone() && !this.isInSittingPose() && !this.isBaby()) {
            	if(this.random.nextInt(300) == 0) {
            		if(this.getAnimationState() == 0) {
            			if(this.random.nextBoolean()) {
            				this.setAnimationState(1);
            				this.setAnimationTick(40);
            			}
            			else if(this.random.nextBoolean()) {
            				this.setAnimationState(2);
            				this.setAnimationTick(25);
            			}
            			else if(this.random.nextBoolean()) {
            				this.setAnimationState(3);
            				this.setAnimationTick(27);
            				this.getJumpControl().jump();
            			}
            			else {
            				this.setAnimationState(4);
            				this.setAnimationTick(26);
            				this.getJumpControl().jump();
            			}
            		}
            	}
        	}
    	}
    }
    
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_, MobSpawnType p_146748_, SpawnGroupData p_146749_, CompoundTag p_146750_) {
    	this.entityData.set(VARIANT, level().registryAccess().registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).getKey(getInitialVariant()).toString());
    	return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }
    
    @Override
    protected SoundEvent getAmbientSound() {
    	return ModSounds.JERBOA_IDLE.get();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("variant", this.entityData.get(VARIANT));
        compound.putInt("AnimationState", this.getAnimationState());
        compound.putInt("AnimationTick", this.getAnimationTick());
    	this.alarm.write(compound);
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
    	super.readAdditionalSaveData(compound);
        this.entityData.set(VARIANT, compound.getString("variant"));
    	this.setAnimationState(compound.getInt("AnimationState"));
    	this.setAnimationTick(compound.getInt("AnimationTick"));
    	this.alarm.read(compound);
    }

    @Override
    public void setVariant(JerboaVariant variant) {
        this.entityData.set(VARIANT, level().registryAccess().registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).getKey(variant).toString());
    }

    protected JerboaVariant getInitialVariant() {
        return WeightedRandom.getRandomItem(this.level().getRandom(), this.level().registryAccess().registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).stream().filter(JerboaVariant::isCanSpawnNaturally).toList()).orElseThrow();
    }

    @Override
    public @NotNull JerboaVariant getVariant() {
        return Objects.requireNonNull(this.level().registryAccess().registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).get(ResourceLocation.parse(this.entityData.get(VARIANT))));
    }
    
    public void setAnimationTick(int value) {
        this.entityData.set(ANIMATION_TICK, value);
    }
    
    public int getAnimationTick() {
        return this.entityData.get(ANIMATION_TICK);
    }
    
    public void setAnimationState(int value) {
        this.entityData.set(ANIMATION_STATE, value);
    }
    
    public int getAnimationState() {
        return this.entityData.get(ANIMATION_STATE);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!this.isTame()) {
            if (this.isFood(itemstack)) {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                if ((Config.jerboaTameChance <= 1 || this.random.nextInt(Config.jerboaTameChance) == 0) && !ForgeEventFactory.onAnimalTame(this, player)) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.setOrderedToSit(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
                return InteractionResult.SUCCESS;
            }
        }
        else if (this.isOwnedBy(player) && !isFood(itemstack) && canSitOnShoulder()) {
            if (!this.level().isClientSide) {
                this.setOrderedToSit(!this.isOrderedToSit());
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexture(){
        return isBaby() ? getVariant().getBabyTexture() : getVariant().getTexture();
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ModTags.Items.JERBOA_FOOD);
    }

    @Override
    public boolean canMate(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (!(otherAnimal instanceof Jerboa)) {
            return false;
        } else {
            Jerboa jerboa = (Jerboa) otherAnimal;
            if (!jerboa.isTame()) {
                return false;
            } else if (jerboa.isInSittingPose()) {
                return false;
            } else {
                return this.isInLove() && jerboa.isInLove();
            }
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
    	if(this.isBaby()) {
    		return null;
    	}
        if (ageableMob instanceof Jerboa otherParent) {
            Jerboa entity = ModEntities.JERBOA.get().create(serverLevel);
            if (this == ageableMob) {
                assert entity != null;
                UUID uuid = this.getOwnerUUID();
                if (uuid != null) {
                    entity.setOwnerUUID(uuid);
                    entity.setTame(true);
                }
                entity.setVariant(this.getVariant());
                return entity;
            }
            JerboaVariant childVariant = WeightedRandom.getRandomItem(serverLevel.getRandom(), serverLevel.registryAccess().registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).stream().filter(s -> s.isValidCombination(serverLevel.registryAccess(), this.getVariant(), otherParent.getVariant())).toList()).orElse(this.random.nextBoolean() ? this.getVariant() : otherParent.getVariant());
            assert entity != null;
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                entity.setOwnerUUID(uuid);
                entity.setTame(true);
            }
            entity.setVariant(childVariant);
            return entity;
        }
        return null;
    }
}
