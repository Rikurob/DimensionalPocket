
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.dimensionalpocket.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import net.dimensionalpocket.block.StoneEnchantingtableBlock;
import net.dimensionalpocket.block.OverworldPortalBlock;
import net.dimensionalpocket.block.EnderStoneBlock;
import net.dimensionalpocket.block.DimensionalPortalBlock;
import net.dimensionalpocket.block.DimensionalFireBlock;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimensionalpocketModBlocks {
	private static final List<Block> REGISTRY = new ArrayList<>();
	public static final Block STONE_ENCHANTINGTABLE = register(new StoneEnchantingtableBlock());
	public static final Block ENDER_STONE = register(new EnderStoneBlock());
	public static final Block OVERWORLD_PORTAL = register(new OverworldPortalBlock());
	public static final Block DIMENSIONAL_PORTAL = register(new DimensionalPortalBlock());
	public static final Block DIMENSIONAL_FIRE = register(new DimensionalFireBlock());

	private static Block register(Block block) {
		REGISTRY.add(block);
		return block;
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new Block[0]));
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			OverworldPortalBlock.registerRenderLayer();
			DimensionalPortalBlock.registerRenderLayer();
			DimensionalFireBlock.registerRenderLayer();
		}
	}
}
