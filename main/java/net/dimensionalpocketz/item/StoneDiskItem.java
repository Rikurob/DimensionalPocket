
package net.dimensionalpocketz.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.dimensionalpocketz.init.DimensionalpocketzModTabs;

public class StoneDiskItem extends Item {
	public StoneDiskItem() {
		super(new Item.Properties().tab(DimensionalpocketzModTabs.TAB_DIMENSIONAL_POCKETS).stacksTo(64).rarity(Rarity.COMMON));
		setRegistryName("stone_disk");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
