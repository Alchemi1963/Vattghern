package me.alchemi.vattghern.common.items;

import java.util.List;

import me.alchemi.vattghern.common.items.base.ItemBlockBasicMeta;
import me.alchemi.vattghern.objects.ModBlocks;
import me.alchemi.vattghern.objects.ModTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemBrazier extends ItemBlockBasicMeta {

	public ItemBrazier() {
		super(ModBlocks.BRAZIER, "brazier");
		setCreativeTab(ModTabs.TAB_DECO);
		
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		if (stack.getMetadata() == 1) {
			tooltip.add(new TextComponentTranslation("tile.brazier.eternal.lore").getFormattedText());
		}
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return stack.getMetadata() == 1;
	}
	
}
