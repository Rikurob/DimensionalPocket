package net.dimensionalpocketz.procedures;

import net.minecraft.world.item.ItemStack;

public class PocketDimensionItemMakeItemGlowProcedure {
	public static boolean execute(ItemStack itemstack) {
		boolean value = false;
		if (itemstack.getOrCreateTag().getBoolean("Bound")) {
			value = true;
		} else {
			value = false;
		}
		return value;
	}
}
