package me.alchemi.vattghern.common.items.base;

import me.alchemi.vattghern.Vattghern;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class ItemBlockBasic extends ItemBlock {

	public ItemBlockBasic(Block block, String name) {
		super(block);
		setRegistryName(new ResourceLocation(Vattghern.MOD_ID, name));
	}
	
}
