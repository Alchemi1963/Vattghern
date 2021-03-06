package me.alchemi.vattghern.objects;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import me.alchemi.vattghern.common.crops.Crop;
import me.alchemi.vattghern.common.crops.CropArenaria;
import me.alchemi.vattghern.common.crops.CropBalisse;
import me.alchemi.vattghern.common.crops.CropBeggartick;
import me.alchemi.vattghern.common.crops.CropBerbercane;
import me.alchemi.vattghern.common.crops.CropBisonGrass;
import me.alchemi.vattghern.common.crops.CropBryonia;
import me.alchemi.vattghern.common.crops.CropCelandine;
import me.alchemi.vattghern.common.crops.ItemSeed;
import net.minecraft.item.Item;

public class ModCrops {

	private static Set<Crop> registry = new HashSet<Crop>(); 
	
	public static final Crop CELANDINE = new CropCelandine();
	public static final Crop BEGGARTICK = new CropBeggartick();
	public static final Crop ARENARIA = new CropArenaria();
	public static final Crop BALISSE = new CropBalisse();
	public static final Crop BERBERCANE = new CropBerbercane(); //https://en.wikipedia.org/wiki/Berberis_vulgaris
	public static final Crop BISON_GRASS = new CropBisonGrass(); //vodka
	public static final Crop BRYONIA = new CropBryonia();
//	public static final Crop BUCKTHORN = new CropBuckthorn();
//	public static final Crop CROWS_EYE = new CropCrowEye();
//	public static final Crop FOOLS_PARSLEY = new CropFoolsParsley();
//	public static final Crop GINATIA = new CropGinatia();
//	public static final Crop HAN = new CropHan();
//	public static final Crop HELLEBORE = new CropHellebore();
//	public static final Crop HONEYSUCKLE = new CropHoneysuckle();
//	public static final Crop HOP = new CropHop();
//	public static final Crop HORNWORT = new CropHornwort();//water
//	public static final Crop MISTLETOE = new CropMistletoe();
//	public static final Crop MOLEYARROW = new CropMoleyarrow();
//	public static final Crop NOSTRIX = new CropNostrix(); //on walls etc
//	public static final Crop PRINGRAPE = new CropPringrape();
//	public static final Crop RANOGRIN = new CropRanogrin();
//	public static final Crop RIBLEAF = new CropRibleaf();
//	public static final Crop VERBENA = new CropVerbena();
//	public static final Crop WHITE_MYRTLE = new CropWhiteMyrtle();
//	public static final Crop WOLFSBANE = new CropWolfsbane(); //wolfs avoid it :)
	
	public static void register(Crop crop) {
		if (!registry.contains(crop)) {
			registry.add(crop);
		}
		
	}
	
	public static ItemSeed[] getSeeds() {
		return registry.parallelStream().map(C -> C.getSeeds()).collect(Collectors.toSet()).toArray(new ItemSeed[registry.size()]);
	}
	
	public static Item[] getCrops() {
		Set<Item> itemSet = registry.parallelStream().filter(Crop::hasCrops).map(Crop::getCrops).collect(Collectors.toSet());
		return itemSet.toArray(new Item[itemSet.size()]);
	}
	
	public static Crop[] getCropBlocks() {
		return registry.toArray(new Crop[registry.size()]);
	}
	
	public static int size() {
		return registry.size();
	}
	public static void registerModels() {
		for (Crop c : registry) {
			c.initModel();
			c.getSeeds().initModel();
			if (c.hasCrops()) c.getCrops().initModel();
		}
	}
	
}
