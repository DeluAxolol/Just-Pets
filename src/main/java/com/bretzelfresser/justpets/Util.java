package com.bretzelfresser.justpets;

import java.lang.reflect.Method;
import java.util.UUID;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class Util {
	public static final Method GET_ENTITY = ObfuscationReflectionHelper.findMethod(Level.class, "m_142646_");
	public static final Method SET_SHOULDER_ENTITY_RIGHT = ObfuscationReflectionHelper.findMethod(Player.class, "m_36364_", CompoundTag.class);
	public static final Method SET_SHOULDER_ENTITY_LEFT = ObfuscationReflectionHelper.findMethod(Player.class, "m_36362_", CompoundTag.class);
	
	@SuppressWarnings("unchecked")
	public static <T extends Entity> T getEntityByUUID(Level level, UUID uuid) {
		try {
			LevelEntityGetter<Entity> entities = (LevelEntityGetter<Entity>) GET_ENTITY.invoke(level);
			return (T) entities.get(uuid);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void respawnEntityOnShoulder(ServerPlayer player) {
		if(!player.getShoulderEntityRight().isEmpty()) {
			CompoundTag tag = player.getShoulderEntityRight();
			if (!player.level().isClientSide && !tag.isEmpty()) {
				EntityType.create(tag, player.level()).ifPresent((entity) -> {
					if (entity instanceof TamableAnimal) {
						((TamableAnimal)entity).setOwnerUUID(player.getUUID());
					}

					entity.setPos(player.getX(), player.getY() + (double)0.7F, player.getZ());
					((ServerLevel)player.level()).addWithUUID(entity);
				});
			}
			try {
				SET_SHOULDER_ENTITY_RIGHT.invoke(player, new CompoundTag());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(!player.getShoulderEntityLeft().isEmpty()) {
			CompoundTag tag = player.getShoulderEntityLeft();
			if (!player.level().isClientSide && !tag.isEmpty()) {
				EntityType.create(tag, player.level()).ifPresent((entity) -> {
					if (entity instanceof TamableAnimal) {
						((TamableAnimal)entity).setOwnerUUID(player.getUUID());
					}

					entity.setPos(player.getX(), player.getY() + (double)0.7F, player.getZ());
					((ServerLevel)player.level()).addWithUUID(entity);
				});
			}
			try {
				SET_SHOULDER_ENTITY_LEFT.invoke(player, new CompoundTag());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
