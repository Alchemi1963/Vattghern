package me.alchemi.vattghern.compat;

import ganymedes01.headcrumbs.ModBlocks;
import ganymedes01.headcrumbs.blocks.BlockHeadcrumbsSkull;
import ganymedes01.headcrumbs.items.ItemHeadcrumbsSkull;
import ganymedes01.headcrumbs.tileentities.TileEntityBlockSkull;
import ganymedes01.headcrumbs.utils.HeadUtils;
import me.alchemi.vattghern.utils.Scheduler;
import me.alchemi.vattghern.utils.Utils;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HeadcrumbsCompat implements ICompat {

	public static HeadcrumbsCompat INSTANCE;
	
	private HeadcrumbsCompat() {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(HeadcrumbsNithingTE.class);
	}
	
	public static final void init() {
		INSTANCE = new HeadcrumbsCompat();
	}
	
	@Override
	public boolean hookEnabled() {
		return true;
	}

	@SubscribeEvent
	public void onHeadPlace(PlayerInteractEvent.RightClickBlock e) {
		if (Utils.containsOreDict(e.getWorld().getBlockState(e.getPos()), "fenceWood") 
				&& e.getFace() == EnumFacing.UP
				&& e.getItemStack().getItem() instanceof ItemHeadcrumbsSkull) {
			
			IBlockState oldState = e.getWorld().getBlockState(e.getPos());
			e.getWorld().setBlockState(e.getPos(), Blocks.SOUL_SAND.getDefaultState(), 7);
			
//			IBlockState clickedState = e.getWorld().getBlockState(e.getPos().offset(EnumFacing.UP));
//			System.out.println(clickedState.getMaterial());
//			System.out.println(ModBlocks.skull.canPlaceBlockAt(e.getWorld(), e.getPos()));
			
			((ItemHeadcrumbsSkull)e.getItemStack().getItem()).placeBlockAt(e.getItemStack(), e.getEntityPlayer(), e.getWorld(), e.getPos(), EnumFacing.UP, 0, 0, 0, null);
			new Scheduler(1, new Runnable() {
				
				@Override
				public void run() {
					e.getWorld().setBlockState(e.getPos(), oldState, 7);
					
				}
			});
			
			new Scheduler(2, new Runnable() {
				
				@Override
				public void run() {
					onHeadPlace(new PlaceEvent(new BlockSnapshot(e.getWorld(), e.getPos().add(0, 1, 0), oldState),
							oldState, e.getEntityPlayer(), e.getHand()));
				}
			});
		}
	}
	
	@SubscribeEvent
	public void onHeadPlace(BlockEvent.PlaceEvent e) {
		
		if (e.getState().getBlock() instanceof BlockHeadcrumbsSkull) {
			
			if (e.getWorld().getTileEntity(e.getPos()) != null) {
				TileEntityBlockSkull te = Utils.getTileEntity(e.getWorld(), e.getPos(), TileEntityBlockSkull.class);
				
				if (te != null 
						&& (te.getSkullModel().contains("horse") 
								|| te.getSkullModel().contains("donkey") 
								|| te.getSkullModel().contains("mule"))) {
					
					IBlockState state1 = e.getPlacedAgainst();
					IBlockState state2 = e.getWorld().getBlockState(e.getPos().add(0, -2, 0));
					
					if (Utils.containsOreDict(new ItemStack(state1.getBlock(), 1, state1.getBlock().getMetaFromState(state1)), "fenceWood")
						&& Utils.containsOreDict(new ItemStack(state2.getBlock(), 1, state2.getBlock().getMetaFromState(state2)), "fenceWood")) {
						
						HeadcrumbsNithingTE newTe = new HeadcrumbsNithingTE();
						newTe.readFromNBT(te.writeToNBT(new NBTTagCompound()));
						newTe.setOwner(e.getPlayer());
						e.getWorld().setTileEntity(e.getPos(), newTe);
						
					}
				}
			}
		}		
	}
}
