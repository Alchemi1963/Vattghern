package me.alchemi.vattghern.objects;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.client.entities.renderers.RenderLeshen;
import me.alchemi.vattghern.common.entities.EntityLeshen;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {

	public static void init() {
		int id = 1;
		EntityRegistry.registerModEntity(new ResourceLocation(Vattghern.MOD_ID, "leshen"), EntityLeshen.class, "Leshen", id++, Vattghern.instance, 64, 3, true);
		
		EntityRegistry.addSpawn(EntityLeshen.class, 2, 0, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST, Biomes.COLD_TAIGA, Biomes.COLD_TAIGA_HILLS, Biomes.REDWOOD_TAIGA, Biomes.REDWOOD_TAIGA_HILLS, Biomes.TAIGA, Biomes.TAIGA_HILLS);
		
		LootTableList.register(EntityLeshen.LOOT);
	}

	public static void registerModels() {
		RenderingRegistry.registerEntityRenderingHandler(EntityLeshen.class, RenderLeshen.FACTORY);
	}
	
}
