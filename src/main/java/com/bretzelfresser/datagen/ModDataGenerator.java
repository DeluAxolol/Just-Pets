package com.bretzelfresser.datagen;

import com.bretzelfresser.JustJerboa;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = JustJerboa.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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


        gen.addProvider(event.includeClient(), new ModEnglishLanguageProvider(output));


    }
}
