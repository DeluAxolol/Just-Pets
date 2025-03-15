package com.bretzelfresser.entity;

import com.bretzelfresser.JustJerboa;
import com.bretzelfresser.registries.ModEntities;
import com.bretzelfresser.registries.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class Jerboa extends Animal {

    public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Jerboa.class, EntityDataSerializers.INT);

    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6f);
    }

    public Jerboa(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, getInitialVariant().ordinal());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("variant", getVariant().ordinal());
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        if (compound.contains("variant")) {
            this.entityData.set(VARIANT, compound.getInt("variant"));
        }
    }

    public void setVariant(Variant variant) {
        this.entityData.set(VARIANT, variant.ordinal());
    }

    protected Variant getInitialVariant() {
        return Variant.values()[this.level().random.nextInt(Variant.values().length)];
    }

    public Variant getVariant() {
        return Variant.values()[Mth.clamp(this.entityData.get(VARIANT), 0, Variant.values().length - 1)];
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ModTags.Items.JERBOA_FOOD);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.JERBOA.get().create(serverLevel);
    }


    public enum Variant {
        VARIANT1(JustJerboa.modLoc("textures/entity/jerboa1.png")),
        VARIANT2(JustJerboa.modLoc("textures/entity/jerboa2.png")),
        VARIANT3(JustJerboa.modLoc("textures/entity/jerboa3.png")),
        VARIANT4(JustJerboa.modLoc("textures/entity/jerboa4.png")),
        VARIANT5(JustJerboa.modLoc("textures/entity/jerboa5.png"));

        private ResourceLocation texture;

        Variant(ResourceLocation texture) {
            this.texture = texture;
        }

        public ResourceLocation getTextureLocation() {
            return texture;
        }
    }
}
