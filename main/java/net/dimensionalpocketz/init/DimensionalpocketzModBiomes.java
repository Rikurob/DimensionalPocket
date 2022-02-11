
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.dimensionalpocketz.init;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.resources.ResourceLocation;

import net.dimensionalpocketz.world.biome.PocketDimensionBiomeBiome;
import net.dimensionalpocketz.DimensionalpocketzMod;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimensionalpocketzModBiomes {
	private static final List<Biome> REGISTRY = new ArrayList<>();
	public static Biome POCKET_DIMENSION_BIOME = register("pocket_dimension_biome", PocketDimensionBiomeBiome.createBiome());

	private static Biome register(String registryname, Biome biome) {
		REGISTRY.add(biome.setRegistryName(new ResourceLocation(DimensionalpocketzMod.MODID, registryname)));
		return biome;
	}

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new Biome[0]));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			PocketDimensionBiomeBiome.init();
		});
	}
}
