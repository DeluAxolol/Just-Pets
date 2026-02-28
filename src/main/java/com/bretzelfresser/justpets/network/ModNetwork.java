package com.bretzelfresser.justpets.network;

import com.bretzelfresser.justpets.JustPets;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;

public class ModNetwork 
{
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(ResourceLocation.fromNamespaceAndPath(JustPets.MODID, JustPets.MODID),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
	);
	
	public static int ID = 0;
	public static void registerMessages()
	{
		CHANNEL.registerMessage(ID++, UpdateJerboaEquipPacket.class, UpdateJerboaEquipPacket::write, UpdateJerboaEquipPacket::read, UpdateJerboaEquipPacket::handle);
	}
	
    public static <MSG> void sendToServer(MSG message) 
    {
    	CHANNEL.sendToServer(message);
    }
    
    public static <MSG> void sendToAll(MSG message)
    {
    	for(ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) 
    	{
    		CHANNEL.sendTo(message, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    	}
    }
}
