package me.alchemi.vattghern.common.blocks;

import java.util.List;

import me.alchemi.vattghern.common.blocks.base.BlockBasicMeta;
import me.alchemi.vattghern.common.tileentities.TileBrazier;
import me.alchemi.vattghern.objects.ModTabs;
import me.alchemi.vattghern.utils.Scheduler;
import me.alchemi.vattghern.utils.Utils;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class BlockBrazier extends BlockBasicMeta implements ITileEntityProvider{

	private static final AxisAlignedBB AABB = Utils.createAABB(1, 0, 1, 15, 16, 15);
	private static final AxisAlignedBB AABB_LEGS = Utils.createAABB(3, 0, 3, 13, 1, 13);
	private static final AxisAlignedBB AABB_WALL_NORTH = Utils.createAABB(1, 0, 1, 15, 16, 2);
	private static final AxisAlignedBB AABB_WALL_SOUTH = Utils.createAABB(1, 0, 14, 15, 16, 15);
	private static final AxisAlignedBB AABB_WALL_EAST = Utils.createAABB(14, 0, 1, 15, 16, 15);
	private static final AxisAlignedBB AABB_WALL_WEST = Utils.createAABB(1, 0, 1, 2, 16, 15);
	
	private static final AxisAlignedBB[] AABB_LOGS = new AxisAlignedBB[] {Utils.createAABB(6.5, 2, 3, 9.5, 5, 13),
			Utils.createAABB(10, 2, 3, 13, 5, 13),
			Utils.createAABB(3, 2, 3, 6, 5, 13),
			Utils.createAABB(3, 5, 6.5, 13, 8, 9.5),
			Utils.createAABB(3, 5, 3, 13, 8, 6),
			Utils.createAABB(3, 5, 10, 13, 8, 13)}; 
	
	public static final PropertyInteger LOGS = PropertyInteger.create("logs", 0, 6);
	public static final PropertyBool LIT = PropertyBool.create("lit");
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool ETERNAL = PropertyBool.create("eternal");
	
	public BlockBrazier() {
		super(Material.IRON, "brazier", 2);
		setCreativeTab(ModTabs.TAB_DECO);
		setHardness(6.0F);
		
		setDefaultState(getDefaultState().withProperty(LOGS, 0)
				.withProperty(LIT, false)
				.withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 1, new ModelResourceLocation(getRegistryName(), "inventory_eternal"));
	}
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.getValue(LIT).booleanValue() ? 15 : 0;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, LOGS, LIT, FACING, ETERNAL);
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
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
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		super.onBlockHarvested(worldIn, pos, state, player);
		TileBrazier tile = Utils.getTileEntity(worldIn, pos, TileBrazier.class);
		if (tile != null) {
			tile.removeAllLogs(true);
		}
	}
	
	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
		super.onBlockExploded(world, pos, explosion);
		TileBrazier tile = Utils.getTileEntity(world, pos, TileBrazier.class);
		if (tile != null) {
			tile.removeAllLogs(true);
		}
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(ETERNAL).booleanValue() ? 1 : 0;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(this, 1, this.getMetaFromState(state));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return meta == 0 ? getDefaultState().withProperty(ETERNAL, false) : getDefaultState().withProperty(ETERNAL, true);
	}
	
	@Override
	public String getNameFromMeta(int meta) {
		return meta == 0 ? "" : "eternal";
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (state.getBlock() instanceof BlockBrazier) {
			
			if (entityIn.getPositionVector().y >= (double)pos.getY()
					&& entityIn.getPositionVector().y <= (double)pos.getY() + 0.5
					&& entityIn.getPositionVector().x > (double)pos.getX() + 0.125d
					&& entityIn.getPositionVector().x < (double)pos.getX() + 0.875d
					&& entityIn.getPositionVector().z > (double)pos.getZ() + 0.125d
					&& entityIn.getPositionVector().z < (double)pos.getZ() + 0.875d
					&& state.getValue(LIT).booleanValue()) {
				setEntityOnFire(entityIn, worldIn);
			}
			
		}
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		IBlockState state0 = worldIn.getBlockState(pos);
		IBlockState state1 = worldIn.getBlockState(pos.down());
		
		if (state0.getBlock() instanceof BlockBrazier && state0.getValue(LIT).booleanValue()) {
			setEntityOnFire(entityIn, worldIn);
		} else if (state1.getBlock() instanceof BlockBrazier && state1.getValue(LIT).booleanValue()) {
			setEntityOnFire(entityIn, worldIn);
		}
	}
	
	protected final void setEntityOnFire(Entity entityIn, World worldIn) {
		if (!entityIn.isImmuneToFire()) {
			entityIn.setFire(4);
			if (entityIn instanceof EntityItem) {
				new Scheduler(10, new Runnable() {
					
					@Override
					public void run() {
						worldIn.removeEntity(entityIn);
					}
				});
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack handStack = playerIn.getHeldItem(hand);
		TileBrazier tile = Utils.getTileEntity(worldIn, pos, TileBrazier.class);
		
		if (tile == null) return false;
		
		else if (handStack.getItem() instanceof ItemFlintAndSteel
				&& tile.getLogs() > 0 && !tile.isLit()) {
			
			tile.toggleLit();
			if (playerIn instanceof EntityPlayerMP && !playerIn.isCreative()) handStack.attemptDamageItem(1, RANDOM, (EntityPlayerMP) playerIn);
					
			return true;
			
		} else if (handStack.getItem() instanceof ItemBucket
				&& tile.isLit() && FluidUtil.getFluidContained(handStack).isFluidEqual(new FluidStack(FluidRegistry.WATER, Fluid.BUCKET_VOLUME))) {
			
			if (!playerIn.isCreative()) playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
			tile.toggleLit();
			if (!worldIn.isRemote) worldIn.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			
			return true;
			
		} else if (Utils.containsOreDict(handStack, "logWood") && tile.getLogs() < tile.getMaxLogs()
				&& !tile.isLit()) {
			if (!playerIn.isCreative()) playerIn.setHeldItem(hand, tile.addLog(handStack, playerIn.isSneaking()));
			else tile.addLog(handStack, playerIn.isSneaking());
			return true;
			
		} else if (handStack.isEmpty() && !tile.isLit() && tile.getLogs() > 0) {
			
			if (playerIn.isSneaking()) {
				tile.removeAllLogs(true);
			} else {
				tile.removeLog(true);
			}
			
			return true;
			
		}
		
		return true;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
        
        for (int i = 0; i < state.getValue(LOGS).intValue(); i++) {
        	addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LOGS[i]);
        }
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBrazier(meta == 1);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

}
