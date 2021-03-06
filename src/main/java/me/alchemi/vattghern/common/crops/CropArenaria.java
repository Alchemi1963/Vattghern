package me.alchemi.vattghern.common.crops;

import me.alchemi.vattghern.common.items.base.ItemBasic;
import net.minecraft.block.properties.PropertyInteger;

public class CropArenaria extends Crop {

	private static final PropertyInteger CROP_AGE = PropertyInteger.create("age", 0, 7);
	
	public CropArenaria() {
		super("arenaria", 7, new ItemBasic("arenaria"), Crop.DEFAULT_CROP_AABB);
		setShearsEffective(true);
	}

	@Override
	protected PropertyInteger getAgeProperty() {
		return CROP_AGE;
	}

}
