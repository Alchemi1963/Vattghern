package me.alchemi.vattghern.objects.recipes;

import org.apache.commons.codec.digest.Sha2Crypt;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.holders.BlockHolder;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeNithing2 extends ShapedOreRecipe{

	public RecipeNithing2() {
		super(new ResourceLocation(Vattghern.MOD_ID), new ItemStack(Item.getItemFromBlock(BlockHolder.NITHING)), new Primer());
		setRegistryName(new ResourceLocation(Vattghern.MOD_ID, "nithing_recipe"));
		
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		Vattghern.print(var1, var1.getStackInRowAndColumn(0, 1));
		ItemStack result = new ItemStack(Item.getItemFromBlock(BlockHolder.NITHING));
		NBTTagCompound nbt = new NBTTagCompound();
		
		if (var1.getStackInRowAndColumn(0, 1) != null 
				&& var1.getStackInRowAndColumn(0, 2).isItemEqual(var1.getStackInRowAndColumn(0, 1))) {
			
			nbt.setTag("head", var1.getStackInRowAndColumn(0, 0).writeToNBT(new NBTTagCompound()));
			nbt.setTag("fence", var1.getStackInRowAndColumn(1, 0).writeToNBT(new NBTTagCompound()));
			
			result.setTagCompound(nbt);
		} else if (var1.getStackInRowAndColumn(1, 1) != null 
				&& var1.getStackInRowAndColumn(1, 2).isItemEqual(var1.getStackInRowAndColumn(1, 1))) {
			
			nbt.setTag("head", var1.getStackInRowAndColumn(1, 0).writeToNBT(new NBTTagCompound()));
			nbt.setTag("fence", var1.getStackInRowAndColumn(1, 1).writeToNBT(new NBTTagCompound()));
			
			result.setTagCompound(nbt);
		} else if (var1.getStackInRowAndColumn(2, 1) != null 
				&& var1.getStackInRowAndColumn(2, 2).isItemEqual(var1.getStackInRowAndColumn(2, 1))) {
			
			nbt.setTag("head", var1.getStackInRowAndColumn(1, 0).writeToNBT(new NBTTagCompound()));
			nbt.setTag("fence", var1.getStackInRowAndColumn(1, 2).writeToNBT(new NBTTagCompound()));
			
			result.setTagCompound(nbt);
		} else {
			result = null;
		}
		return result;
	}

	private static class Primer extends ShapedPrimer {
		public Primer() {
			this.height = 3;
			this.width = 1;
			this.input = net.minecraft.util.NonNullList.withSize(3, Ingredient.fromItem(Items.AIR));
			input.set(0, new OreIngredient("skullHorse"));
			input.set(1, new OreIngredient("fenceWood"));
			input.set(2, new OreIngredient("fenceWood"));
		}
	}
	
	
}
