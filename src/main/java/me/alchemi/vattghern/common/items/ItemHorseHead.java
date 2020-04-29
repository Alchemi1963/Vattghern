package me.alchemi.vattghern.common.items;

import me.alchemi.vattghern.common.items.base.ItemBlockBasicMeta;
import me.alchemi.vattghern.objects.ModBlocks;
import me.alchemi.vattghern.objects.ModTabs;
import net.minecraft.item.ItemStack;

public class ItemHorseHead extends ItemBlockBasicMeta {

	public ItemHorseHead() {
		super(ModBlocks.HORSE_HEAD, "horse_head");
		setCreativeTab(ModTabs.TAB_MOBDROPS);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
			return super.getUnlocalizedName() + ".donkey";
		case 1:
			return super.getUnlocalizedName() + ".horse_black";
		case 2:
			return super.getUnlocalizedName() + ".horse_brown";
		case 3:
			return super.getUnlocalizedName() + ".horse_chestnut";
		case 4:
			return super.getUnlocalizedName() + ".horse_creamy";
		case 5:
			return super.getUnlocalizedName() + ".horse_darkbrown";
		case 6:
			return super.getUnlocalizedName() + ".horse_gray";
		case 7:
			return super.getUnlocalizedName() + ".horse_skeleton";
		case 8:
			return super.getUnlocalizedName() + ".horse_white";
		case 9:
			return super.getUnlocalizedName() + ".horse_zombie";
		case 10:
			return super.getUnlocalizedName() + ".mule";
		default:
			return super.getUnlocalizedName() + ".horse_zombie";
		}
	}
	
}
