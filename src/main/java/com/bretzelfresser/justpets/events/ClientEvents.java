package com.bretzelfresser.justpets.events;

import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.entity.Jerboa;
import com.bretzelfresser.justpets.network.ModNetwork;
import com.bretzelfresser.justpets.network.UpdateJerboaEquipPacket;
import com.bretzelfresser.justpets.registries.ModKeyMappings;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustPets.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.player != null && mc.level != null && event.phase == Phase.START)
		{
			if(mc.crosshairPickEntity instanceof Jerboa jerboa)
			{
				if(ModKeyMappings.EQUIP_JERBOA.isDown()) {
					ModNetwork.sendToServer(new UpdateJerboaEquipPacket(mc.player.getUUID(), jerboa.getUUID(), true));
				}
			}
			if(ModKeyMappings.UNEQUIP_JERBOA.isDown()) {
				ModNetwork.sendToServer(new UpdateJerboaEquipPacket(mc.player.getUUID(), mc.player.getUUID(), false));
			}
		}
	}
}
