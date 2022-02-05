
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.dimensionalpocket.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class DimensionalpocketModTabs {
	public static CreativeModeTab TAB_DIMENSIONAL_POCKETS;

	public static void load() {
		TAB_DIMENSIONAL_POCKETS = new CreativeModeTab("tabdimensional_pockets") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(DimensionalpocketModItems.POCKET_DIMENSION_ITEM);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
