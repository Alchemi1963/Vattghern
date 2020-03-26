package me.alchemi.vattghern.compat;

import ganymedes01.headcrumbs.blocks.BlockHeadcrumbsSkull;
import me.alchemi.vattghern.objects.Utils;
import me.alchemi.vattghern.objects.tileentities.TileEntityNithing;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HeadcrumbsCompat implements ICompat {

	public static HeadcrumbsCompat INSTANCE;
	
	private HeadcrumbsCompat() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public static final void init() {
		INSTANCE = new HeadcrumbsCompat();
	}
	
	@Override
	public boolean hookEnabled() {
		return true;
	}

	@SubscribeEvent
	public void onHeadPlace(BlockEvent.PlaceEvent e) {
		
		if (e.getState().getBlock() instanceof BlockHeadcrumbsSkull) {
			IBlockState state1 = e.getWorld().getBlockState(e.getPos().add(0, -1, 0));
			IBlockState state2 = e.getWorld().getBlockState(e.getPos().add(0, -2, 0));
			if (Utils.containsOreDict(new ItemStack(state1.getBlock(), 1, state1.getBlock().getMetaFromState(state1)), "fenceWood")
				&& Utils.containsOreDict(new ItemStack(state2.getBlock(), 1, state2.getBlock().getMetaFromState(state2)), "fenceWood")) {
			
				TileEntityNithing te = new TileEntityNithing();
				te.setOwner(e.getPlayer());
				e.getWorld().setTileEntity(e.getPos(), te);
			
			}
		}
		
	}
	
}
