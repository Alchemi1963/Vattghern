package me.alchemi.vattghern.utils;

import me.alchemi.vattghern.Vattghern;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.OreDictionary;

public class Utils {

	public static Item newItem(String registryName) {
		Item item = new Item();
		return setup(item, registryName);
	}
	
	public static Item setup(Item item, String registryName) {
		item = item.setRegistryName(Vattghern.MOD_ID, registryName);
		item = item.setTranslationKey(Vattghern.MOD_ID + "." + registryName);
		return item;
	}
	
	public static String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
	
	public static AxisAlignedBB createAABB(double x1, double y1, double z1, double x2, double y2, double z2) {
		return new AxisAlignedBB(x1* 0.0625D, y1 * 0.0625D, z1 * 0.0625D, x2 * 0.0625D, y2 * 0.0625D, z2 * 0.0625D);
	}
	
	public static boolean containsOreDict(IBlockState state, String oredict) {
		return containsOreDict(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), oredict);
	}
	
	public static boolean containsOreDict(ItemStack item, String oredict) {
		return OreDictionary.doesOreNameExist(oredict) && OreDictionary.containsMatch(false, OreDictionary.getOres(oredict, false), item);
	}
	
	public static void sendMessageToPlayer(EntityPlayer player, String message) {
		if (!player.world.isRemote) player.sendMessage(new TextComponentTranslation(message));
	}
	
	public static final <T> T getTileEntity(IBlockAccess world, BlockPos pos, Class<T> cls){
		TileEntity te = world.getTileEntity(pos);
		if (cls.isInstance(te)) {
			return (T)te;
		}
		return null;
	}
}
