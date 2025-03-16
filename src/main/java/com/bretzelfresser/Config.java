package com.bretzelfresser;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@Mod.EventBusSubscriber(modid = JustJerboa.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue JERBOA_TAMING_CHANCE = BUILDER.comment("this defines the chance whith which the jerboa can be tames, the final chance is 1/value that the jerboa will be tames").defineInRange("jerboa_tame_chance", 3, 1, Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static int jerboaTameChance;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        jerboaTameChance = JERBOA_TAMING_CHANCE.get();
    }
}
