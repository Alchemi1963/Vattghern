package me.alchemi.vattghern.objects;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.common.blocks.BlockHorseHead;
import me.alchemi.vattghern.common.crops.Crop;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(value = Vattghern.MOD_ID)
public class ModBlocks {

	public static final BlockHorseHead HORSE_HEAD = null;
	
	public static final Crop CROP_CELANDINE = null;
	
	public static void registerModels() {
		HORSE_HEAD.initModel();
	}
}
