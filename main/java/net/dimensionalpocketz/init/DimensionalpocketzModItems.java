
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.dimensionalpocketz.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import net.dimensionalpocketz.item.StoneDiskItem;
import net.dimensionalpocketz.item.PocketDimensionItemItem;
import net.dimensionalpocketz.item.LargePocketDimensionItemItem;
import net.dimensionalpocketz.item.EnderDustItem;
import net.dimensionalpocketz.item.DiamondPestleItem;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimensionalpocketzModItems {
	private static final List<Item> REGISTRY = new ArrayList<>();
	public static final Item POCKET_DIMENSION_ITEM = register(new PocketDimensionItemItem());
	public static final Item STONE_DISK = register(new StoneDiskItem());
	public static final Item STONE_ENCHANTINGTABLE = register(DimensionalpocketzModBlocks.STONE_ENCHANTINGTABLE,
			DimensionalpocketzModTabs.TAB_DIMENSIONAL_POCKETS);
	public static final Item ENDER_DUST = register(new EnderDustItem());
	public static final Item ENDER_STONE = register(DimensionalpocketzModBlocks.ENDER_STONE, DimensionalpocketzModTabs.TAB_DIMENSIONAL_POCKETS);
	public static final Item DIAMOND_PESTLE = register(new DiamondPestleItem());
	public static final Item OVERWORLD_PORTAL = register(DimensionalpocketzModBlocks.OVERWORLD_PORTAL, null);
	public static final Item DIMENSIONAL_PORTAL = register(DimensionalpocketzModBlocks.DIMENSIONAL_PORTAL, null);
	public static final Item DIMENSIONAL_FIRE = register(DimensionalpocketzModBlocks.DIMENSIONAL_FIRE, null);
	public static final Item LARGE_POCKET_DIMENSION_ITEM = register(new LargePocketDimensionItemItem());

	private static Item register(Item item) {
		REGISTRY.add(item);
		return item;
	}

	private static Item register(Block block, CreativeModeTab tab) {
		return register(new BlockItem(block, new Item.Properties().tab(tab)).setRegistryName(block.getRegistryName()));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new Item[0]));
	}
}
