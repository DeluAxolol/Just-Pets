package com.bretzelfresser.justpets.datagen;

import com.bretzelfresser.justpets.JustPets;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustPets.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput output = event.getGenerator().getPackOutput();
        var lookup = event.getLookupProvider();

        gen.addProvider(event.includeServer(), new ModRecipeProvider(output));
        ModBlockTags blockTags = gen.addProvider(event.includeServer(), new ModBlockTags(output, lookup, helper));
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(output, lookup, blockTags.contentsGetter()));
        gen.addProvider(event.includeServer(), new ModJerboaVariantProvider(output, helper));
        gen.addProvider(event.includeServer(), new ModAdvancementProvider(output, lookup, helper));


        gen.addProvider(event.includeClient(), new ModEnglishLanguageProvider(output));
        gen.addProvider(event.includeClient(), new ModItemModels(output, helper));


    }
}
