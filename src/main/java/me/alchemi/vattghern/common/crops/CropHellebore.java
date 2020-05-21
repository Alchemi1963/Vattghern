package me.alchemi.vattghern.common.crops;

import me.alchemi.vattghern.common.items.base.ItemBasic;
import net.minecraft.block.properties.PropertyInteger;

public class CropHellebore extends Crop {

	public CropHellebore() {
		super("arenaria", 4, new ItemBasic("arenaria"), Crop.DEFAULT_CROP_AABB);
	}

	@Override
	protected PropertyInteger getAgeProperty() {
		return null;
	}

}
