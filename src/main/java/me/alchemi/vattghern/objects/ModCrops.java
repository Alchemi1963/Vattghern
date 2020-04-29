package me.alchemi.vattghern.objects;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import me.alchemi.vattghern.common.crops.Crop;
import me.alchemi.vattghern.common.crops.CropBeggartick;
import me.alchemi.vattghern.common.crops.CropCelandine;
import me.alchemi.vattghern.common.crops.ItemSeed;
import net.minecraft.item.Item;

public class ModCrops {

	private static Set<Crop> registry = new HashSet<Crop>(); 
	
	public static final Crop CELANDINE = new CropCelandine();
	public static final Crop BEGGARTICK = new CropBeggartick();
	//Arenaria
	//Balisse
	//Berbercane https://en.wikipedia.org/wiki/Berberis_vulgaris
	//bison grass --> vodka
	//bryonia
	//buckthorn
	//crow's eye
	//fools parsley
	//ginatia
	//han
	//hellebore
	//honeysuckle
	//hop
	//hornwort --> water
	//mistletoe
	//moleyarrow
	//nostrix --> on walls etc
	//pringrape
	//ranogrin
	//ribleaf
	//verbena
	//white myrtle
	//wolfsbane  --> wolfs avoid it :)
	
	public static void register(Crop crop) {
		if (!registry.contains(crop)) {
			registry.add(crop);
		}
	}
	
	public static ItemSeed[] getSeeds() {
		return registry.stream().map(C -> C.getSeeds()).collect(Collectors.toSet()).toArray(new ItemSeed[registry.size()]);
	}
	
	public static Item[] getCrops() {
		return registry.stream().map(C -> C.getCrops()).collect(Collectors.toSet()).toArray(new Item[registry.size()]);
	}
	
	public static Crop[] getCropBlocks() {
		return registry.toArray(new Crop[registry.size()]);
	}
	
	public static void registerModels() {
		for (Crop c : registry) {
			c.initModel();
			c.getSeeds().initModel();
			c.getCrops().initModel();
		}
	}
	
}
