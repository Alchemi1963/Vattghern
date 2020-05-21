package me.alchemi.vattghern.compat;

import ganymedes01.headcrumbs.utils.helpers.VanillaHelper;
import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.objects.ModBlocks;
import me.alchemi.vattghern.utils.EnumHorseType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HeadcrumbsCompat implements ICompat {

	public static void init() {
		int i = 0;
		for (EnumHorseType horse : EnumHorseType.values()) {
			String horseType = horse.getName();
			if (horseType.equals("horse_skeleton")) {
				horseType = "skeleton_horse";
			} else if (horseType.equals("horse_zombie")) {
				horseType = "zombie_horse";
			}
			Ingredient.fromStacks(VanillaHelper.getStack(horseType));
			GameRegistry.addShapelessRecipe(new ResourceLocation(Vattghern.MOD_ID, "headcrumbsHorseConvert_" + i), 
					new ResourceLocation(Vattghern.MOD_ID), new ItemStack(ModBlocks.HORSE_HEAD, 1, i), NBTIngredient.fromStacks(VanillaHelper.getStack(horseType)));
			i++;
		}
	}
	
	@Override
	public boolean hookEnabled() {
		return true;
	}
	
	private static class NBTIngredient extends Ingredient {
		
		@Override
		public boolean apply(ItemStack p_apply_1_) {
			if(super.apply(p_apply_1_)) {
				for (ItemStack i : this.getMatchingStacks()) {
					if (i.hasTagCompound() && p_apply_1_.hasTagCompound() && i.getTagCompound().equals(p_apply_1_.getTagCompound())) {
						return true;
					} else if (!i.hasTagCompound() && !p_apply_1_.hasTagCompound()) {
						return true;
					}
				}
			}
			return false;
		}
		
	}

}
