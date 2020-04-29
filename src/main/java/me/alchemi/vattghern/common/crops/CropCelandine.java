package me.alchemi.vattghern.common.crops;

import me.alchemi.vattghern.common.items.base.ItemBasic;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.math.AxisAlignedBB;

public class CropCelandine extends Crop{

	private static PropertyInteger CROP_AGE = PropertyInteger.create("age", 0, 7);
	
	public CropCelandine() {
		super("celandine", 7, Blocks.GRASS, new ItemBasic("celandine"), Crop.DEFAULT_CROP_AABB);
		setShearsEffective(true);
	}

	@Override
	protected PropertyInteger getAgeProperty() {
		return CROP_AGE;
	}
}
