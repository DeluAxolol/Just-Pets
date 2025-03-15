package com.bretzelfresser.registries;

import com.bretzelfresser.JustJerboa;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {


    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(JustJerboa.MODID);



    public static final DeferredItem<Item> DRIED_GRASS_SEEDS = ITEMS.register("dried_grass_seeds", () -> new Item(new Item.Properties().stacksTo(32)));
    public static final DeferredItem<DeferredSpawnEggItem> JERBOA_SPAWN_EGG = ITEMS.register("jerboa_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.JERBOA, 0xFFFFFF, 0, new Item.Properties()));
}
