package me.alchemi.vattghern.objects.items;

import ganymedes01.headcrumbs.utils.Utils;
import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.objects.gui.NithingGui;
import me.alchemi.vattghern.objects.items.base.ItemBasic;
import me.alchemi.vattghern.objects.tileentities.INithingTile;
import me.alchemi.vattghern.proxies.GuiProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCarvingKnife extends ItemBasic {

	public ItemCarvingKnife() {
		super("carving_knife");
		setMaxDamage(1024);
		setMaxStackSize(1);
		setNoRepair();
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		INithingTile tile = Utils.getTileEntity(worldIn, pos, INithingTile.class);
		
		if (tile != null) {
			player.openGui(Vattghern.MOD_ID, GuiProxy.GUIS.NITHING.getID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
}
