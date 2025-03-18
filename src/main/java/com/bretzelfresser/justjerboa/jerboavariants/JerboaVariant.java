package com.bretzelfresser.justjerboa.jerboavariants;

import com.bretzelfresser.justjerboa.registries.ModJerboaVariants;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public interface JerboaVariant extends WeightedEntry {


    Codec<JerboaVariant> DIRECT_CODEC = ExtraCodecs.lazyInitializedCodec(() -> ModJerboaVariants.JERBOA_VARIANT_SERIALIZER_REGISTRY.get().getCodec()).dispatch(JerboaVariant::codec, Function.identity());


    Codec<Holder<JerboaVariant>> REFRENCE_CODEC = RegistryFileCodec.create(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY, DIRECT_CODEC);

    Codec<HolderSet<JerboaVariant>> REFRENCE_LIST_CODEC = RegistryCodecs.homogeneousList(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY, DIRECT_CODEC);

    /**
     * @return the weight which must be greater then 0, for weighted random
     */
    int getDefaultWeight();

    @Override
    default @NotNull Weight getWeight(){
        return Weight.of(getDefaultWeight());
    }

    /**
     * @return whether this variant can spawn naturally
     */
    boolean isCanSpawnNaturally();

    /**
     * @return the Resourceloaction of the texture, dont forget the .png at the end
     */
    ResourceLocation getTexture();

    /**
     *
     * @return the texture fitting for the baby texture
     */
    ResourceLocation getBabyTexture();

    /**
     * @param access the registry access, ca be obtained from the level
     * @param parent1 the first parent
     * @param parent2 the secont parent, parents are interchangebale, so it doesnt matter in which order u put parent1 and parent2
     * @return whether this variant can be produced by this parent variants
     */
    boolean isValidCombination(RegistryAccess access, JerboaVariant parent1, JerboaVariant parent2);


    /**
     * @return the codec with which the variant class will be serialized
     */
    Codec<? extends JerboaVariant> codec();
}
