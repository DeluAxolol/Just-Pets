package com.bretzelfresser.events;

import com.bretzelfresser.JustJerboa;
import com.bretzelfresser.rendering.model.JerboaModel;
import com.bretzelfresser.rendering.model.ModModelLayerLocations;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod.EventBusSubscriber(modid = JustJerboa.MODID)
public class ClientSetupEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayerLocations.LAYER_LOCATION, JerboaModel::createBodyLayer);
    }
}
