
package net.dimensionalpocket.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.dimensionalpocket.init.DimensionalpocketModTabs;

public class DiamondPestleItem extends Item {
	public DiamondPestleItem() {
		super(new Item.Properties().tab(DimensionalpocketModTabs.TAB_DIMENSIONAL_POCKETS).stacksTo(1).rarity(Rarity.COMMON));
		setRegistryName("diamond_pestle");
	}

	@Override
	public boolean hasCraftingRemainingItem() {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemstack) {
		return new ItemStack(this);
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
