package me.alchemi.vattghern.proxies;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.holders.BlockHolder;
import me.alchemi.vattghern.holders.EntityHolder;
import me.alchemi.vattghern.holders.ItemHolder;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		
		OBJLoader.INSTANCE.addDomain(Vattghern.MOD_ID);
	}
	
	@SubscribeEvent
	public static void modelRegister(ModelRegistryEvent e) {
		EntityHolder.registerModels();
		BlockHolder.registerModels();
		ItemHolder.registerModels();
	}
	
}
