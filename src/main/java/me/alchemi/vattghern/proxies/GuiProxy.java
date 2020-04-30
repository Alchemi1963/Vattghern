package me.alchemi.vattghern.proxies;

import me.alchemi.vattghern.client.gui.NithingGui;
import me.alchemi.vattghern.common.containers.NithingContainer;
import me.alchemi.vattghern.common.tileentities.TileNithing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

	public static enum GUIS {
		
		NITHING(0);
		
		private final int ID;
		
		private GUIS(int id) {
			ID = id;
		}
		
		public int getID() {
			return ID;
		}
		
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		switch(ID) {
		case 0:
			return new NithingContainer(player.inventory, (TileNithing)te);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		switch(ID) {
		case 0:
			return new NithingGui((TileNithing) te, new NithingContainer(player.inventory, (TileNithing)te));
		}
		return null;
	}

	
	
}
