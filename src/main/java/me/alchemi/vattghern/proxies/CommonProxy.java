package me.alchemi.vattghern.proxies;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.common.blocks.BlockBrazier;
import me.alchemi.vattghern.common.blocks.BlockHorseHead;
import me.alchemi.vattghern.common.blocks.ghosts.CropBryoniaTop;
import me.alchemi.vattghern.common.items.ItemBrazier;
import me.alchemi.vattghern.common.items.ItemCarvingKnife;
import me.alchemi.vattghern.common.items.ItemHorseHead;
import me.alchemi.vattghern.common.items.ItemMedallion;
import me.alchemi.vattghern.common.tileentities.TileBrazier;
import me.alchemi.vattghern.common.tileentities.TileNithing;
import me.alchemi.vattghern.objects.ModBlocks;
import me.alchemi.vattghern.objects.ModCrops;
import me.alchemi.vattghern.objects.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@EventBusSubscriber
public class CommonProxy {

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> e) {
		
		e.getRegistry().registerAll(new BlockBrazier(), new BlockHorseHead(), new CropBryoniaTop());
		e.getRegistry().registerAll(ModCrops.getCropBlocks());
		
		GameRegistry.registerTileEntity(TileNithing.class, new ResourceLocation(Vattghern.MOD_ID, "nithing"));
		GameRegistry.registerTileEntity(TileBrazier.class, new ResourceLocation(Vattghern.MOD_ID, "brazier"));
		
	}
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> e) {
		e.getRegistry().registerAll( 
				new ItemMedallion(),
				new ItemHorseHead(),
				new ItemCarvingKnife(),
				new ItemBrazier());
		
		e.getRegistry().registerAll(ModCrops.getCrops());
		e.getRegistry().registerAll(ModCrops.getSeeds());
		
		for (int i = 0; i < 11; i++) {
			OreDictionary.registerOre("headHorse", new ItemStack(ModBlocks.HORSE_HEAD, 1, i));
			OreDictionary.registerOre("skullHorse", new ItemStack(ModBlocks.HORSE_HEAD, 1, i));
		}
	}
	
	public void preInit(FMLPreInitializationEvent e) {
		ModEntities.init();
	}
	
	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Vattghern.instance, new GuiProxy());
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		
	}
}
