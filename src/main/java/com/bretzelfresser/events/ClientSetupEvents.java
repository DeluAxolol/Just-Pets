package com.bretzelfresser.events;

import com.bretzelfresser.JustJerboa;
import com.bretzelfresser.registries.ModEntities;
import com.bretzelfresser.rendering.model.BabyJerboaModel;
import com.bretzelfresser.rendering.model.JerboaModel;
import com.bretzelfresser.rendering.model.ModModelLayerLocations;
import com.bretzelfresser.rendering.rendering.JerboaOnShoulderLayer;
import com.bretzelfresser.rendering.rendering.JerboaRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

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
        for (PlayerSkin.Model modelSkin : event.getSkins()){
            PlayerRenderer renderer = event.getSkin(modelSkin);
            renderer.addLayer(new JerboaOnShoulderLayer<>(renderer, event.getEntityModels()));
        }
    }
}
