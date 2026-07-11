package com.bretzelfresser.justpets.registries;

import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.entity.JerboaVariantPredicate;
import com.google.common.collect.HashBiMap;
import net.minecraft.Util;
import net.minecraft.advancements.critereon.EntitySubPredicate;

import static net.minecraft.advancements.critereon.EntitySubPredicate.Types.*;

public class ModEntityPredicateTypes {


    public static final EntitySubPredicate.Type JERBOA_VARIANT_PREDICATE_TYPE = JerboaVariantPredicate::fromJson;


    public static void registerTypes() {
        makeVanilla();
        EntitySubPredicate.Types.TYPES.put(JustPets.modLoc("jerboa_variant").toString(), JERBOA_VARIANT_PREDICATE_TYPE);
    }

    private static void makeVanilla() {
        TYPES = Util.make(HashBiMap.create(), map -> map.putAll(TYPES));
    }
}
