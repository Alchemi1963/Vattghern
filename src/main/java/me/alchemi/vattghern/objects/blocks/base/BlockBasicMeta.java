package me.alchemi.vattghern.objects.blocks.base;

import me.alchemi.vattghern.Vattghern;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBasicMeta extends Block {

	private final int subTypes;
	
	public BlockBasicMeta(Material material, String name, int subTypes) {
		super(material);
		setRegistryName(Vattghern.MOD_ID, name);
		setUnlocalizedName(name);
		this.subTypes = subTypes;
	}
	
	public int getSubTypes() {
		return subTypes;
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		for (int i = 0; i < subTypes; i++) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, new ModelResourceLocation(getRegistryName(), "inventory"+i));
		}
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (int i = 0; i < subTypes; i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}

	public String getNameFromMeta(int meta) {
		return "meta" + String.valueOf(meta);
	}
}
