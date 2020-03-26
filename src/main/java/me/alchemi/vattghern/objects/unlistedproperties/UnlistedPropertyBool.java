package me.alchemi.vattghern.objects.unlistedproperties;

import net.minecraftforge.common.property.IUnlistedProperty;

public class UnlistedPropertyBool implements IUnlistedProperty<Boolean>{

	private final String name;
	
	private UnlistedPropertyBool(String name) {
		this.name = name;
	}
	
	public static UnlistedPropertyBool create(String name) {
		return new UnlistedPropertyBool(name);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isValid(Boolean value) {
		return true;
	}

	@Override
	public Class<Boolean> getType() {
		return Boolean.class;
	}

	@Override
	public String valueToString(Boolean value) {
		return value.toString();
	}

}
