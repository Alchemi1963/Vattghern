package me.alchemi.vattghern.utils;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public enum EnumHorseType implements IStringSerializable{

	DONKEY, HORSE_BLACK, HORSE_BROWN, HORSE_CHESTNUT, HORSE_CREAMY, HORSE_DARKBROWN, HORSE_GRAY, MULE, HORSE_SKELETON, HORSE_WHITE, HORSE_ZOMBIE;
	
	public ResourceLocation getTextureLocation() {
		return new ResourceLocation("textures/entity/horse/" + this.toString().toLowerCase() + ".png");
	}
	
	@Override
	public String getName() {
		return this.toString().toLowerCase();
	}
	
}
