package me.alchemi.vattghern.common.items.base;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.common.blocks.base.BlockBasicMeta;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemBlockBasicMeta extends ItemBlock {
	
	public ItemBlockBasicMeta(BlockBasicMeta block, String name) {
		super(block);
		setRegistryName(new ResourceLocation(Vattghern.MOD_ID, name));
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		return super.getTranslationKey(stack) + "." + ((BlockBasicMeta)block).getNameFromMeta(stack.getMetadata());
	}
	
}
