package com.bretzelfresser.justjerboa.jerboavariants;

import com.bretzelfresser.justjerboa.registries.ModJerboaVariants;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.Optional;

public class JerboaVariantChecker {

    public static final Codec<JerboaVariantChecker> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceKey.codec(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).optionalFieldOf("toCheck").forGetter(jvc -> Optional.ofNullable(jvc.toCheck)),
            TagKey.codec(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).optionalFieldOf("validVariants").forGetter(jvc -> Optional.ofNullable(jvc.validVariants))
    ).apply(instance, (toCheckOpt, validVariantsOpt) -> {
        if (toCheckOpt.isPresent()) {
            return new JerboaVariantChecker(toCheckOpt.get());
        } else if (validVariantsOpt.isPresent()) {
            return new JerboaVariantChecker(validVariantsOpt.get());
        }
        throw new IllegalStateException("Either toCheck or validVariants must be present");
    }));


    protected ResourceKey<JerboaVariant> toCheck;
    protected TagKey<JerboaVariant> validVariants;


    public JerboaVariantChecker(ResourceLocation variant) {
        this(ResourceKey.create(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY, variant));
    }

    public JerboaVariantChecker(ResourceKey<JerboaVariant> toCheck) {
        this.toCheck = toCheck;
    }

    public JerboaVariantChecker(TagKey<JerboaVariant> validVariants) {
        this.validVariants = validVariants;
    }

    public boolean test(RegistryAccess access, JerboaVariant jerboaVariant) {
        if (toCheck != null){
            return access.registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).getOrThrow(toCheck) == jerboaVariant;
        }
        return access.registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).getOrCreateTag(validVariants).contains(Holder.direct(jerboaVariant));
    }
}
