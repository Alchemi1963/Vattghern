package me.alchemi.vattghern.common.crops;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.init.Blocks;

public class CropBerbercane extends Crop {

	private static final PropertyInteger CROP_AGE = PropertyInteger.create("age", 0, 3);
	
	public CropBerbercane() {
		super("berbercane", 3, Blocks.GRASS, null, Crop.DEFAULT_CROP_AABB);
	}

	@Override
	protected PropertyInteger getAgeProperty() {
		return CROP_AGE;
	}

}
