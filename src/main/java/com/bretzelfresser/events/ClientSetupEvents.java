package com.bretzelfresser.events;

import com.bretzelfresser.JustJerboa;
import com.bretzelfresser.registries.ModEntities;
import com.bretzelfresser.rendering.model.JerboaModel;
import com.bretzelfresser.rendering.model.ModModelLayerLocations;
import com.bretzelfresser.rendering.rendering.JerboaRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod.EventBusSubscriber(modid = JustJerboa.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetupEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayerLocations.LAYER_LOCATION, JerboaModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntities.JERBOA.get(), JerboaRenderer::new);
    }
}
