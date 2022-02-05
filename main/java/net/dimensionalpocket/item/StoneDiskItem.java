
package net.dimensionalpocket.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.dimensionalpocket.init.DimensionalpocketModTabs;

public class StoneDiskItem extends Item {
	public StoneDiskItem() {
		super(new Item.Properties().tab(DimensionalpocketModTabs.TAB_DIMENSIONAL_POCKETS).stacksTo(64).rarity(Rarity.COMMON));
		setRegistryName("stone_disk");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
