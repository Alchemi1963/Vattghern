package me.alchemi.vattghern.common.blocks;

import java.util.Arrays;

import me.alchemi.vattghern.common.blocks.base.BlockBasicMeta;
import me.alchemi.vattghern.common.tileentities.TileEntityNithing;
import me.alchemi.vattghern.objects.ModBlocks;
import me.alchemi.vattghern.utils.EnumHorseType;
import me.alchemi.vattghern.utils.Utils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMule;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.passive.EntityZombieHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHorseHead extends BlockBasicMeta {

	public static final PropertyEnum<EnumHorseType> HORSETYPE = PropertyEnum.create("horsetype", EnumHorseType.class);
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool ISNITHING = PropertyBool.create("is_nithing");
	
	protected static final AxisAlignedBB NORTH_AABB = Utils.createAABB(6, 0, 0, 10, 14, 12);
	protected static final AxisAlignedBB SOUTH_AABB = Utils.createAABB(6, 0, 4, 10, 14, 16);
	protected static final AxisAlignedBB EAST_AABB = Utils.createAABB(0, 0, 6, 12, 14, 10);
	protected static final AxisAlignedBB WEST_AABB = Utils.createAABB(4, 0, 6, 16, 14, 10);
	
	public BlockHorseHead() {
		super(Material.CIRCUITS, "horse_head", 11);
		setDefaultState(getDefaultState().withProperty(HORSETYPE, EnumHorseType.HORSE_ZOMBIE)
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(ISNITHING, false));
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
		return new BlockStateContainer(this, new IProperty[] {FACING, HORSETYPE, ISNITHING});
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		
		if (placer instanceof EntityPlayer) {
			IBlockState state1 = worldIn.getBlockState(pos.add(0, -1, 0));
			IBlockState state2 = worldIn.getBlockState(pos.add(0, -2, 0));
			if (Utils.containsOreDict(new ItemStack(state1.getBlock(), 1, state1.getBlock().getMetaFromState(state1)), "fenceWood")
				&& Utils.containsOreDict(new ItemStack(state2.getBlock(), 1, state2.getBlock().getMetaFromState(state2)), "fenceWood")) {
			
				state = state.withProperty(ISNITHING, true);
				worldIn.setBlockState(pos, state);
				
				TileEntityNithing te = (TileEntityNithing) createTileEntity(worldIn, state);
				te.setOwner((EntityPlayer) placer);
				worldIn.setTileEntity(pos, te);
			}
		}
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getStateFromMeta(meta).withProperty(FACING, placer.getHorizontalFacing().getOpposite());
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
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return state.getValue(ISNITHING) ? new TileEntityNithing() : null;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return state.getValue(ISNITHING);
	}
	
	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player,
			boolean willHarvest) {
		
		if (state.getValue(ISNITHING)) {
			TileEntityNithing te = (TileEntityNithing) world.getTileEntity(pos);
			
			return (player.isCreative() || te.getOwnerUUID() == null || player.getUniqueID().equals(te.getOwnerUUID())) 
					? super.removedByPlayer(state, world, pos, player, willHarvest) : false;
		}
		return super.removedByPlayer(state, world, pos, player, willHarvest);
	}
	
	public static ItemStack getHeadItem(EntityLivingBase entity) {
		
		if (!(entity instanceof AbstractHorse)) return ItemStack.EMPTY;
		
		int meta = 0;
		
		if (entity instanceof EntityHorse) {
			NBTTagCompound nbt = entity.writeToNBT(new NBTTagCompound());
			int variant = nbt.getInteger("Variant");
			
			if (Arrays.asList(0, 256, 512, 768, 1024).contains(variant)) {
				//white	
				meta = 8;
			} else if (Arrays.asList(1, 257, 513, 769, 1025).contains(variant)) {
				//creamy
				meta = 4;
			} else if (Arrays.asList(2, 258, 514, 770, 1026).contains(variant)) {
				//chestnut
				meta = 3;
			} else if (Arrays.asList(3, 259, 515, 771, 1027).contains(variant)) {
				//brown
				meta = 2;
			} else if (Arrays.asList(4, 260, 516, 772, 1028).contains(variant)) {
				//black
				meta = 1;
			} else if (Arrays.asList(5, 261, 517, 773, 1029).contains(variant)) {
				//gray
				meta = 6;
			} else if (Arrays.asList(6, 262, 518, 774, 1030).contains(variant)) {
				//dark brown
				meta = 5;
			}
			
		} else if (entity instanceof EntitySkeletonHorse) {
			meta = 7;
		
		} else if (entity instanceof EntityZombieHorse) {
			meta = 9;
		
		} else if (entity instanceof EntityMule) {
			meta = 10;
		
		} else if (entity instanceof EntityDonkey) {
			meta = 0;
		}
		
		return new ItemStack(ModBlocks.HORSE_HEAD, 1, meta);
		
	}
	
}
