
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.dimensionalpockets.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.dimensionalpockets.block.entity.StoneEnchantingtableBlockEntity;
import net.mcreator.dimensionalpockets.block.entity.DimensionalPortalBlockEntity;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimensionalpocketsModBlockEntities {
	private static final List<BlockEntityType<?>> REGISTRY = new ArrayList<>();
	public static final BlockEntityType<?> STONE_ENCHANTINGTABLE = register("dimensionalpockets:stone_enchantingtable",
			DimensionalpocketsModBlocks.STONE_ENCHANTINGTABLE, StoneEnchantingtableBlockEntity::new);
	public static final BlockEntityType<?> DIMENSIONAL_PORTAL = register("dimensionalpockets:dimensional_portal",
			DimensionalpocketsModBlocks.DIMENSIONAL_PORTAL, DimensionalPortalBlockEntity::new);

	private static BlockEntityType<?> register(String registryname, Block block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		BlockEntityType<?> blockEntityType = BlockEntityType.Builder.of(supplier, block).build(null).setRegistryName(registryname);
		REGISTRY.add(blockEntityType);
		return blockEntityType;
	}

	@SubscribeEvent
	public static void registerTileEntity(RegistryEvent.Register<BlockEntityType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new BlockEntityType[0]));
	}
}
