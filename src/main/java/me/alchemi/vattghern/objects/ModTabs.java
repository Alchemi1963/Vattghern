package me.alchemi.vattghern.objects;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModTabs {

	public static final CreativeTabs TAB_FARMING = new CreativeTabs("tabFarming") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModCrops.CELANDINE.getSeeds());
		}
	};
	
	public static final CreativeTabs TAB_MOBDROPS = new CreativeTabs("tabMobDrops") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.ROTTEN_FLESH);
		}
	};
	
	public static final CreativeTabs TAB_TOOLS = new CreativeTabs("tabTools") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.MEDALLION);
		}
	};
	
}
