package com.bretzelfresser.datagen;

import com.bretzelfresser.JustJerboa;
import com.bretzelfresser.jerboavariants.DefaultJerboaVariant;
import com.bretzelfresser.jerboavariants.JerboaVariant;
import com.bretzelfresser.jerboavariants.JerboaVariantChecker;
import com.bretzelfresser.registries.ModJerboaVariants;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.server.packs.PackType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.JsonCodecProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModJerboaVariantProvider extends JsonCodecProvider<JerboaVariant> {
    public ModJerboaVariantProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, PackOutput.Target.DATA_PACK, ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY.location().getPath(), PackType.SERVER_DATA, JerboaVariant.DIRECT_CODEC, lookupProvider, JustJerboa.MODID, existingFileHelper);
    }

    @Override
    protected void gather() {
        unconditional(JustJerboa.modLoc("dark_brown_variant"), new DefaultJerboaVariant(10, true, JustJerboa.modLoc("textures/entity/jerboa2.png"), List.of()));
        unconditional(JustJerboa.modLoc("black_variant"), new DefaultJerboaVariant(7, true, JustJerboa.modLoc("textures/entity/jerboa3.png"), List.of()));
        unconditional(JustJerboa.modLoc("brown_variant"), new DefaultJerboaVariant(4, true, JustJerboa.modLoc("textures/entity/jerboa4.png"), List.of()));
        unconditional(JustJerboa.modLoc("strange_variant"), new DefaultJerboaVariant(1, true, JustJerboa.modLoc("textures/entity/jerboa5.png"), List.of(Pair.of(new JerboaVariantChecker(JustJerboa.modLoc("pink_variant")), new JerboaVariantChecker(JustJerboa.modLoc("brown_variant"))))));
        unconditional(JustJerboa.modLoc("pink_variant"), new DefaultJerboaVariant(1, false, JustJerboa.modLoc("textures/entity/jerboa1.png"), List.of(Pair.of(new JerboaVariantChecker(JustJerboa.modLoc("brown_variant")), new JerboaVariantChecker(JustJerboa.modLoc("black_variant"))))));
    }
}
