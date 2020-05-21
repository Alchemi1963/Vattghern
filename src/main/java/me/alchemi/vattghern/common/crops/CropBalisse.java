package me.alchemi.vattghern.common.crops;

import me.alchemi.vattghern.common.items.base.ItemBasic;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.init.Blocks;

public class CropBalisse extends Crop {

	private static final PropertyInteger CROP_AGE = PropertyInteger.create("age", 0, 7);
	
	public CropBalisse() {
		super("balisse", 7, Blocks.GRASS, new ItemBasic("balisse"), Crop.DEFAULT_CROP_AABB);
		setPreBlossomStage(4);
	}

	@Override
	protected PropertyInteger getAgeProperty() {
		return CROP_AGE;
	}

}
