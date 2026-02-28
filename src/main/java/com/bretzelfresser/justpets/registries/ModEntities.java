package com.bretzelfresser.justpets.registries;

import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.entity.Jerboa;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {


    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, JustPets.MODID);


    public static final RegistryObject<EntityType<Jerboa>> JERBOA = ENTITIES.register("jerboa", () -> EntityType.Builder.of(Jerboa::new, MobCategory.AMBIENT).sized(0.4f, 0.5f).build(JustPets.modLoc("jerboa").toString()));
}
