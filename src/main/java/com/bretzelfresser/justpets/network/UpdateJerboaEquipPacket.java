package com.bretzelfresser.justpets.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.bretzelfresser.justpets.Util;
import com.bretzelfresser.justpets.entity.Jerboa;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

public class UpdateJerboaEquipPacket 
{
	private final UUID playerUUID;
	private final UUID entityUUID;
	private final boolean isEquip;

	public UpdateJerboaEquipPacket(UUID playerUUID, UUID entityUUID, boolean isEquip) 
	{
		this.playerUUID = playerUUID;
		this.entityUUID = entityUUID;
		this.isEquip = isEquip;
	}

	public static UpdateJerboaEquipPacket read(FriendlyByteBuf buf)
	{
		return new UpdateJerboaEquipPacket(buf.readUUID(), buf.readUUID(), buf.readBoolean());
	}

	public void write(FriendlyByteBuf buf)
	{
		buf.writeUUID(this.playerUUID);
		buf.writeUUID(this.entityUUID);
		buf.writeBoolean(this.isEquip);
	}

	public static boolean handle(UpdateJerboaEquipPacket message, Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(() ->
		{
			if(ctx.get().getDirection().getReceptionSide().isServer())
			{
				Entity entity = Util.getEntityByUUID(ctx.get().getSender().level(), message.playerUUID);
				if(entity instanceof ServerPlayer player) 
				{
					Entity entity1 = Util.getEntityByUUID(ctx.get().getSender().level(), message.entityUUID);
					if(entity1 instanceof Jerboa jerboa && jerboa.isTame() && jerboa.isOwnedBy(player) && message.isEquip) 
					{
						jerboa.setEntityOnShoulder(player);
					}
					if(!message.isEquip) {
						Util.respawnEntityOnShoulder(player);
					}
				}
			}
		});
		ctx.get().setPacketHandled(true);
		return true;
	}
}
