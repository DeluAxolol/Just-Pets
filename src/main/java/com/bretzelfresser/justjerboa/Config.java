package com.bretzelfresser.justjerboa;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = JustJerboa.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue JERBOA_TAMING_CHANCE = BUILDER.comment("this defines the chance whith which the jerboa can be tames, the final chance is 1/value that the jerboa will be tames").defineInRange("jerboa_tame_chance", 3, 1, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int jerboaTameChance;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        jerboaTameChance = JERBOA_TAMING_CHANCE.get();
    }
}
