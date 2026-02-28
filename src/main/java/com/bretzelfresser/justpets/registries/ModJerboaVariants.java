package com.bretzelfresser.justpets.registries;

import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.jerboavariants.JerboaVariant;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class ModJerboaVariants {

    public static final ResourceKey<Registry<JerboaVariant>> JERBOA_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.parse("jerboa_variant"));


    public static final ResourceKey<JerboaVariant> BROWN = key("brown_variant");
    public static final ResourceKey<JerboaVariant> GREY_SANDY = key("grey_sandy_variant");
    public static final ResourceKey<JerboaVariant> GREY = key("grey_variant");
    public static final ResourceKey<JerboaVariant> PALE_BROWN = key("pale_brown_variant");
    public static final ResourceKey<JerboaVariant> PINK = key("pink_variant");

    public static ResourceKey<JerboaVariant> key(String name){
        return ResourceKey.create(JERBOA_VARIANT_REGISTRY_KEY, JustPets.modLoc(name));
    }
}
