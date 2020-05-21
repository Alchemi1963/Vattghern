package me.alchemi.vattghern.proxies;

import me.alchemi.vattghern.objects.ModBlocks;
import me.alchemi.vattghern.objects.ModCrops;
import me.alchemi.vattghern.objects.ModEntities;
import me.alchemi.vattghern.objects.ModItems;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		
//		OBJLoader.INSTANCE.addDomain(Vattghern.MOD_ID);
	}
	
	@SubscribeEvent
	public static void modelRegister(ModelRegistryEvent e) {
		ModEntities.registerModels();
		ModBlocks.registerModels();
		ModItems.registerModels();
		ModCrops.registerModels();
	}
	
}
