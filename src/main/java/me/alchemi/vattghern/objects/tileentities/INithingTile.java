package me.alchemi.vattghern.objects.tileentities;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public interface INithingTile extends IMultiBlockProvider {

	void setOwner(EntityPlayer owner);
	
	EntityPlayer getOwner();
	
	UUID getOwnerUUID();
	
	String getOwnerName();
	
	void setVictim(String name);
	
	String getVictim();
	
	EntityLivingBase getVictimInWorld();
	
	void destroy();
}
