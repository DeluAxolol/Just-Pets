package com.bretzelfresser.justpets.events;

import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.entity.Jerboa;
import com.bretzelfresser.justpets.registries.ModEntities;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustPets.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEvents {


    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.JERBOA.get(), Jerboa.createAttributes().build());
    }
    
    @SubscribeEvent
    public static void spawnPlacementRegister(SpawnPlacementRegisterEvent event) {
    	event.register(ModEntities.JERBOA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (p_217081_, p_217082_, p_217083_, p_217084_, p_217085_) -> p_217082_.getBlockState(p_217084_.below()).is(BlockTags.SAND) && p_217082_.getRawBrightness(p_217084_, 0) > 8, Operation.AND);
    }
}
