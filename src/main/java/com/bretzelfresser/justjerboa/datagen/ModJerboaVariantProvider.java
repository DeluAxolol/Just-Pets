package com.bretzelfresser.justjerboa.datagen;

import com.bretzelfresser.justjerboa.JustJerboa;
import com.bretzelfresser.justjerboa.jerboavariants.DefaultJerboaVariant;
import com.bretzelfresser.justjerboa.jerboavariants.JerboaVariant;
import com.bretzelfresser.justjerboa.jerboavariants.JerboaVariantChecker;
import com.bretzelfresser.justjerboa.registries.ModJerboaVariants;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModJerboaVariantProvider extends JsonCodecProvider<JerboaVariant> {
    public ModJerboaVariantProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper, JustJerboa.MODID, JsonOps.INSTANCE, PackType.SERVER_DATA, ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY.location().getPath(), JerboaVariant.DIRECT_CODEC, Map.of());
    }

    @Override
    protected void gather(BiConsumer<ResourceLocation, JerboaVariant> consumer) {
        consumer.accept(JustJerboa.modLoc("dark_brown_variant"), new DefaultJerboaVariant(10, true, JustJerboa.modLoc("textures/entity/jerboa2.png"), List.of()));
        consumer.accept(JustJerboa.modLoc("black_variant"), new DefaultJerboaVariant(7, true, JustJerboa.modLoc("textures/entity/jerboa3.png"), List.of()));
        consumer.accept(JustJerboa.modLoc("brown_variant"), new DefaultJerboaVariant(4, true, JustJerboa.modLoc("textures/entity/jerboa4.png"), List.of()));
        consumer.accept(JustJerboa.modLoc("strange_variant"), new DefaultJerboaVariant(1, true, JustJerboa.modLoc("textures/entity/jerboa5.png"), List.of(Pair.of(new JerboaVariantChecker(JustJerboa.modLoc("pink_variant")), new JerboaVariantChecker(JustJerboa.modLoc("brown_variant"))))));
        consumer.accept(JustJerboa.modLoc("pink_variant"), new DefaultJerboaVariant(1, false, JustJerboa.modLoc("textures/entity/jerboa1.png"), List.of(Pair.of(new JerboaVariantChecker(JustJerboa.modLoc("brown_variant")), new JerboaVariantChecker(JustJerboa.modLoc("black_variant"))))));
    }
}
