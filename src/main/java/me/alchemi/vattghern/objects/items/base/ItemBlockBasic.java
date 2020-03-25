package me.alchemi.vattghern.objects.items.base;

import me.alchemi.vattghern.Vattghern;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ItemBlockBasic extends ItemBlock {

	public ItemBlockBasic(Block block, String name) {
		super(block);
		setRegistryName(new ResourceLocation(Vattghern.MOD_ID, name));
	}
	
}
