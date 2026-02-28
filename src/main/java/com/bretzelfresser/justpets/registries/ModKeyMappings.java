package com.bretzelfresser.justpets.registries;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;

public class ModKeyMappings {
	public static final KeyMapping EQUIP_JERBOA = new KeyMapping("key.justpets.equip", KeyConflictContext.IN_GAME, KeyModifier.SHIFT, InputConstants.Type.KEYSYM, InputConstants.KEY_X, "key.categories.justpets");
	public static final KeyMapping UNEQUIP_JERBOA = new KeyMapping("key.justpets.unequip", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_X, "key.categories.justpets");
}
