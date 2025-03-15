package com.bretzelfresser.registries;

import com.bretzelfresser.JustJerboa;
import com.bretzelfresser.jerboavariants.DefaultJerboaVariant;
import com.bretzelfresser.jerboavariants.JerboaVariant;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class ModJerboaVariants {

    public static final ResourceKey<Registry<JerboaVariant>> JERBOA_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(new ResourceLocation("jerboa_variant"));


    public static final DeferredRegister<Codec<? extends JerboaVariant>> JERBOA_VARIANT_SERIALIZERS = DeferredRegister.create(JustJerboa.modLoc("jerboa_variant_serializer"), JustJerboa.MODID);
    public static final DeferredRegister<JerboaVariant> JERBOA_VARIANTS = DeferredRegister.create(JERBOA_VARIANT_REGISTRY_KEY, JustJerboa.MODID);


    public static final Registry<Codec<? extends JerboaVariant>> JERBOA_VARIANT_SERIALIZER_REGISTRY = JERBOA_VARIANT_SERIALIZERS.makeRegistry(ModJerboaVariants::makeRegistry);

    public static final DeferredHolder<Codec<? extends JerboaVariant>, Codec<DefaultJerboaVariant>> DEFAULT_JERBOA_VARIANT_CODEC = JERBOA_VARIANT_SERIALIZERS.register("default_jerboa_variant_serializer", () -> DefaultJerboaVariant.CODEC);

    private static void makeRegistry(RegistryBuilder<Codec<? extends JerboaVariant>> registryBuilder) {
        registryBuilder.sync(true);
    }
}
