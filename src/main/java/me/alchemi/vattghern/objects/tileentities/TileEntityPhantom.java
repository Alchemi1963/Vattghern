package me.alchemi.vattghern.objects.tileentities;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityPhantom extends TileEntity implements ITickable {

	private Class<? extends Block> parent;
	private EnumFacing face;
	
	public TileEntityPhantom() {
	}
	
	public void setParent(Class<? extends Block> parent, EnumFacing facing) {
		this.parent = parent;
		this.face = facing;
	}
	
	@Override
	public void update() {
		//check if the parent block still exists
		int x, y, z;
		x = y = z = 0;
		switch (face) {
		case DOWN:
			y = 1;
			break;
		case EAST:
			x = -1;
			break;
		case NORTH:
			z = 1;
			break;
		case SOUTH:
			z = -1;
			break;
		case UP:
			y = -1;
			break;
		case WEST:
			x = 1;
			break;
		default:
			break;
		}
		IBlockState state = world.getBlockState(pos.add(x, y, z));
		if (!parent.isInstance(state.getBlock())) {
			world.setBlockToAir(pos);
		}
	}
	
}
