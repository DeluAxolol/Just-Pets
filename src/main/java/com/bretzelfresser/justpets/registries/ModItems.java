package com.bretzelfresser.justpets.registries;

import com.bretzelfresser.justpets.JustPets;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JustPets.MODID);



    public static final RegistryObject<Item> DRIED_GRASS_SEEDS = ITEMS.register("dried_grass_seeds", () -> new Item(new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> JERBOA_SPAWN_EGG = ITEMS.register("jerboa_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.JERBOA, 7226679, 7226679, new Item.Properties()));
}
