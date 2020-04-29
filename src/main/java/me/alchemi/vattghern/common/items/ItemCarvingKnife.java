package me.alchemi.vattghern.common.items;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.common.items.base.ItemBasic;
import me.alchemi.vattghern.common.tileentities.TileEntityNithing;
import me.alchemi.vattghern.objects.ModTabs;
import me.alchemi.vattghern.proxies.GuiProxy;
import me.alchemi.vattghern.utils.Utils;
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
		setCreativeTab(ModTabs.TAB_TOOLS);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		TileEntityNithing tile = Utils.getTileEntity(worldIn, pos, TileEntityNithing.class);
		
		if (tile != null) {
			player.openGui(Vattghern.MOD_ID, GuiProxy.GUIS.NITHING.getID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
}
