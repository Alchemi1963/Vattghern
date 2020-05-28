package me.alchemi.vattghern.common.crops;

import java.util.Random;

import me.alchemi.vattghern.common.items.base.ItemBasic;
import me.alchemi.vattghern.objects.ModBlocks;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CropBryonia extends Crop {

	public static final PropertyInteger CROP_AGE = PropertyInteger.create("age", 0, 11);
	
	public CropBryonia() {
		super("bryonia", 11, new ItemBasic("bryonia"), new AxisAlignedBB[] {
				Crop.FULL_BLOCK_AABB,
				Crop.FULL_BLOCK_AABB,
				Crop.FULL_BLOCK_AABB,
				Crop.FULL_BLOCK_AABB,
				Crop.FULL_BLOCK_AABB,
				Crop.FULL_BLOCK_AABB,
				Crop.FULL_BLOCK_AABB,
				Crop.FULL_BLOCK_AABB,
				Crop.FULL_BLOCK_AABB,
				Crop.FULL_BLOCK_AABB,
				Crop.FULL_BLOCK_AABB,
				Crop.FULL_BLOCK_AABB,				
		});
		setShearsEffective(true);
		setMultiplier(2);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		IBlockState updatedState = worldIn.getBlockState(pos);
		if (updatedState.getValue(CROP_AGE) >= 6) {
			worldIn.setBlockState(pos.up(), ModBlocks.CROP_BRYONIA_TOP.getDefaultState());
		}
	}
	
	@Override
	protected PropertyInteger getAgeProperty() {
		return CROP_AGE;
	}

}
