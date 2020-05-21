package me.alchemi.vattghern.common.items.base;

import me.alchemi.vattghern.Vattghern;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemBasic extends Item {

	public ItemBasic(String name) {
		super();
		setRegistryName(Vattghern.MOD_ID, name);
		setTranslationKey(name);
	}
	
	public void initModel() {
		 ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
}
