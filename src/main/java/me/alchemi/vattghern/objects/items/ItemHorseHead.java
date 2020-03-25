package me.alchemi.vattghern.objects.items;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.holders.BlockHolder;
import me.alchemi.vattghern.objects.EnumHorseType;
import me.alchemi.vattghern.objects.items.base.ItemBlockBasicMeta;
import me.alchemi.vattghern.objects.tileentities.TileEntityHorseHead;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemHorseHead extends ItemBlockBasicMeta {

	public ItemHorseHead() {
		super(BlockHolder.HORSE_HEAD, "horse_head");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
			return super.getUnlocalizedName() + ".donkey";
		case 1:
			return super.getUnlocalizedName() + ".horse_black";
		case 2:
			return super.getUnlocalizedName() + ".horse_brown";
		case 3:
			return super.getUnlocalizedName() + ".horse_chestnut";
		case 4:
			return super.getUnlocalizedName() + ".horse_creamy";
		case 5:
			return super.getUnlocalizedName() + ".horse_darkbrown";
		case 6:
			return super.getUnlocalizedName() + ".horse_gray";
		case 7:
			return super.getUnlocalizedName() + ".horse_skeleton";
		case 8:
			return super.getUnlocalizedName() + ".horse_white";
		case 9:
			return super.getUnlocalizedName() + ".horse_zombie";
		case 10:
			return super.getUnlocalizedName() + ".mule";
		default:
			return super.getUnlocalizedName() + ".horse_zombie";
		}
	}
	
}
