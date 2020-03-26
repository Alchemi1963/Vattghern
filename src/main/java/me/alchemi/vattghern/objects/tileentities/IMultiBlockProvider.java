package me.alchemi.vattghern.objects.tileentities;

import net.minecraft.util.ITickable;

public interface IMultiBlockProvider extends ITickable {

	boolean checkMultiBlockValid();
	
}
