package me.alchemi.vattghern.eventhandlers;

import java.util.Arrays;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.compat.TinkersCompat;
import me.alchemi.vattghern.holders.BlockHolder;
import me.alchemi.vattghern.holders.ItemHolder;
import me.alchemi.vattghern.objects.blocks.BlockHorseHead;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.TConstruct;

@EventBusSubscriber(modid = Vattghern.MOD_ID)
public class LootTableHandler {

	@SubscribeEvent
	public static void onDrop(LivingDropsEvent e) {
		
		if (e.getEntityLiving() instanceof EntityHorse
				&& e.getSource().getTrueSource() instanceof EntityPlayer) {
			
			float chanceMod = 1;
			
			EntityPlayer player = (EntityPlayer) e.getSource().getTrueSource();
			
			if (e.getSource().damageType.equals("player")) {
				ItemStack hand = player.getHeldItemMainhand();
				if (Loader.isModLoaded("tconstruct")
						&& TinkersCompat.INSTANCE.isItemSword(hand)) {
					
					return;
					
				} else if (hand.isItemEnchanted()) {
					for (NBTBase tag : player.getHeldItemMainhand().getEnchantmentTagList()) {
						NBTTagCompound tagCompound = (NBTTagCompound)tag;
						if (tagCompound.getShort("id") == 21) {
							chanceMod = tagCompound.getShort("lvl")/1.5F;
							break;
						}
					}
				}	
			}
			
			if (e.getEntityLiving().world.rand.nextDouble() <= (20.0F * chanceMod)/100.0F) {
				e.getDrops().add(new EntityItem(e.getEntity().world, e.getEntity().posX, e.getEntity().posY, e.getEntity().posZ,
						BlockHorseHead.getHeadItem(e.getEntityLiving())));
			}
		}
		
	}
	
}
