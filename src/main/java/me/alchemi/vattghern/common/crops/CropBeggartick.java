package me.alchemi.vattghern.common.crops;

import me.alchemi.vattghern.common.items.base.ItemBasic;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;

public class CropBeggartick extends Crop {

	private static final PropertyInteger CROP_AGE = PropertyInteger.create("age", 0, 5);
	
	public CropBeggartick() {
		super("beggartick", 5, Blocks.GRASS, new ItemBasic("beggartick"), Crop.DEFAULT_CROP_AABB);
		setShearsEffective(true);
	}

	@Override
	protected PropertyInteger getAgeProperty() {
		return CROP_AGE;
	}

}
