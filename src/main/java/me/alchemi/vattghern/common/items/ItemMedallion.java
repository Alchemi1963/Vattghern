package me.alchemi.vattghern.common.items;

import me.alchemi.vattghern.common.items.base.ItemBasic;
import me.alchemi.vattghern.objects.ModTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemMedallion extends ItemBasic {

	public ItemMedallion() {
		super("medallion");
		setCreativeTab(ModTabs.TAB_TOOLS);
	}
	
}
