package com.bretzelfresser.justpets.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bretzelfresser.justpets.registries.ModSounds;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;

public class JerboaAlarm {
	public final Jerboa jerboa;
	public final int maxCooldown;
	public final int maxAlarmTime;
	
	public final Map<EntityType<?>, JerboaAlarmState> alarmMap = new HashMap<>();
	
	public JerboaAlarm(Jerboa jerboa, int maxCooldown, int maxAlarmTime) {
		this.jerboa = jerboa;
		this.maxCooldown = maxCooldown;
		this.maxAlarmTime = maxAlarmTime;
	}
	
	public void tick(Player player) {
		if(player.tickCount % 40 == 0) {
			List<Monster> list = player.level().getEntitiesOfClass(Monster.class, player.getBoundingBox().inflate(10.0F), t -> !t.isAlliedTo(player) && t.distanceTo(player) <= 7);
			for(Monster monster : list) {
				if(this.alarmMap.containsKey(monster.getType())) {
					continue;
				}
				this.alarmMap.put(monster.getType(), new JerboaAlarmState());
			}
		}
		for(Map.Entry<EntityType<?>, JerboaAlarmState> entry : this.alarmMap.entrySet()) {
			JerboaAlarmState state = entry.getValue();
			if(state.cooldown <= 0) {
				if(state.alarmTime < this.maxAlarmTime) {
					if(state.tick++ >= 20) {
						state.tick = 0;
						state.alarmTime += 1;
						player.playSound(ModSounds.JERBOA_ALARM.get(), 2.0F, 1.0F);
					}
				}
				else {
					state.cooldown = this.maxCooldown;
					state.alarmTime = 0;
				}
			}
			else {
				state.cooldown--;
			}
		}
	}
	
	public void write(CompoundTag tag) {
		ListTag list = new ListTag();
		for(Map.Entry<EntityType<?>, JerboaAlarmState> entry : this.alarmMap.entrySet()) {
			EntityType<?> type = entry.getKey();
			JerboaAlarmState state = entry.getValue();
			CompoundTag nbt = new CompoundTag();
			nbt.putString("EntityType", ForgeRegistries.ENTITY_TYPES.getKey(type).toString());
			nbt.putInt("Cooldown", state.cooldown);
			nbt.putInt("AlarmTime", state.alarmTime);
			list.add(nbt);
		}
		tag.put("JerboaAlarm", list);
	}
	
	public void read(CompoundTag tag) {
		ListTag list = tag.getList("JerboaAlarm", 10);
		for(int i = 0; i < list.size(); i++) {
			CompoundTag nbt = list.getCompound(i);
			JerboaAlarmState state = new JerboaAlarmState();
			state.cooldown = nbt.getInt("Cooldown");
			state.alarmTime = nbt.getInt("AlarmTime");
			this.alarmMap.put(ForgeRegistries.ENTITY_TYPES.getValue(ResourceLocation.parse(nbt.getString("EntityType"))), state);
		}
	}
	
	public static class JerboaAlarmState {
		public int cooldown, alarmTime, tick;
	}
}
