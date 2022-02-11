
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.dimensionalpocketz.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimensionalpocketzModGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> POCKETADVENTURETOSURVIVAL = GameRules.register("pocketAdventuretoSurvival",
			GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));
}
