package me.alchemi.vattghern.common.blocks.ghosts;

import java.util.Random;

import me.alchemi.vattghern.common.blocks.base.BlockBasic;
import me.alchemi.vattghern.common.crops.Crop;
import me.alchemi.vattghern.common.crops.CropBryonia;
import me.alchemi.vattghern.objects.ModCrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CropBryoniaTop extends BlockBasic{

	private static final PropertyInteger CROP_AGE = PropertyInteger.create("age", 6, 11);
	
	public CropBryoniaTop() {
		super(Material.PLANTS, "crop_bryonia_top");
		setDefaultState(getDefaultState().withProperty(CROP_AGE, 6));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CROP_AGE);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		IBlockState motherState = worldIn.getBlockState(pos.down());
		if (motherState.getBlock() instanceof CropBryonia) {
			if (motherState.getValue(CropBryonia.CROP_AGE) < 6) {
				worldIn.setBlockToAir(pos);
			}
			
			if (state.getValue(CROP_AGE).intValue() != motherState.getValue(CropBryonia.CROP_AGE).intValue()) {
				state = state.withProperty(CROP_AGE, motherState.getValue(CropBryonia.CROP_AGE));
				worldIn.setBlockState(pos, state);
			}
			
		} else {
			worldIn.setBlockToAir(pos);
		}
		
		super.updateTick(worldIn, pos, state, rand);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState motherState = worldIn.getBlockState(pos.down());
		if (motherState.getBlock() instanceof CropBryonia) {
			return ModCrops.BRYONIA.onBlockActivated(worldIn, pos.down(), motherState, playerIn, hand, facing, hitX, hitY, hitZ);
		}
		return false;
	}
	
	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
		IBlockState motherState = world.getBlockState(pos.down());
		if (motherState.getBlock() instanceof CropBryonia) {
			ModCrops.BRYONIA.onBlockExploded(world, pos.down(), explosion);
		}
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		IBlockState motherState = worldIn.getBlockState(pos.down());
		if (motherState.getBlock() instanceof CropBryonia) {
			ModCrops.BRYONIA.onBlockHarvested(worldIn, pos.down(), motherState, player);
		}
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return Crop.FULL_BLOCK_AABB;
	}
}
