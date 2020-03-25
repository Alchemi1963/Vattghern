package me.alchemi.vattghern.holders;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.objects.blocks.BlockHorseHead;
import me.alchemi.vattghern.objects.blocks.BlockNithing;
import me.alchemi.vattghern.objects.blocks.BlockPhantom;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(value = Vattghern.MOD_ID)
public class BlockHolder {

	public static final BlockNithing NITHING = null;
	public static final BlockPhantom BLOCKPHANTOM_0 = null;
	
	public static final BlockHorseHead HORSE_HEAD = null;
	
	public static void registerModels() {
		NITHING.initModel();
		HORSE_HEAD.initModel();
	}
}
