package me.alchemi.vattghern.objects;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.objects.models.ModelLeshen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class Utils {

	public static Item newItem(String registryName) {
		Item item = new Item();
		return setup(item, registryName);
	}
	
	public static Item setup(Item item, String registryName) {
		item = item.setRegistryName(Vattghern.MOD_ID, registryName);
		item = item.setUnlocalizedName(Vattghern.MOD_ID + "." + registryName);
		return item;
	}
	
	public static AxisAlignedBB createAABB(double x1, double y1, double z1, double x2, double y2, double z2) {
		return new AxisAlignedBB(x1* 0.0625D, y1 * 0.0625D, z1 * 0.0625D, x2 * 0.0625D, y2 * 0.0625D, z2 * 0.0625D);
	}
	
	public static boolean containsOreDict(ItemStack item, String oredict) {
		return OreDictionary.doesOreNameExist(oredict) && OreDictionary.containsMatch(false, OreDictionary.getOres(oredict, false), item);
	}
	
	public static void sendMessageToPlayer(EntityPlayer player, String message) {
		if (!player.world.isRemote) player.sendMessage(new TextComponentTranslation(message));
	}
}
