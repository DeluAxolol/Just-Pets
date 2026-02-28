package com.bretzelfresser.justpets.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.bretzelfresser.justpets.entity.Jerboa;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;

@Mixin(Player.class)
public class MixinPlayer 
{
    @Inject(method = "removeEntitiesOnShoulder", at = @At("HEAD"), cancellable = true)
    private void removeEntitiesOnShoulder(CallbackInfo ci)
    {
    	Player player = Player.class.cast(this);
    	if(!player.getShoulderEntityLeft().isEmpty())
    	{
            EntityType.create(player.getShoulderEntityLeft(), player.level()).ifPresent((t) -> 
            {
            	if(t instanceof Jerboa)
            	{
            		ci.cancel();
            	}
            });
    	}
    	if(!player.getShoulderEntityRight().isEmpty())
    	{
            EntityType.create(player.getShoulderEntityRight(), player.level()).ifPresent((t) -> 
            {
            	if(t instanceof Jerboa)
            	{
            		ci.cancel();
            	}
            });
    	}
    }
}
