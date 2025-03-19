package com.bretzelfresser.justjerboa.datagen;

import com.bretzelfresser.justjerboa.JustJerboa;
import com.bretzelfresser.justjerboa.jerboavariants.DefaultJerboaVariant;
import com.bretzelfresser.justjerboa.jerboavariants.JerboaVariant;
import com.bretzelfresser.justjerboa.jerboavariants.JerboaVariantChecker;
import com.bretzelfresser.justjerboa.registries.ModJerboaVariants;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ModJerboaVariantProvider extends JsonCodecProvider<JerboaVariant> {
    public ModJerboaVariantProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper, JustJerboa.MODID, JsonOps.INSTANCE, PackType.SERVER_DATA, ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY.location().getPath(), JerboaVariant.DIRECT_CODEC, Map.of());
    }

    @Override
    protected void gather(BiConsumer<ResourceLocation, JerboaVariant> consumer) {
        add(consumer, ModJerboaVariants.BROWN, new DefaultJerboaVariant(10, true, JustJerboa.entityPngTexture("brown"), JustJerboa.entityPngTexture("baby_brown"), List.of()));
        add(consumer, ModJerboaVariants.GREY, new DefaultJerboaVariant(7, true, JustJerboa.entityPngTexture("grey"), JustJerboa.entityPngTexture("baby_grey"), List.of()));
        add(consumer, ModJerboaVariants.GREY_SANDY, new DefaultJerboaVariant(4, true, JustJerboa.entityPngTexture("grey_sandy"), JustJerboa.entityPngTexture("baby_grey_sandy"), List.of()));
        add(consumer, ModJerboaVariants.PALE_BROWN, new DefaultJerboaVariant(1, true, JustJerboa.entityPngTexture("pale_brown"), JustJerboa.entityPngTexture("baby_pale_brown"), List.of(Pair.of(new JerboaVariantChecker(ModJerboaVariants.PINK), new JerboaVariantChecker(ModJerboaVariants.GREY_SANDY)))));
        add(consumer, ModJerboaVariants.PINK, new DefaultJerboaVariant(1, false, JustJerboa.entityPngTexture("pink"), JustJerboa.entityPngTexture("baby_pink"), List.of(Pair.of(new JerboaVariantChecker(ModJerboaVariants.PALE_BROWN), new JerboaVariantChecker(ModJerboaVariants.GREY_SANDY)))));
    }

    public void add(BiConsumer<ResourceLocation, JerboaVariant> consumer, ResourceKey<JerboaVariant> key, JerboaVariant value) {
        consumer.accept(key.location(), value);
    }
}
