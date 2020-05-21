package me.alchemi.vattghern.common.tileentities;

import me.alchemi.vattghern.Config;
import me.alchemi.vattghern.common.blocks.BlockBrazier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileBrazier extends TileEntity implements ITickable {
	
	private static final int MAX_LOGS = 6;
	
	private boolean eternal;
	
	private ItemStack logs;
	private boolean lit = false;
	private int ticksLit = 0;
	private int ticksLitFire = 0;
	
	public TileBrazier(boolean eternal) {
		logs = ItemStack.EMPTY;
		this.eternal = eternal;
		if (eternal) {
			logs = new ItemStack(Blocks.LOG, MAX_LOGS);
		}
		sync();
	}
	
	public TileBrazier() {
		this(false);
	}

	public void sync() {
		
		if (world == null || pos == null) return;
		
		IBlockState state = world.getBlockState(pos);
		
		if (!(state.getBlock() instanceof BlockBrazier)) return;
		
		state = state.withProperty(BlockBrazier.LIT, lit).withProperty(BlockBrazier.LOGS, getLogs()).withProperty(BlockBrazier.ETERNAL, eternal);
		world.setBlockState(pos, state);
		markDirty();
		
	}
	
	public void toggleLit() {
		lit = !lit;
		
		if (!lit) {
			ticksLitFire = 0;
			
		} else {
			if (!world.isRemote) world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
		}
		
		sync();
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return false;
	}
	
	public ItemStack addLog(ItemStack log, boolean all) {
		
		if (eternal) return log;
		
		if (logs.getCount() < MAX_LOGS
				&& logs.isItemEqual(log)) {
			
			int amount = logs.getCount() + 1;
			
			if (all) {
				amount = MAX_LOGS - logs.getCount();
			}
			
			log.setCount(log.getCount() - (amount - logs.getCount()));
			logs.setCount(amount);
			
		} else if (logs.isEmpty()) {
			
			int amount = 1;
			
			if (all) {
				amount = MAX_LOGS;
			}
			
			log.setCount(log.getCount() - amount);
			logs = log.copy();
			logs.setCount(amount);
			
		}
		
		sync();
		return log;
	}
	
	public void removeLog(boolean spawn) {
		if (eternal) return;
		
		if (logs.getCount() > 0) {
			if (spawn && !world.isRemote) 
				world.spawnEntity(new EntityItem(world, pos.getX(), pos.up().getY(), pos.getZ(), 
						new ItemStack(logs.getItem(), 1, logs.getMetadata())));

			logs.setCount(logs.getCount() - 1);
			sync();
		}
	}
	
	public void removeAllLogs(boolean spawn) {
		if (eternal) return;
		
		if (spawn & !world.isRemote) world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), logs));
		logs = ItemStack.EMPTY;
		
		sync();
		
	}
	
	public boolean isLit() {
		return lit;
	}
	
	public int getLogs() {
		return eternal ? MAX_LOGS : logs.getCount();
	}
	
	public int getMaxLogs() {
		return MAX_LOGS;
	}
	
	@Override
	public void update() {
		if (lit) {
			if (!world.isRemote && ticksLitFire%24 == 0) {
				world.playSound(null, pos, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.AMBIENT, 1.0F + world.rand.nextFloat(), world.rand.nextFloat() * 0.7F + 0.3F);
			}
			
			ticksLit ++;
			ticksLitFire ++;
			
			if (!eternal) {
				if (ticksLit >= Config.brazierLogBurnTime) {
					removeLog(false);
					ticksLit = 0;
					if (logs.getCount() == 0) {
						toggleLit();
						return;
					}
				}
				if (world.canSeeSky(pos) && world.isRainingAt(pos)) {
					toggleLit();
					if (!world.isRemote) world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
					return;
				}
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		ticksLit = compound.getInteger("ticksLit");
		lit = compound.getBoolean("lit");
		logs = new ItemStack(compound.getCompoundTag("logs"));
		eternal = compound.getBoolean("eternal");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setInteger("ticksLit", ticksLit);
		compound.setBoolean("lit", lit);
		compound.setTag("logs", logs.writeToNBT(new NBTTagCompound()));
		compound.setBoolean("eternal", eternal);
		return compound;
	}
	
}
