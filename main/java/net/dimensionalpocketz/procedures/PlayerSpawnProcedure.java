package net.dimensionalpocketz.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.dimensionalpocketz.network.DimensionalpocketzModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerSpawnProcedure {
	@SubscribeEvent
	public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
		Entity entity = event.getPlayer();
		execute(event, entity);
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player) {
			ItemStack _setstack = ((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new DimensionalpocketzModVariables.PlayerVariables())).DimensionalBag);
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		{
			ItemStack _setval = (ItemStack.EMPTY);
			entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.DimensionalBag = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
