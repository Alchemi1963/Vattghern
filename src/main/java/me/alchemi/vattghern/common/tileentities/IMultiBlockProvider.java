package me.alchemi.vattghern.common.tileentities;

import net.minecraft.util.ITickable;

public interface IMultiBlockProvider extends ITickable {

	boolean checkMultiBlockValid();
	
}
