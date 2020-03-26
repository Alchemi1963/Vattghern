package me.alchemi.vattghern.holders;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.objects.blocks.BlockHorseHead;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(value = Vattghern.MOD_ID)
public class BlockHolder {

	public static final BlockHorseHead HORSE_HEAD = null;
	
	public static void registerModels() {
		if (!Loader.isModLoaded("headcrumbs")) HORSE_HEAD.initModel();
	}
}
