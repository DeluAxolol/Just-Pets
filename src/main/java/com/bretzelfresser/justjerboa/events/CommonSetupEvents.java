package com.bretzelfresser.justjerboa.events;

import com.bretzelfresser.justjerboa.JustJerboa;
import com.bretzelfresser.justjerboa.entity.Jerboa;
import com.bretzelfresser.justjerboa.registries.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustJerboa.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEvents {


    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.JERBOA.get(), Jerboa.createAttributes().build());
    }
}
