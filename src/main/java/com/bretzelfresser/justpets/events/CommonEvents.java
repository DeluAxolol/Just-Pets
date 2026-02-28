package com.bretzelfresser.justpets.events;

import java.util.List;

import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.registries.ModEntities;
import com.bretzelfresser.justpets.registries.ModSounds;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustPets.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		Player player = event.player;
		if(!player.getShoulderEntityLeft().isEmpty()) {
			EntityType.create(player.getShoulderEntityLeft(), player.level()).filter((t) -> t.getType() == ModEntities.JERBOA.get()).ifPresent((e) -> {
				List<Monster> list = player.level().getEntitiesOfClass(Monster.class, player.getBoundingBox().inflate(10.0F), t -> !t.isAlliedTo(player) && t.distanceTo(player) <= 7);
				if(!list.isEmpty()) {
					if(player.tickCount % 40 == 0) {
						player.playSound(ModSounds.JERBOA_ALARM.get(), 2.0F, 1.0F);
					}
				}
			});
		}
		if(!player.getShoulderEntityRight().isEmpty()) {
			EntityType.create(player.getShoulderEntityRight(), player.level()).filter((t) -> t.getType() == ModEntities.JERBOA.get()).ifPresent((e) -> {
				List<Monster> list = player.level().getEntitiesOfClass(Monster.class, player.getBoundingBox().inflate(10.0F), t -> !t.isAlliedTo(player) && t.distanceTo(player) <= 7);
				if(!list.isEmpty()) {
					if(player.tickCount % 40 == 0) {
						player.playSound(ModSounds.JERBOA_ALARM.get(), 2.0F, 1.0F);
					}
				}
			});
		}
	}
}
