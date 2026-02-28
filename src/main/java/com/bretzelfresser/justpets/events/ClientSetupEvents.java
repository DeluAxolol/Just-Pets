package com.bretzelfresser.justpets.events;

import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.registries.ModEntities;
import com.bretzelfresser.justpets.registries.ModKeyMappings;
import com.bretzelfresser.justpets.rendering.model.BabyJerboaModel;
import com.bretzelfresser.justpets.rendering.model.JerboaModel;
import com.bretzelfresser.justpets.rendering.model.ModModelLayerLocations;
import com.bretzelfresser.justpets.rendering.rendering.JerboaOnShoulderLayer;
import com.bretzelfresser.justpets.rendering.rendering.JerboaRenderer;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustPets.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
    	event.register(ModKeyMappings.EQUIP_JERBOA);
    	event.register(ModKeyMappings.UNEQUIP_JERBOA);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.AddLayers event){
        for (var modelSkin : event.getSkins()){
            PlayerRenderer renderer = event.getSkin(modelSkin);
            renderer.addLayer(new JerboaOnShoulderLayer<>(renderer, event.getEntityModels()));
        }
    }
}
