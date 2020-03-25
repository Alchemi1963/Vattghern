package me.alchemi.vattghern.proxies;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.holders.BlockHolder;
import me.alchemi.vattghern.holders.EntityHolder;
import me.alchemi.vattghern.objects.Utils;
import me.alchemi.vattghern.objects.blocks.BlockHorseHead;
import me.alchemi.vattghern.objects.blocks.BlockNithing;
import me.alchemi.vattghern.objects.blocks.BlockPhantom;
import me.alchemi.vattghern.objects.items.ItemHorseHead;
import me.alchemi.vattghern.objects.items.ItemMedallion;
import me.alchemi.vattghern.objects.recipes.RecipeNithing;
import me.alchemi.vattghern.objects.recipes.RecipeNithing2;
import me.alchemi.vattghern.objects.tileentities.TileEntityNithing;
import me.alchemi.vattghern.objects.tileentities.TileEntityPhantom;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumFacing;
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
		e.getRegistry().registerAll(new BlockNithing(), new BlockHorseHead(), new BlockPhantom(BlockNithing.AABB, EnumFacing.UP));
		
		GameRegistry.registerTileEntity(TileEntityNithing.class, new ResourceLocation(Vattghern.MOD_ID, "nithing"));
		GameRegistry.registerTileEntity(TileEntityPhantom.class, new ResourceLocation(Vattghern.MOD_ID, "phantomblock"));
	}
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> e) {
		e.getRegistry().registerAll( 
				new ItemMedallion(),
				Utils.setup(new ItemBlock(BlockHolder.NITHING), "nithing"),
				new ItemHorseHead());
		
		for (int i = 0; i < 11; i++) {
			OreDictionary.registerOre("headHorse", new ItemStack(BlockHolder.HORSE_HEAD, 1, i));
			OreDictionary.registerOre("skullHorse", new ItemStack(BlockHolder.HORSE_HEAD, 1, i));
		}

	}
	
	@SubscribeEvent
	public static void onRecipeRegister(RegistryEvent.Register<IRecipe> e) {
		e.getRegistry().registerAll(new RecipeNithing(), new RecipeNithing2());
	}
	
	public void preInit(FMLPreInitializationEvent e) {
		EntityHolder.init();
	}
	
	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Vattghern.instance, new GuiProxy());
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		
	}
}
