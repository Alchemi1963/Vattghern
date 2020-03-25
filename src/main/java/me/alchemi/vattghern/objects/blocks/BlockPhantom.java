package me.alchemi.vattghern.objects.blocks;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.objects.tileentities.TileEntityPhantom;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPhantom extends Block implements ITileEntityProvider {

	protected final AxisAlignedBB AABB;
	private final AxisAlignedBB parentAABB;
	protected int id;
	protected static int lastId = 0;
	private final EnumFacing face;	

	public BlockPhantom(AxisAlignedBB bounding_box, EnumFacing face) {
		super(Material.CIRCUITS);
		this.parentAABB = bounding_box;
		this.face = face;
		id = lastId;
		lastId++;
		setRegistryName(new ResourceLocation(Vattghern.MOD_ID, "blockphantom_" + id));
		switch(face) {
		case DOWN:
			this.AABB = new AxisAlignedBB(bounding_box.minX, bounding_box.minY * -1.0D, bounding_box.minZ, bounding_box.maxX, 1.0D, bounding_box.maxZ);
			break;
		case EAST:
			this.AABB = new AxisAlignedBB(0, bounding_box.minY, bounding_box.minZ, bounding_box.maxX - 1.0D, bounding_box.maxY, bounding_box.maxZ);
			break;
		case NORTH:
			this.AABB = new AxisAlignedBB(bounding_box.minX, bounding_box.minY, bounding_box.minZ * -1.0D, bounding_box.maxX, bounding_box.maxY, 1.0D);
			break;
		case SOUTH:
			this.AABB = new AxisAlignedBB(bounding_box.minX, bounding_box.minY, 0, bounding_box.maxX, bounding_box.maxY, bounding_box.maxZ - 1.0D);
			break;
		case UP:
			this.AABB = new AxisAlignedBB(bounding_box.minX, 0, bounding_box.minZ, bounding_box.maxX, bounding_box.maxY - 1.0D, bounding_box.maxZ);
			break;
		case WEST:
			this.AABB = new AxisAlignedBB(bounding_box.minX * -1.0D, bounding_box.minY, bounding_box.minZ, 1.0D, bounding_box.maxY, bounding_box.maxZ);
			break;
		default:
			this.AABB = new AxisAlignedBB(bounding_box.minX, 0, bounding_box.minZ, bounding_box.maxX, bounding_box.maxY - 1.0D, bounding_box.maxZ);
			break;
		
		}
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	public void place(World world, IBlockState state, BlockPos pos, Class<? extends Block> parent) {
		world.setBlockState(pos, this.getDefaultState());
		TileEntity te = world.getTileEntity(pos);
		if (!(te instanceof TileEntityPhantom)) {
			te = this.createNewTileEntity(world, 0);
			world.setTileEntity(pos, te);
		}
		((TileEntityPhantom)te).setParent(parent, face);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPhantom();
	}
	
}
