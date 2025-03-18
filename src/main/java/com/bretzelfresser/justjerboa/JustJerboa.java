package com.bretzelfresser.justjerboa;

import com.bretzelfresser.justjerboa.jerboavariants.JerboaVariant;
import com.bretzelfresser.justjerboa.registries.ModEntities;
import com.bretzelfresser.justjerboa.registries.ModItems;
import com.bretzelfresser.justjerboa.registries.ModJerboaVariants;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DataPackRegistryEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JustJerboa.MODID)
public class JustJerboa {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "justjerboa";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation modLoc(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name);
    }

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public JustJerboa(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        ModItems.ITEMS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModJerboaVariants.JERBOA_VARIANT_SERIALIZERS.register(modEventBus);
        ModJerboaVariants.JERBOA_VARIANTS.register(modEventBus);

        modEventBus.register(this);
        modEventBus.addListener(this::registerDataPackRegistries);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            event.accept(ModItems.DRIED_GRASS_SEEDS);
        }
        if (event.getTabKey().equals(CreativeModeTabs.SPAWN_EGGS)) {
            event.accept(ModItems.JERBOA_SPAWN_EGG);
        }
    }

    public void registerDataPackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY, JerboaVariant.DIRECT_CODEC, JerboaVariant.DIRECT_CODEC);
    }
}
