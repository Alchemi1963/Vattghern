package me.alchemi.vattghern.common.crops;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.utils.Utils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemSeed extends ItemSeeds {
	
	protected final Block soilBlock;
	protected final Block cropBlock;
	
	public ItemSeed(String name, Block cropBlock, Block soilBlock) {
		super(cropBlock, soilBlock);
		this.soilBlock = soilBlock;
		this.cropBlock = cropBlock;
		setRegistryName(Vattghern.MOD_ID, name);
		setTranslationKey(name);

		OreDictionary.registerOre("crop" + Utils.capitalize(name), this);
		OreDictionary.registerOre("seed" + Utils.capitalize(name), this);
	}
	
	public ItemSeed(String name, Block cropBlock) {
		this(name, cropBlock, Blocks.FARMLAND);
	}
	
	public Block getSoilBlock() {
		return soilBlock;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock() == soilBlock && worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos.up(), cropBlock.getDefaultState());

            if (player instanceof EntityPlayerMP)
            {
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos.up(), itemstack);
            }

            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
