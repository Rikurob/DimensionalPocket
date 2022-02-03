package net.mcreator.dimensionalpockets.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;

import net.mcreator.dimensionalpockets.network.DimensionalpocketsModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DimensionalPocketStructureAdditionalGenerationConditionProcedure {
	@SubscribeEvent
	public static void onEntityTravelToDimension(EntityTravelToDimensionEvent event) {
		Entity entity = event.getEntity();
		execute(event, entity.level, entity);
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD) == (ResourceKey.create(Registry.DIMENSION_REGISTRY,
				new ResourceLocation("dimensionalpockets:pocket_dimension")))) {
			if ((entity.getCapability(DimensionalpocketsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new DimensionalpocketsModVariables.PlayerVariables())).pocketSpawn) {
				if (world instanceof ServerLevel _serverworld) {
					StructureTemplate template = _serverworld.getStructureManager()
							.getOrCreate(new ResourceLocation("dimensionalpockets", "dimensionalpockettype1"));
					if (template != null) {
						template.placeInWorld(_serverworld,
								new BlockPos(
										(int) ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag()
												.getDouble("pocketX") - 10),
										(int) 50,
										(int) ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag()
												.getDouble("pocketZ") - 10)),
								new BlockPos(
										(int) ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag()
												.getDouble("pocketX") - 10),
										(int) 50,
										(int) ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag()
												.getDouble("pocketZ") - 10)),
								new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
								_serverworld.random, 3);
					}
				}
				{
					boolean _setval = false;
					entity.getCapability(DimensionalpocketsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.pocketSpawn = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
