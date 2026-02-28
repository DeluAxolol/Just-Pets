package com.bretzelfresser.justpets.registries;


import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.jerboavariants.DefaultJerboaVariant;
import com.bretzelfresser.justpets.jerboavariants.JerboaVariant;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModJerboaVariantSerializers {

    public static final DeferredRegister<Codec<? extends JerboaVariant>> JERBOA_VARIANT_SERIALIZERS = DeferredRegister.create(JustPets.modLoc("jerboa_variant_serializer"), JustPets.MODID);


    public static final Supplier<IForgeRegistry<Codec<? extends JerboaVariant>>> JERBOA_VARIANT_SERIALIZER_REGISTRY = JERBOA_VARIANT_SERIALIZERS.makeRegistry(ModJerboaVariantSerializers::makeRegistry);

    public static final RegistryObject<Codec<DefaultJerboaVariant>> DEFAULT_JERBOA_VARIANT_CODEC = JERBOA_VARIANT_SERIALIZERS.register("default_jerboa_variant_serializer", () -> DefaultJerboaVariant.CODEC);

    private static RegistryBuilder<Codec<? extends JerboaVariant>> makeRegistry() {
        return new RegistryBuilder<Codec<? extends JerboaVariant>>().disableSaving();
    }
}
