
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.dimensionalpocket.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.dimensionalpocket.client.gui.StoneEnchantmentGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DimensionalpocketModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(DimensionalpocketModMenus.STONE_ENCHANTMENT_GUI, StoneEnchantmentGUIScreen::new);
		});
	}
}
