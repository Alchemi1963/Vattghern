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
	public String getTranslationKey(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
			return super.getTranslationKey() + ".donkey";
		case 1:
			return super.getTranslationKey() + ".horse_black";
		case 2:
			return super.getTranslationKey() + ".horse_brown";
		case 3:
			return super.getTranslationKey() + ".horse_chestnut";
		case 4:
			return super.getTranslationKey() + ".horse_creamy";
		case 5:
			return super.getTranslationKey() + ".horse_darkbrown";
		case 6:
			return super.getTranslationKey() + ".horse_gray";
		case 7:
			return super.getTranslationKey() + ".horse_skeleton";
		case 8:
			return super.getTranslationKey() + ".horse_white";
		case 9:
			return super.getTranslationKey() + ".horse_zombie";
		case 10:
			return super.getTranslationKey() + ".mule";
		default:
			return super.getTranslationKey() + ".horse_zombie";
		}
	}
	
}
