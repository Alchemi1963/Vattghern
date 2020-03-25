package me.alchemi.vattghern.objects;

import me.alchemi.vattghern.Vattghern;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.registries.IForgeRegistryEntry;

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
}
