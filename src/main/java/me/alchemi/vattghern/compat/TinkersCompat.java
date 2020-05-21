package me.alchemi.vattghern.compat;

import me.alchemi.vattghern.common.blocks.BlockHorseHead;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMule;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.passive.EntityZombieHorse;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.tools.SwordCore;

public class TinkersCompat implements ICompat {

	public static TinkersCompat INSTANCE;
	
	private TinkersCompat() {
		TinkerRegistry.registerHeadDrop(EntityHorse.class, EntityLivingBase -> BlockHorseHead.getHeadItem(EntityLivingBase));
		TinkerRegistry.registerHeadDrop(EntityDonkey.class, EntityLivingBase -> BlockHorseHead.getHeadItem(EntityLivingBase));
		TinkerRegistry.registerHeadDrop(EntityMule.class, EntityLivingBase -> BlockHorseHead.getHeadItem(EntityLivingBase));
		TinkerRegistry.registerHeadDrop(EntitySkeletonHorse.class, EntityLivingBase -> BlockHorseHead.getHeadItem(EntityLivingBase));
		TinkerRegistry.registerHeadDrop(EntityZombieHorse.class, EntityLivingBase -> BlockHorseHead.getHeadItem(EntityLivingBase));
	}
	
	public static void init() {
		INSTANCE = new TinkersCompat();
	}
	
	public boolean isItemSword(ItemStack tool) {
		return tool.getItem() instanceof SwordCore;
	}
	
	@Override
	public boolean hookEnabled() {
		return true;
	}

}
