package me.alchemi.vattghern.common.crops;

import java.util.Random;

import me.alchemi.vattghern.Config;
import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.common.items.base.ItemBasic;
import me.alchemi.vattghern.objects.ModTabs;
import me.alchemi.vattghern.objects.ModCrops;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Crop extends BlockCrops{

	public static final AxisAlignedBB[] DEFAULT_CROP_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), 
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), 
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), 
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), 
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), 
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), 
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	
	protected final ItemSeed seed;
	protected final ItemBasic crop;
	protected final AxisAlignedBB[] crop_aabb;
	protected final int maxAge;
	protected boolean shearsEffective;
	
	public Crop(String name, int maxAge, Block soilBlock, ItemBasic crop, AxisAlignedBB[] CROP_AABB) {
		super();
		setRegistryName(Vattghern.MOD_ID, "crop_" + name);
		setTranslationKey("crop_" + name);
		this.crop_aabb = CROP_AABB;
		this.maxAge = maxAge;
		this.seed = new ItemSeed(name + "_seeds", this, soilBlock);
		this.crop = crop;
		
		this.seed.setCreativeTab(ModTabs.TAB_FARMING);
		this.crop.setCreativeTab(ModTabs.TAB_FARMING);
		
		ModCrops.register(this);
	}
	
	public Crop(String name, int maxAge, ItemBasic crop, AxisAlignedBB[] CROP_AABB) {
		this(name, maxAge, Blocks.FARMLAND, crop, CROP_AABB);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if (state.getValue(getAgeProperty()).intValue() == maxAge
				&& shearsEffective && playerIn.getHeldItem(hand).getItem() instanceof ItemShears
				&& !worldIn.isRemote && !playerIn.isCreative()) {
			worldIn.setBlockState(pos, state.withProperty(getAgeProperty(), maxAge - 1));
			
			ItemStack shears = playerIn.getHeldItem(hand);
			shears.damageItem(1, playerIn);
			playerIn.setHeldItem(hand, shears);
			
			int amount = 1;
			if (worldIn.rand.nextInt(100) <= Config.extraCropChance) amount ++; 
			
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(crop, amount)));
			if (worldIn.rand.nextInt(100) <= Config.seedDropChance) worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(seed)));
			
			return true;
		}
		
		return false;
	}
	
	public void setShearsEffective(boolean shearsEffective) {
		this.shearsEffective = shearsEffective;
	}
	
	public boolean areShearsEffective() {
		return shearsEffective;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Crop && ((Crop)obj).getRegistryName().equals(this.getRegistryName());
	}
	
	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == seed.getSoilBlock(); 
	}
	
	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		IBlockState soil = worldIn.getBlockState(pos.down());
		return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && soil.getBlock() == seed.getSoilBlock();
	}
	
	@Override
	protected abstract PropertyInteger getAgeProperty();
	
	@Override
	public int getMaxAge() {
		return maxAge;
	}
	
	@Override
	protected Item getSeed() {
		return seed;
	}
	
	@Override
	protected Item getCrop() {
		return crop;
	}
	
	public ItemSeed getSeeds() {
		return seed;
	}
	
	public ItemBasic getCrops() {
		return crop;
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (rand.nextInt(3) == 0) {
			checkAndDropBlock(worldIn, pos, state);
		} else {
			super.updateTick(worldIn, pos, state, rand);
		}
	}
	
	@Override
	protected int getBonemealAgeIncrease(World worldIn) {
		return MathHelper.getInt(worldIn.rand, 1, maxAge - 1);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, getAgeProperty());
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return crop_aabb[state.getValue(this.getAgeProperty()).intValue()];
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
