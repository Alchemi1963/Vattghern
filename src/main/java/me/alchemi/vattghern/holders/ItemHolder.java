package me.alchemi.vattghern.holders;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.objects.items.ItemHorseHead;
import me.alchemi.vattghern.objects.items.ItemMedallion;
import me.alchemi.vattghern.objects.items.ItemNithing;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ObjectHolder(value = Vattghern.MOD_ID)
public class ItemHolder {

	//Items
	public static final ItemMedallion MEDALLION = null;
	
	
	//Tools
	
	//Weapons

	@SideOnly(Side.CLIENT)
	public static void registerModels() {
		MEDALLION.initModel();
	}
	
}
