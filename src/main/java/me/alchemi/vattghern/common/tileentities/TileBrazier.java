package me.alchemi.vattghern.common.tileentities;

import me.alchemi.vattghern.Config;
import me.alchemi.vattghern.common.blocks.BlockBrazier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;

public class TileBrazier extends TileEntity implements ITickable{
	
	private ItemStackHandler handler = new ItemStackHandler(1) {
		@Override
		public int getSlotLimit(int slot) {
			return 6;
		}
	};
	private boolean lit = false;
	
	private int ticksLit = 0;
	
	public TileBrazier() {
	}

	public IBlockState sync() {
		IBlockState state = this.world.getBlockState(pos);
		return state.withProperty(BlockBrazier.LIT, lit).withProperty(BlockBrazier.LOGS, handler.getStackInSlot(0).getCount());
	}
	
	public void toggleLit() {
		lit = !lit;
		sync();
	}
	
	public ItemStack addLog(ItemStack log, boolean all) {
		if (handler.getStackInSlot(0).getCount() < handler.getSlotLimit(0)
				&& handler.getStackInSlot(0).isItemEqual(log)) {
			
			ItemStack slot = handler.getStackInSlot(0);
			int amount = slot.getCount() + 1;
			if (all) {
				amount = slot.getCount() + log.getCount();
				if (amount > handler.getSlotLimit(0)) {
					amount = handler.getSlotLimit(0);
				}
				log.setCount(log.getCount() - (amount - slot.getCount()));
			} else {
				log.setCount(log.getCount() - 1);
			}
			slot.setCount(amount);
			handler.setStackInSlot(0, slot);
			
			sync();
		}
		return log;
	}
	
	public void removeLog(boolean spawn) {
		if (handler.getStackInSlot(0).getCount() > 0) {
			ItemStack slot = handler.getStackInSlot(0);
			slot.setCount(slot.getCount() - 1);
			if (spawn) world.spawnEntity(new EntityItem(world, pos.getX(), pos.up().getY(), pos.getZ(), new ItemStack(slot.getItem(), 1)));
			handler.setStackInSlot(0, slot);
			
			sync();
		}
	}
	
	public void removeAllLogs(boolean spawn) {
		for (int i = 0; i < getLogs(); i++) {
			removeLog(spawn);
		}
	}
	
	public boolean isLit() {
		return lit;
	}
	
	public int getLogs() {
		return handler.getStackInSlot(0).getCount();
	}
	
	public int getMaxLogs() {
		return handler.getSlotLimit(0);
	}
	
	@Override
	public void update() {
		if (lit) {
			ticksLit ++;
			if (ticksLit >= Config.brazierLogBurnTime) {
				removeLog(false);
				ticksLit = 0;
				if (handler.getStackInSlot(0).getCount() == 0) {
					toggleLit();
					return;
				}
			}
			if (world.canSeeSky(pos) && world.isRainingAt(pos)) {
				toggleLit();
				return;
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		ticksLit = compound.getInteger("ticksLit");
		lit = compound.getBoolean("lit");
		handler.setStackInSlot(0, new ItemStack(compound.getCompoundTag("logs")));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setInteger("ticksLit", ticksLit);
		compound.setBoolean("lit", lit);
		compound.setTag("logs", handler.getStackInSlot(0).writeToNBT(new NBTTagCompound()));
		return compound;
	}
	
}
