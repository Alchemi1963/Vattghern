package me.alchemi.vattghern.objects.blocks;

import java.util.Random;

import me.alchemi.vattghern.holders.ItemHolder;
import me.alchemi.vattghern.objects.EnumHorseType;
import me.alchemi.vattghern.objects.Utils;
import me.alchemi.vattghern.objects.blocks.base.BlockBasicMeta;
import me.alchemi.vattghern.objects.models.blocks.ModelHorseHead;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BlockHorseHead extends BlockBasicMeta {

	public static final PropertyEnum<EnumHorseType> HORSETYPE = PropertyEnum.create("horsetype", EnumHorseType.class);
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	protected static final AxisAlignedBB NORTH_AABB = Utils.createAABB(6, 0, 0, 10, 14, 12);
	protected static final AxisAlignedBB SOUTH_AABB = Utils.createAABB(6, 0, 4, 10, 14, 16);
	protected static final AxisAlignedBB EAST_AABB = Utils.createAABB(0, 0, 6, 12, 14, 10);
	protected static final AxisAlignedBB WEST_AABB = Utils.createAABB(4, 0, 6, 16, 14, 10);
	
	public BlockHorseHead() {
		super(Material.CIRCUITS, "horse_head", 11);
		setDefaultState(getDefaultState().withProperty(HORSETYPE, EnumHorseType.HORSE_ZOMBIE).withProperty(FACING, EnumFacing.NORTH));
		setHardness(0.5F);
		setSoundType(SoundType.STONE);
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		drops.add(new ItemStack(this, 1, this.getMetaFromState(state)));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(this, 1, this.getMetaFromState(state));
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch(state.getValue(FACING)) {
		case EAST:
			return EAST_AABB;
		case NORTH:
			return NORTH_AABB;
		case SOUTH:
			return SOUTH_AABB;
		case WEST:
			return WEST_AABB;
		default:
			return NORTH_AABB;
		}
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, HORSETYPE, FACING);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		switch(state.getValue(HORSETYPE)) {
		case DONKEY:
			return 0;
		case HORSE_BLACK:
			return 1;
		case HORSE_BROWN:
			return 2;
		case HORSE_CHESTNUT:
			return 3;
		case HORSE_CREAMY:
			return 4;
		case HORSE_DARKBROWN:
			return 5;
		case HORSE_GRAY:
			return 6;
		case HORSE_SKELETON:
			return 7;
		case HORSE_WHITE:
			return 8;
		case HORSE_ZOMBIE:
			return 9;
		case MULE:
			return 10;
		default:
			return 9;
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		switch(meta) {
		case 0:
			return getDefaultState().withProperty(HORSETYPE, EnumHorseType.DONKEY);
		case 1:
			return getDefaultState().withProperty(HORSETYPE, EnumHorseType.HORSE_BLACK);
		case 2:
			return getDefaultState().withProperty(HORSETYPE, EnumHorseType.HORSE_BROWN);
		case 3:
			return getDefaultState().withProperty(HORSETYPE, EnumHorseType.HORSE_CHESTNUT);
		case 4:
			return getDefaultState().withProperty(HORSETYPE, EnumHorseType.HORSE_CREAMY);
		case 5:
			return getDefaultState().withProperty(HORSETYPE, EnumHorseType.HORSE_DARKBROWN);
		case 6:
			return getDefaultState().withProperty(HORSETYPE, EnumHorseType.HORSE_GRAY);
		case 7:
			return getDefaultState().withProperty(HORSETYPE, EnumHorseType.HORSE_SKELETON);
		case 8:
			return getDefaultState().withProperty(HORSETYPE, EnumHorseType.HORSE_WHITE);
		case 9:
			return getDefaultState().withProperty(HORSETYPE, EnumHorseType.HORSE_ZOMBIE);
		case 10:
			return getDefaultState().withProperty(HORSETYPE, EnumHorseType.MULE);
		default:
			return getDefaultState();
		}
	}
	
}
