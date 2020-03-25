package me.alchemi.vattghern.objects.tileentities;

import me.alchemi.vattghern.objects.EnumHorseType;
import me.alchemi.vattghern.objects.blocks.BlockHorseHead;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityHorseHead extends TileEntity{

	private EnumHorseType horseType;
	private double skullRotation = 0.0D;
	
	public TileEntityHorseHead() {
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setString("horseType", horseType.toString());
		compound.setDouble("rot", skullRotation);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if (compound.hasKey("horseType")) {
			horseType = EnumHorseType.valueOf(compound.getString("horseType").toUpperCase());
		} else horseType = EnumHorseType.HORSE_ZOMBIE;
		
		if (compound.hasKey("rot")) {
			skullRotation = compound.getDouble("rot");
		} else skullRotation = 0D;
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, 1, getUpdateTag());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		super.handleUpdateTag(tag);
	}
	
	public EnumHorseType getHorseType() {
		return horseType;
	}
	
	public void setHorseType(EnumHorseType horseType) {
		this.horseType = horseType;
	}
	
	@SideOnly(Side.CLIENT)
	public double getSkullRotation() {
		return skullRotation;
	}
		
	public void setSkullRotation(double skullRotation) {
		this.skullRotation = skullRotation;
	}
	
}
