package com.bretzelfresser.justjerboa.datagen;

import com.bretzelfresser.justjerboa.JustJerboa;
import com.bretzelfresser.justjerboa.registries.ModEntities;
import com.bretzelfresser.justjerboa.registries.ModItems;
import net.minecraft.data.PackOutput;

public class ModEnglishLanguageProvider extends BetterLanguageProvider{
    public ModEnglishLanguageProvider(PackOutput output) {
        super(output, JustJerboa.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        simpleItem(ModItems.DRIED_GRASS_SEEDS.get());
        simpleEntity(ModEntities.JERBOA.get());
    }
}
