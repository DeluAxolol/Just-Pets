package com.bretzelfresser.datagen;

import com.bretzelfresser.JustJerboa;
import com.bretzelfresser.registries.ModEntities;
import com.bretzelfresser.registries.ModItems;
import net.minecraft.data.PackOutput;

public class ModEnglishLanguageProvider extends BetterLanguageProvider{
    public ModEnglishLanguageProvider(PackOutput output) {
        super(output, JustJerboa.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        simpleItem(ModItems.DRIED_GRASS_SEEDS.asItem());
        simpleEntity(ModEntities.JERBOA.get());
    }
}
