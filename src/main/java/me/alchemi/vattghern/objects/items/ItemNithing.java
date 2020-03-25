package me.alchemi.vattghern.objects.items;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.holders.BlockHolder;
import me.alchemi.vattghern.objects.items.base.ItemBlockBasic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Vattghern.MOD_ID)
public class ItemNithing extends ItemBlockBasic {

	public ItemNithing() {
		super(BlockHolder.NITHING, "nithing");
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.DECORATIONS);
		
	}
	
	
	
}
