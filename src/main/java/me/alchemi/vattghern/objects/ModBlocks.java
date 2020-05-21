package me.alchemi.vattghern.objects;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.common.blocks.BlockBrazier;
import me.alchemi.vattghern.common.blocks.BlockHorseHead;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(value = Vattghern.MOD_ID)
public class ModBlocks {

	public static final BlockHorseHead HORSE_HEAD = null;
	
	public static final BlockBrazier BRAZIER = null;
	
	public static void registerModels() {
		BRAZIER.initModel();
		HORSE_HEAD.initModel();
	}
}
