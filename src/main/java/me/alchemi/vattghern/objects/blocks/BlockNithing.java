package me.alchemi.vattghern.objects.blocks;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.holders.BlockHolder;
import me.alchemi.vattghern.objects.Utils;
import me.alchemi.vattghern.objects.tileentities.TileEntityNithing;
import me.alchemi.vattghern.objects.tileentities.special.NithingTESR;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Vattghern.MOD_ID)
public class BlockNithing extends BlockContainer implements ITileEntityProvider{

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public static final AxisAlignedBB AABB = Utils.createAABB(6, 0, 6, 10, 25.5, 10);
	
	public BlockNithing() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(Vattghern.MOD_ID, "nithing"));
		setUnlocalizedName("nithing");
		setHardness(3.0F);
		setCreativeTab(CreativeTabs.DECORATIONS);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB.setMaxY(1.0D);
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return super.getBlockLayer();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return super.getRenderType(state);
	}
	
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		
		//bind TESR
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNithing.class, new NithingTESR());
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		drops.add(new ItemStack(this, 1, getMetaFromState(state)));
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player,
			boolean willHarvest) {
		TileEntityNithing te = (TileEntityNithing) world.getTileEntity(pos);
		return (player.isCreative() || te.getOwnerUUID() == null || player.getUniqueID().equals(te.getOwnerUUID())) 
				? super.removedByPlayer(state, world, pos, player, willHarvest) : false;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return createBlockState().getBaseState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntityNithing te = (TileEntityNithing) worldIn.getTileEntity(pos);
		te.setOwner((EntityPlayer) placer);
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("fence")) te.setFenceItem(new ItemStack(stack.getTagCompound().getCompoundTag("fence")));
		else te.setFenceItem(new ItemStack(Blocks.OAK_FENCE));
		
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("head")) te.setHeadItem(new ItemStack(stack.getTagCompound().getCompoundTag("head")));
		else te.setHeadItem(new ItemStack(BlockHolder.HORSE_HEAD, 1, 9));
		BlockHolder.BLOCKPHANTOM_0.place(worldIn, state, pos.add(0, 1, 0), BlockNithing.class);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityNithing();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}
}
