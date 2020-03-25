package me.alchemi.vattghern.objects.tileentities;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.google.common.base.Predicate;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.objects.blocks.BlockPhantom;
import me.alchemi.vattghern.objects.gui.NithingGui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TileEntityNithing extends TileEntity implements ITickable {
	
	private UUID ownerUUID;
	private String ownerName;
	private String victim;
	private ItemStack fenceItem;
	private ItemStack headItem;
	private EntityLivingBase victimEntity;
	
	private int ticks = 0;
	
	private boolean destroy = false;
	
	public TileEntityNithing() {
		MinecraftForge.EVENT_BUS.register(this);	
	}
	
	public void setFenceItem(ItemStack fenceItem) {
		this.fenceItem = fenceItem;
	}
	
	public void setHeadItem(ItemStack headItem) {
		this.headItem = headItem;
	}
	
	public void setOwner(EntityPlayer owner) {
		this.ownerUUID = owner.getUniqueID();
		this.ownerName = owner.getName();
	}
	
	public EntityPlayer getOwner() {
		return world.getMinecraftServer().getPlayerList().getPlayerByUUID(ownerUUID);
	}
	
	public UUID getOwnerUUID() {
		return ownerUUID;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	public ItemStack getFenceItem() {
		return fenceItem;
	}
	
	public ItemStack getHeadItem() {
		return headItem;
	}
	
	public void setVictim(String victim) {
		this.victim = victim;
	}
	
	public String getVictim() {
		return victim;
	}
	
	public EntityLivingBase getVictimInWorld() {
		List<EntityLivingBase> ents = world.getEntitiesWithinAABB(EntityLivingBase.class, 
				new AxisAlignedBB(pos.getX() - 100, pos.getY() - 100, pos.getZ() - 100, pos.getX() + 100, pos.getY() + 100, pos.getZ() + 100), 
				new Predicate<EntityLivingBase>() {
					@Override
					public boolean apply(EntityLivingBase t) {
						return t instanceof EntityPlayer ? t.getName().equalsIgnoreCase(victim) 
								: t.hasCustomName() ? t.getCustomNameTag().equalsIgnoreCase(victim) : false;
					}
				});
		Random rand = new Random();
		return ents.size() > 0 ? ents.get(rand.nextInt(ents.size())) : null;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		ownerUUID = nbt.getUniqueId("ownerUUID");
		ownerName = nbt.getString("ownerName");
		victim = nbt.getString("victim");
		fenceItem = new ItemStack(nbt.getCompoundTag("fence"));
		headItem = new ItemStack(nbt.getCompoundTag("head"));
		
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		if (ownerUUID != null) compound.setUniqueId("owner", ownerUUID);
		if (victim != null) compound.setString("victim", victim);
		if (fenceItem != null) compound.setTag("fence", fenceItem.writeToNBT(new NBTTagCompound()));
		if (headItem != null) compound.setTag("head", headItem.writeToNBT(new NBTTagCompound()));
		return compound;
	}
	
	@Override
	public void update() {
		//check if phantomblock still exists
		if (!(world.getBlockState(pos.add(0, 1, 0)).getBlock() instanceof BlockPhantom)) {
			world.destroyBlock(pos, true);
			return;
		}
		
		if (destroy) {
			ticks ++;
			if (ticks >= 5) getWorld().destroyBlock(getPos(), true);
			return;
		}
		if (victimEntity == null) {
			victimEntity = getVictimInWorld();
			if (victimEntity == null) {
				ticks = 0;
				return;
			}
		} else if (!victimEntity.isEntityAlive()) {
			victimEntity = null;
			ticks = 0;
			return;
		}
		
		double dis = Math.sqrt(victimEntity.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()));
		if (dis <= 100) {
			ticks ++;
			
			if (ticks >= 20 * 5 && ticks%10 == 0) victimEntity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20 * 8, 0));
			if (ticks >= 20 * 20 && ticks%10 == 0) victimEntity.addPotionEffect(new PotionEffect(MobEffects.POISON, 20 * 8, 1));
			if (ticks >= 20 * 30 && ticks%10 == 0) victimEntity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 8, 0));
			if (ticks >= 20 * 50 && ticks%10 == 0) victimEntity.addPotionEffect(new PotionEffect(MobEffects.WITHER, 20 * 8, 0));
		} else {
			ticks = 0;
		}
	}
	
	@SubscribeEvent
	public void onOwnerDeath(LivingDeathEvent e) {
		if (e.getEntityLiving().getUniqueID().equals(ownerUUID)) {
			if (hasWorld()) {
				Vattghern.LOGGER.debug(getWorld().destroyBlock(pos, false));
			}
		}
	}

	public void destroy() {
		destroy = true;
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt = writeToNBT(nbt);
		return new SPacketUpdateTileEntity(getPos(), 0, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}
	
	@Override
	public boolean hasFastRenderer() {
		return false;
	}
}
