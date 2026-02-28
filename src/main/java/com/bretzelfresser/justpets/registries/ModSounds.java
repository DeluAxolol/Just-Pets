package com.bretzelfresser.justpets.registries;

import com.bretzelfresser.justpets.JustPets;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, JustPets.MODID);

	public static final RegistryObject<SoundEvent> JERBOA_ALARM = registerSound("jerboa_alarm");
	public static final RegistryObject<SoundEvent> JERBOA_IDLE = registerSound("jerboa_idle");
	
	public static RegistryObject<SoundEvent> registerSound(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(JustPets.MODID, name)));
    }
}
