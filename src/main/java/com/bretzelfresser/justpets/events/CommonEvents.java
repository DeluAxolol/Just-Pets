package com.bretzelfresser.justpets.events;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.Util;
import com.bretzelfresser.justpets.entity.Jerboa;
import com.bretzelfresser.justpets.registries.ModEntities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustPets.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {
	public static final Map<UUID, Optional<Entity>> JERBOA_MAP = new HashMap<>();
	
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		Player player = event.player;
		if(!player.getShoulderEntityLeft().isEmpty()) {
			if(!JERBOA_MAP.containsKey(player.getUUID())) {
				JERBOA_MAP.put(player.getUUID(), EntityType.create(player.getShoulderEntityLeft(), player.level()).filter((t) -> t.getType() == ModEntities.JERBOA.get()));
			}
		}
		if(!player.getShoulderEntityRight().isEmpty()) {
			if(!JERBOA_MAP.containsKey(player.getUUID())) {
				JERBOA_MAP.put(player.getUUID(), EntityType.create(player.getShoulderEntityRight(), player.level()).filter((t) -> t.getType() == ModEntities.JERBOA.get()));
			}
		}
		if(JERBOA_MAP.containsKey(player.getUUID())) {
			JERBOA_MAP.get(player.getUUID()).ifPresent((e) -> {
				if(e instanceof Jerboa jerboa) {
					jerboa.alarm.tick(player);
				}
			});
		}
		JERBOA_MAP.keySet().removeIf(t -> !Util.getEntityByUUID(player.level(), t).isAlive());
	}
}
