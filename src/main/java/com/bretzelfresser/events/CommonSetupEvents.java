package com.bretzelfresser.events;

import com.bretzelfresser.JustJerboa;
import com.bretzelfresser.entity.Jerboa;
import com.bretzelfresser.registries.ModEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@Mod.EventBusSubscriber(modid = JustJerboa.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEvents {


    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.JERBOA.get(), Jerboa.createAttributes().build());
    }
}
