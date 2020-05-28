package me.alchemi.vattghern.objects;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.common.blocks.BlockBrazier;
import me.alchemi.vattghern.common.blocks.BlockHorseHead;
import me.alchemi.vattghern.common.blocks.ghosts.CropBryoniaTop;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(value = Vattghern.MOD_ID)
public class ModBlocks {

	public static final BlockHorseHead HORSE_HEAD = null;
	
	public static final BlockBrazier BRAZIER = null;
	
	//ghost blocks
	public static final CropBryoniaTop CROP_BRYONIA_TOP = null;
	
	public static void registerModels() {
		BRAZIER.initModel();
		HORSE_HEAD.initModel();
		CROP_BRYONIA_TOP.initModel();
	}
}
