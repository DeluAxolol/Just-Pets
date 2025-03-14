package com.bretzelfresser.registries;

import com.bretzelfresser.JustJerboa;
import com.bretzelfresser.entity.Jerboa;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {


    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, JustJerboa.MODID);


    public static final DeferredHolder<EntityType<?>, EntityType<Jerboa>> JERBOA = ENTITIES.register("jerboa", () -> EntityType.Builder.of(Jerboa::new, MobCategory.AMBIENT).sized(0.4f, 0.5f).build(JustJerboa.modLoc("jerboa").toString()));
}
