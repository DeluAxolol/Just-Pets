package com.bretzelfresser.justjerboa.events;

import com.bretzelfresser.justjerboa.JustJerboa;
import com.bretzelfresser.justjerboa.registries.ModEntities;
import com.bretzelfresser.justjerboa.rendering.model.BabyJerboaModel;
import com.bretzelfresser.justjerboa.rendering.model.JerboaModel;
import com.bretzelfresser.justjerboa.rendering.model.ModModelLayerLocations;
import com.bretzelfresser.justjerboa.rendering.rendering.JerboaOnShoulderLayer;
import com.bretzelfresser.justjerboa.rendering.rendering.JerboaRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustJerboa.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetupEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayerLocations.JERBOA_LAYER_LOCATION, JerboaModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayerLocations.BABY_JERBOA_LAYER_LOCATION, BabyJerboaModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntities.JERBOA.get(), JerboaRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.AddLayers event){
        for (var modelSkin : event.getSkins()){
            PlayerRenderer renderer = event.getSkin(modelSkin);
            renderer.addLayer(new JerboaOnShoulderLayer<>(renderer, event.getEntityModels()));
        }
    }
}
