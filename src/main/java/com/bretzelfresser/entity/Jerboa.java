package com.bretzelfresser.entity;

import com.bretzelfresser.Config;
import com.bretzelfresser.jerboavariants.JerboaVariant;
import com.bretzelfresser.registries.ModEntities;
import com.bretzelfresser.registries.ModJerboaVariants;
import com.bretzelfresser.registries.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.ShoulderRidingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public class Jerboa extends ShoulderRidingEntity implements VariantHolder<JerboaVariant> {

    public static final EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(Jerboa.class, EntityDataSerializers.STRING);

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6f)
                .add(Attributes.MOVEMENT_SPEED, 0.4f);
    }

    public Jerboa(EntityType<? extends ShoulderRidingEntity> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LandOnOwnersShoulderGoal(this));

        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.7));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, level().registryAccess().registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).getKey(getInitialVariant()).toString());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("variant", this.entityData.get(VARIANT));
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        if (compound.contains("variant")) {
            this.entityData.set(VARIANT, compound.getString("variant"));
        }
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
        return Objects.requireNonNull(this.level().registryAccess().registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).get(new ResourceLocation(this.entityData.get(VARIANT))));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!this.isTame()) {
            if (!isPanicking() && this.isFood(itemstack)) {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                if ((Config.jerboaTameChance <= 1 || this.random.nextInt(Config.jerboaTameChance) == 0) && !EventHooks.onAnimalTame(this, player)) {
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
        if (this.isTame() && this.isOwnedBy(player)&& !isFood(itemstack) && canSitOnShoulder()) {
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
            JerboaVariant childVariant = WeightedRandom.getRandomItem(serverLevel.getRandom(), serverLevel.registryAccess().registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).stream().filter(s -> s.isValidCombination(serverLevel.registryAccess(), this.getVariant(), otherParent.getVariant())).toList()).orElseThrow();
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
