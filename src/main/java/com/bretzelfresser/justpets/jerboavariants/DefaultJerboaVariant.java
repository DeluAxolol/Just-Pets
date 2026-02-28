package com.bretzelfresser.justpets.jerboavariants;

import com.bretzelfresser.justpets.JustPets;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class DefaultJerboaVariant implements JerboaVariant {

    public static final Codec<Pair<JerboaVariantChecker, JerboaVariantChecker>> PAIR_CODEC = RecordCodecBuilder.create(pairInstance -> pairInstance.group(
            JerboaVariantChecker.CODEC.fieldOf("first").forGetter(Pair::getFirst),
            JerboaVariantChecker.CODEC.fieldOf("second").forGetter(Pair::getSecond)).apply(pairInstance, Pair::of));

    public static final Codec<DefaultJerboaVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("weight").forGetter(DefaultJerboaVariant::getDefaultWeight),
            Codec.BOOL.fieldOf("canSpawnNaturally").forGetter(DefaultJerboaVariant::isCanSpawnNaturally),
            ResourceLocation.CODEC.fieldOf("texture").forGetter(DefaultJerboaVariant::getTexture),
            ResourceLocation.CODEC.fieldOf("baby_texture").orElse(JustPets.modLoc("textures/entity/baby_jerboa_default.png")).forGetter(DefaultJerboaVariant::getBabyTexture),
            Codec.list(PAIR_CODEC).fieldOf("parentCombinations").forGetter(jv -> jv.parentCombinations)
    ).apply(instance, DefaultJerboaVariant::new));

    protected final int weight;
    protected final boolean canSpawnNaturally;
    protected final ResourceLocation texture, babyTexture;
    protected final List<Pair<JerboaVariantChecker, JerboaVariantChecker>> parentCombinations;

    public DefaultJerboaVariant(int weight, boolean canSpawnNaturally, ResourceLocation texture, List<Pair<JerboaVariantChecker, JerboaVariantChecker>> parentCombinations) {
        this(weight, canSpawnNaturally, texture, JustPets.modLoc("textures/entity/baby_jerboa_default.png"), parentCombinations);
    }

    public DefaultJerboaVariant(int weight, boolean canSpawnNaturally, ResourceLocation texture, ResourceLocation babyTexture, List<Pair<JerboaVariantChecker, JerboaVariantChecker>> parentCombinations) {
        this.weight = weight;
        this.canSpawnNaturally = canSpawnNaturally;
        this.texture = texture;
        this.babyTexture = babyTexture;
        this.parentCombinations = parentCombinations;
    }

    public int getDefaultWeight() {
        return weight;
    }

    public boolean isCanSpawnNaturally() {
        return canSpawnNaturally;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public boolean isValidCombination(RegistryAccess access, JerboaVariant parent1, JerboaVariant parent2) {
        for (var pair : parentCombinations) {
            //make sure it doesnt matter in which order parent1 and parent 2 are
            if ((pair.getFirst().test(access, parent1) && pair.getSecond().test(access, parent2)) || (pair.getFirst().test(access, parent2) && pair.getSecond().test(access, parent1))) {
                return true;
            }
        }
        return false;
    }


    @Override
    public ResourceLocation getBabyTexture() {
        return babyTexture;
    }

    @Override
    public Codec<? extends JerboaVariant> codec() {
        return CODEC;
    }
}
