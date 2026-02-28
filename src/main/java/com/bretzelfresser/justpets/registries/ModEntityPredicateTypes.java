package com.bretzelfresser.justpets.registries;

import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.entity.JerboaVariantPredicate;
import com.google.common.collect.HashBiMap;
import net.minecraft.Util;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static net.minecraft.advancements.critereon.EntitySubPredicate.Types.*;

public class ModEntityPredicateTypes {


    public static final EntitySubPredicate.Type JERBOA_VARIANT_PREDICATE_TYPE = JerboaVariantPredicate::fromJson;


    public static void registerTypes(FMLCommonSetupEvent event) {
        makeVanilla();
        EntitySubPredicate.Types.TYPES.put(JustPets.modLoc("jerboa_variant").toString(), JERBOA_VARIANT_PREDICATE_TYPE);
    }

    private static void makeVanilla() {
        TYPES = Util.make(HashBiMap.create(), map -> {
            map.put("any", ANY);
            map.put("lightning", LIGHTNING);
            map.put("fishing_hook", FISHING_HOOK);
            map.put("player", PLAYER);
            map.put("slime", SLIME);
            map.put("cat", CAT.type());
            map.put("frog", FROG.type());
            map.put("axolotl", AXOLOTL.type());
            map.put("boat", BOAT.type());
            map.put("fox", FOX.type());
            map.put("mooshroom", MOOSHROOM.type());
            map.put("painting", PAINTING.type());
            map.put("rabbit", RABBIT.type());
            map.put("horse", HORSE.type());
            map.put("llama", LLAMA.type());
            map.put("villager", VILLAGER.type());
            map.put("parrot", PARROT.type());
            map.put("tropical_fish", TROPICAL_FISH.type());
        });
    }
}
