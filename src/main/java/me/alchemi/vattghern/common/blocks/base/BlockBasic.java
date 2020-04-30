package me.alchemi.vattghern.common.blocks.base;

import java.util.Map;

import me.alchemi.vattghern.Vattghern;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBasic extends Block{

	public BlockBasic(Material material, String name) {
		super(material);
		setRegistryName(Vattghern.MOD_ID, name);
		setTranslationKey(name);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0
				, new ModelResourceLocation(getRegistryName(), "inventory"));
		
	}
}
