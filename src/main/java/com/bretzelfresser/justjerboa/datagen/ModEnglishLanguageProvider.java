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
        simpleItem(ModItems.JERBOA_SPAWN_EGG.get());
        simpleEntity(ModEntities.JERBOA.get());

        add("advancements." + JustJerboa.MODID + ".pink_davancement.title", "Depths of Genetics");
        add("advancements." + JustJerboa.MODID + ".pink_davancement.description", "You bred enough to finally find a hidden combination, Darwin would be really proud of you");
    }
}
