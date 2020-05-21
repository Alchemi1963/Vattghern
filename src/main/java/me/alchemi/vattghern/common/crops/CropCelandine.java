package me.alchemi.vattghern.common.crops;

import me.alchemi.vattghern.common.items.base.ItemBasic;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.init.Blocks;

public class CropCelandine extends Crop{

	private static PropertyInteger CROP_AGE = PropertyInteger.create("age", 0, 7);
	
	public CropCelandine() {
		super("celandine", 7, Blocks.GRASS, new ItemBasic("celandine"), Crop.DEFAULT_CROP_AABB);
		setShearsEffective(true);
		setPreBlossomStage(4);
	}

	@Override
	protected PropertyInteger getAgeProperty() {
		return CROP_AGE;
	}
}
