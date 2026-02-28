package com.bretzelfresser.justpets.datagen;

import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.registries.ModEntities;
import com.bretzelfresser.justpets.registries.ModItems;

import net.minecraft.data.PackOutput;

public class ModEnglishLanguageProvider extends BetterLanguageProvider{
    public ModEnglishLanguageProvider(PackOutput output) {
        super(output, JustPets.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        simpleItem(ModItems.DRIED_GRASS_SEEDS.get());
        simpleItem(ModItems.JERBOA_SPAWN_EGG.get());
        simpleEntity(ModEntities.JERBOA.get());

        add("advancements." + JustPets.MODID + ".pink_davancement.title", "Depths of Genetics");
        add("advancements." + JustPets.MODID + ".pink_davancement.description", "You bred enough to finally find a hidden combination, Darwin would be really proud of you");

        add("key.categories.justpets", "Just Jerboa");
        add("key.justpets.equip", "Equip Jerboa");
        add("key.justpets.unequip", "Unequip Jerboa");
    }
}
