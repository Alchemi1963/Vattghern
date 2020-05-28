package me.alchemi.vattghern.common.crops;

import me.alchemi.vattghern.common.items.base.ItemBasic;
import net.minecraft.block.properties.PropertyInteger;

public class CropBisonGrass extends Crop {

	private static final PropertyInteger CROP_AGE = PropertyInteger.create("age", 0, 7);
	
	public CropBisonGrass() {
		super("bison_grass", 7, new ItemBasic("bison_grass"), Crop.DEFAULT_CROP_AABB);
	}

	@Override
	protected PropertyInteger getAgeProperty() {
		return CROP_AGE;
	}

}
