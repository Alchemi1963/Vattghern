package me.alchemi.vattghern.common.blocks;

import java.util.Map;

import me.alchemi.vattghern.common.blocks.base.BlockBasic;
import me.alchemi.vattghern.common.tileentities.TileBrazier;
import me.alchemi.vattghern.objects.ModTabs;
import me.alchemi.vattghern.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class BlockBrazier extends BlockBasic implements ITileEntityProvider{

	public static final PropertyInteger LOGS = PropertyInteger.create("logs", 0, 6);
	public static final PropertyBool LIT = PropertyBool.create("lit");
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public BlockBrazier() {
		super(Material.IRON, "brazier");
		setCreativeTab(ModTabs.TAB_DECO);
		setHardness(6.0F);
		
		setDefaultState(createBlockState().getBaseState().withProperty(LOGS, 0)
				.withProperty(LIT, false)
				.withProperty(FACING, EnumFacing.NORTH));
		
	}
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.getValue(LIT).booleanValue() ? 15 : 0;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, LOGS, LIT, FACING);
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
		return 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		System.out.println(Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(getDefaultState()).getParticleTexture());
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
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
			if (playerIn instanceof EntityPlayerMP) handStack.attemptDamageItem(1, RANDOM, (EntityPlayerMP) playerIn);
			return true;
			
		} else if (handStack.getItem() instanceof ItemBucket
				&& tile.isLit() && FluidUtil.getFluidContained(handStack).isFluidEqual(new FluidStack(FluidRegistry.WATER, Fluid.BUCKET_VOLUME))) {
			
			playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
			tile.toggleLit();
			return true;
			
		} else if (Utils.containsOreDict(handStack, "logWood") && tile.getLogs() < tile.getMaxLogs()
				&& !tile.isLit()) {
			
			playerIn.setHeldItem(hand, tile.addLog(handStack, playerIn.isSneaking()));
			return true;
			
		} else if (handStack.isEmpty() && !tile.isLit() && tile.getLogs() > 0) {
			
			if (playerIn.isSneaking()) {
				for (int i = 0; i < tile.getLogs(); i++) tile.removeLog(true);
			} else {
				tile.removeLog(true);
			}
			
			return true;
			
		}
		
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBrazier();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public void initModel() {
		super.initModel();
	}

}
