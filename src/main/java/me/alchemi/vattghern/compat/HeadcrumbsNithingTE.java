package me.alchemi.vattghern.compat;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.google.common.base.Predicate;

import ganymedes01.headcrumbs.tileentities.TileEntityBlockSkull;
import me.alchemi.vattghern.objects.tileentities.IMultiBlockProvider;
import me.alchemi.vattghern.objects.tileentities.INithingTile;
import me.alchemi.vattghern.objects.tileentities.TileEntityNithing;
import me.alchemi.vattghern.utils.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class HeadcrumbsNithingTE extends TileEntityBlockSkull implements INithingTile {

	private UUID ownerUUID;
	private String ownerName;
	private String victim;
	private EntityLivingBase victimEntity;
	
	private int ticks = 0;
	
	private boolean destroy = false;
	
	@Override
	public void setOwner(EntityPlayer owner) {
		this.ownerUUID = owner.getUniqueID();
		this.ownerName = owner.getName();
		Utils.sendMessageToPlayer(owner, "nithing.setvictim.name");
	}
	
	@Override
	public EntityPlayer getOwner() {
		return world != null ? world.getMinecraftServer().getPlayerList().getPlayerByUUID(ownerUUID) : null;
	}
	
	@Override
	public UUID getOwnerUUID() {
		return ownerUUID;
	}
	
	@Override
	public String getOwnerName() {
		return ownerName;
	}
	
	@Override
	public void setVictim(String victim) {
		this.victim = victim;
	}
	
	@Override
	public String getVictim() {
		return victim;
	}
	
	@Override
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
		
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		if (ownerUUID != null) compound.setUniqueId("owner", ownerUUID);
		if (victim != null) compound.setString("victim", victim);
		return compound;
	}
	
	@Override
	public void update() {
		if (destroy) {
			ticks ++;
			if (ticks >= 5) {
				world.destroyBlock(pos, true);
				world.destroyBlock(pos.add(0, -1, 0), true);
				world.destroyBlock(pos.add(0, -2, 0), true);
			}
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
		
		if (!checkMultiBlockValid()) {
			victimEntity.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 2, 0));
			victimEntity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20 * 120, 3));
			victimEntity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20 * 120, 0));
			victimEntity.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 20 * 120, 1));
			
			getOwner().addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 2, 0));
			getOwner().addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20 * 120, 3));
			getOwner().addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20 * 120, 0));
			getOwner().addPotionEffect(new PotionEffect(MobEffects.HUNGER, 20 * 120, 1));
			
			world.setBlockToAir(pos);
			world.setBlockToAir(pos.add(0, -1, 0));
			world.setBlockToAir(pos.add(0, -2, 0));
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
	public static void onBlockBreak(LivingDestroyBlockEvent e) {
		
		if (e.getEntity().world.getTileEntity(e.getPos()) instanceof HeadcrumbsNithingTE) {
			
			HeadcrumbsNithingTE te = Utils.getTileEntity(e.getEntity().world, e.getPos(), HeadcrumbsNithingTE.class);
			if (!e.getEntity().getUniqueID().equals(te.getOwnerUUID())) {
				e.setCanceled(true);
			}
		} else if (Utils.containsOreDict(
				new ItemStack(e.getState().getBlock(), 1, e.getState().getBlock().getMetaFromState(e.getState())), "fenceWood")) {
			HeadcrumbsNithingTE te = Utils.getTileEntity(e.getEntity().world, e.getPos().add(0, 1, 0), HeadcrumbsNithingTE.class);
			if (te == null) {
				te = Utils.getTileEntity(e.getEntity().world, e.getPos().add(0, 2, 0), HeadcrumbsNithingTE.class);
				if (te != null) {
					if (te.checkMultiBlockValid() && !e.getEntity().getUniqueID().equals(te.ownerUUID)) {
						e.setCanceled(true);
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerBlockBreak(BlockEvent.BreakEvent e) {
		if (e.getWorld().getTileEntity(e.getPos()) instanceof HeadcrumbsNithingTE) {
			
			HeadcrumbsNithingTE te = (HeadcrumbsNithingTE) e.getWorld().getTileEntity(e.getPos());
			if (te.ownerUUID != null 
					&& !e.getPlayer().getUniqueID().equals(te.ownerUUID)
					&& !e.getPlayer().isCreative()) {
				e.setCanceled(true);
			}
		} else if (Utils.containsOreDict(
				new ItemStack(e.getState().getBlock(), 1, e.getState().getBlock().getMetaFromState(e.getState())), "fenceWood")) {
			HeadcrumbsNithingTE te = Utils.getTileEntity(e.getWorld(), e.getPos().add(0, 1, 0), HeadcrumbsNithingTE.class);
			if (te == null) {
				te = Utils.getTileEntity(e.getWorld(), e.getPos().add(0, 2, 0), HeadcrumbsNithingTE.class);
				if (te != null) {
					if (te.checkMultiBlockValid() 
							&& te.ownerUUID != null
							&& !e.getPlayer().getUniqueID().equals(te.ownerUUID)
							&& !e.getPlayer().isCreative()) {
						e.setCanceled(true);
					}
				}
			}
		}
	}

	@Override
	public void destroy() {
		destroy = true;
	}
	
	@Override
	public boolean checkMultiBlockValid() {
		IBlockState state1 = world.getBlockState(pos.add(0, -1, 0));
		IBlockState state2 = world.getBlockState(pos.add(0, -2, 0));
		return Utils.containsOreDict(new ItemStack(state1.getBlock(), 1, state1.getBlock().getMetaFromState(state1)), "fenceWood")
				&& Utils.containsOreDict(new ItemStack(state2.getBlock(), 1, state2.getBlock().getMetaFromState(state2)), "fenceWood");
	}
	
}
