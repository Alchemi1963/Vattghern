package me.alchemi.vattghern.objects;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.common.items.ItemCarvingKnife;
import me.alchemi.vattghern.common.items.ItemMedallion;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ObjectHolder(value = Vattghern.MOD_ID)
public class ModItems {

	//Items
	public static final ItemMedallion MEDALLION = null;
	
	
	//Tools
	public static final ItemCarvingKnife CARVING_KNIFE = null;
	
	//Weapons

	@SideOnly(Side.CLIENT)
	public static void registerModels() {
		MEDALLION.initModel();
		CARVING_KNIFE.initModel();
	}
	
}
