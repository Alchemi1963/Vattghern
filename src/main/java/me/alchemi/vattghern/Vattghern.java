package me.alchemi.vattghern;

import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import me.alchemi.vattghern.common.crops.Crop;
import me.alchemi.vattghern.common.crops.CropBeggartick;
import me.alchemi.vattghern.common.crops.CropCelandine;
import me.alchemi.vattghern.common.items.base.ItemBasic;
import me.alchemi.vattghern.compat.HeadcrumbsCompat;
import me.alchemi.vattghern.compat.TinkersCompat;
import me.alchemi.vattghern.objects.ModCrops;
import me.alchemi.vattghern.proxies.CommonProxy;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = Vattghern.MOD_ID, name = Vattghern.NAME, version = Vattghern.VERSION, dependencies = "after:headcrumbs")
public class Vattghern {
	
	public static final String MOD_ID = "vattghern";
	public static final String NAME = "Vatt'ghern";
	public static final String VERSION = "1.0.0";
	
	public static Logger LOGGER;
	
	@Instance
	public static Vattghern instance;
	
	public static void print(Object...objects) {
		String msg = "";
		for (Object o : objects) {
			if (o == null) msg += "null\t"; 
			else msg += o.toString() + "\t";
		}
		LOGGER.info(msg.trim());
	}
	
	@SidedProxy(clientSide = "me.alchemi.vattghern.proxies.ClientProxy", serverSide = "me.alchemi.vattghern.proxies.ServerProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		LOGGER = e.getModLog();
		proxy.preInit(e);
		
		if (Loader.isModLoaded("headcrumbs")) {
			HeadcrumbsCompat.init();
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		LOGGER.log(Level.INFO, "Hello there!");
		proxy.init(e);
		
		if (Loader.isModLoaded("tconstruct")) {
			TinkersCompat.init();
		}
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}
