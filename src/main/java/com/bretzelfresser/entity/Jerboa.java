package com.bretzelfresser.entity;

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
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Jerboa extends TamableAnimal implements VariantHolder<JerboaVariant> {

    public static final EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(Jerboa.class, EntityDataSerializers.STRING);

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6f);
    }

    public Jerboa(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
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
    public boolean isFood(ItemStack stack) {
        return stack.is(ModTags.Items.JERBOA_FOOD);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        if (ageableMob instanceof Jerboa otherParent) {
            Jerboa entity = ModEntities.JERBOA.get().create(serverLevel);
            if (this == ageableMob){
                assert entity != null;
                entity.setVariant(this.getVariant());
                return entity;
            }
            JerboaVariant childVariant = WeightedRandom.getRandomItem(serverLevel.getRandom(), serverLevel.registryAccess().registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).stream().filter(s -> s.isValidCombination(serverLevel.registryAccess(), this.getVariant(), otherParent.getVariant())).toList()).orElseThrow();
            assert entity != null;
            entity.setVariant(childVariant);
            return entity;
        }
        return null;
    }
}
