package net.dimensionalpocketz.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;

import net.dimensionalpocketz.network.DimensionalpocketzModVariables;
import net.dimensionalpocketz.init.DimensionalpocketzModItems;
import net.dimensionalpocketz.init.DimensionalpocketzModGameRules;

import java.util.Collections;

public class PocketDimensionUseProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (!itemstack.getOrCreateTag().getBoolean("Bound") && !((entity.level.dimension()) == (ResourceKey.create(Registry.DIMENSION_REGISTRY,
				new ResourceLocation("dimensionalpocketz:pocket_dimension"))))) {
			DimensionalpocketzModVariables.MapVariables
					.get(world).dimensionNumber = DimensionalpocketzModVariables.MapVariables.get(world).dimensionNumber + 1;
			DimensionalpocketzModVariables.MapVariables.get(world).syncData(world);
			itemstack.getOrCreateTag().putDouble("pocketID", DimensionalpocketzModVariables.MapVariables.get(world).dimensionNumber);
			{
				double _setval = x;
				entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.savedX = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = y;
				entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.savedY = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = z;
				entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.savedZ = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			itemstack.getOrCreateTag().putDouble("pocketY", 60);
			itemstack.getOrCreateTag().putBoolean("Bound", (true));
			itemstack.getOrCreateTag().putBoolean("Used", (true));
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
					.getItem() == DimensionalpocketzModItems.POCKET_DIMENSION_ITEM) {
				{
					boolean _setval = true;
					entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.pocketSpawn = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
					.getItem() == DimensionalpocketzModItems.LARGE_POCKET_DIMENSION_ITEM) {
				{
					boolean _setval = true;
					entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.pocketSpawnLarge = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (DimensionalpocketzModVariables.MapVariables.get(world).dimensionCounter <= 50) {
				DimensionalpocketzModVariables.MapVariables.get(world).pocketX = DimensionalpocketzModVariables.MapVariables.get(world).pocketX + 200;
				DimensionalpocketzModVariables.MapVariables.get(world).syncData(world);
				itemstack.getOrCreateTag().putDouble("pocketX", DimensionalpocketzModVariables.MapVariables.get(world).pocketX);
				itemstack.getOrCreateTag().putDouble("pocketZ", DimensionalpocketzModVariables.MapVariables.get(world).pocketZ);
				DimensionalpocketzModVariables.MapVariables
						.get(world).dimensionCounter = DimensionalpocketzModVariables.MapVariables.get(world).dimensionCounter + 1;
				DimensionalpocketzModVariables.MapVariables.get(world).syncData(world);
			} else {
				DimensionalpocketzModVariables.MapVariables.get(world).pocketZ = DimensionalpocketzModVariables.MapVariables.get(world).pocketZ + 200;
				DimensionalpocketzModVariables.MapVariables.get(world).syncData(world);
				itemstack.getOrCreateTag().putDouble("pocketX", DimensionalpocketzModVariables.MapVariables.get(world).pocketX);
				itemstack.getOrCreateTag().putDouble("pocketZ", DimensionalpocketzModVariables.MapVariables.get(world).pocketZ);
				DimensionalpocketzModVariables.MapVariables.get(world).dimensionCounter = 0;
				DimensionalpocketzModVariables.MapVariables.get(world).syncData(world);
			}
			if (entity instanceof ServerPlayer _player && !_player.level.isClientSide()) {
				ResourceKey<Level> destinationType = ResourceKey.create(Registry.DIMENSION_REGISTRY,
						new ResourceLocation("dimensionalpocketz:pocket_dimension"));
				if (_player.level.dimension() == destinationType)
					return;
				ServerLevel nextLevel = _player.server.getLevel(destinationType);
				if (nextLevel != null) {
					_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
					_player.teleportTo(nextLevel, nextLevel.getSharedSpawnPos().getX(), nextLevel.getSharedSpawnPos().getY() + 1,
							nextLevel.getSharedSpawnPos().getZ(), _player.getYRot(), _player.getXRot());
					_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
					for (MobEffectInstance effectinstance : _player.getActiveEffects())
						_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), effectinstance));
					_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
				}
			}
			{
				Entity _ent = entity;
				_ent.teleportTo((itemstack.getOrCreateTag().getDouble("pocketX")), 60, (itemstack.getOrCreateTag().getDouble("pocketZ")));
				if (_ent instanceof ServerPlayer _serverPlayer) {
					_serverPlayer.connection.teleport((itemstack.getOrCreateTag().getDouble("pocketX")), 60,
							(itemstack.getOrCreateTag().getDouble("pocketZ")), _ent.getYRot(), _ent.getXRot(), Collections.emptySet());
				}
			}
			new Object() {
				private int ticks = 0;
				private float waitTicks;
				private LevelAccessor world;

				public void start(LevelAccessor world, int waitTicks) {
					this.waitTicks = waitTicks;
					MinecraftForge.EVENT_BUS.register(this);
					this.world = world;
				}

				@SubscribeEvent
				public void tick(TickEvent.ServerTickEvent event) {
					if (event.phase == TickEvent.Phase.END) {
						this.ticks += 1;
						if (this.ticks >= this.waitTicks)
							run();
					}
				}

				private void run() {
					if (entity instanceof ServerPlayer _player && !_player.level.isClientSide()) {
						ResourceKey<Level> destinationType = Level.OVERWORLD;
						if (_player.level.dimension() == destinationType)
							return;
						ServerLevel nextLevel = _player.server.getLevel(destinationType);
						if (nextLevel != null) {
							_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
							_player.teleportTo(nextLevel, nextLevel.getSharedSpawnPos().getX(), nextLevel.getSharedSpawnPos().getY() + 1,
									nextLevel.getSharedSpawnPos().getZ(), _player.getYRot(), _player.getXRot());
							_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
							for (MobEffectInstance effectinstance : _player.getActiveEffects())
								_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), effectinstance));
							_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
						}
					}
					{
						Entity _ent = entity;
						_ent.teleportTo(
								((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedX),
								((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedY),
								((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedZ));
						if (_ent instanceof ServerPlayer _serverPlayer) {
							_serverPlayer.connection.teleport(
									((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedX),
									((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedY),
									((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedZ),
									_ent.getYRot(), _ent.getXRot(), Collections.emptySet());
						}
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, 2);
			new Object() {
				private int ticks = 0;
				private float waitTicks;
				private LevelAccessor world;

				public void start(LevelAccessor world, int waitTicks) {
					this.waitTicks = waitTicks;
					MinecraftForge.EVENT_BUS.register(this);
					this.world = world;
				}

				@SubscribeEvent
				public void tick(TickEvent.ServerTickEvent event) {
					if (event.phase == TickEvent.Phase.END) {
						this.ticks += 1;
						if (this.ticks >= this.waitTicks)
							run();
					}
				}

				private void run() {
					if (entity instanceof ServerPlayer _player && !_player.level.isClientSide()) {
						ResourceKey<Level> destinationType = ResourceKey.create(Registry.DIMENSION_REGISTRY,
								new ResourceLocation("dimensionalpocketz:pocket_dimension"));
						if (_player.level.dimension() == destinationType)
							return;
						ServerLevel nextLevel = _player.server.getLevel(destinationType);
						if (nextLevel != null) {
							_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
							_player.teleportTo(nextLevel, nextLevel.getSharedSpawnPos().getX(), nextLevel.getSharedSpawnPos().getY() + 1,
									nextLevel.getSharedSpawnPos().getZ(), _player.getYRot(), _player.getXRot());
							_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
							for (MobEffectInstance effectinstance : _player.getActiveEffects())
								_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), effectinstance));
							_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
						}
					}
					{
						Entity _ent = entity;
						_ent.teleportTo((itemstack.getOrCreateTag().getDouble("pocketX")), 60, (itemstack.getOrCreateTag().getDouble("pocketZ")));
						if (_ent instanceof ServerPlayer _serverPlayer) {
							_serverPlayer.connection.teleport((itemstack.getOrCreateTag().getDouble("pocketX")), 60,
									(itemstack.getOrCreateTag().getDouble("pocketZ")), _ent.getYRot(), _ent.getXRot(), Collections.emptySet());
						}
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, 2);
			if (world.getLevelData().getGameRules().getBoolean(DimensionalpocketzModGameRules.POCKETADVENTURETOSURVIVAL)) {
				if (entity instanceof ServerPlayer _player)
					_player.setGameMode(GameType.SURVIVAL);
			}
		} else {
			if (itemstack.getOrCreateTag().getBoolean("Bound") && (entity.level.dimension()) == (ResourceKey.create(Registry.DIMENSION_REGISTRY,
					new ResourceLocation("dimensionalpocketz:pocket_dimension")))) {
				if (entity instanceof ServerPlayer _player && !_player.level.isClientSide()) {
					ResourceKey<Level> destinationType = Level.OVERWORLD;
					if (_player.level.dimension() == destinationType)
						return;
					ServerLevel nextLevel = _player.server.getLevel(destinationType);
					if (nextLevel != null) {
						_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
						_player.teleportTo(nextLevel, nextLevel.getSharedSpawnPos().getX(), nextLevel.getSharedSpawnPos().getY() + 1,
								nextLevel.getSharedSpawnPos().getZ(), _player.getYRot(), _player.getXRot());
						_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
						for (MobEffectInstance effectinstance : _player.getActiveEffects())
							_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), effectinstance));
						_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
					}
				}
				{
					Entity _ent = entity;
					_ent.teleportTo(
							((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedX),
							((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedY),
							((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedZ));
					if (_ent instanceof ServerPlayer _serverPlayer) {
						_serverPlayer.connection.teleport(
								((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedX),
								((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedY),
								((entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new DimensionalpocketzModVariables.PlayerVariables())).savedZ),
								_ent.getYRot(), _ent.getXRot(), Collections.emptySet());
					}
				}
				if (world.getLevelData().getGameRules().getBoolean(DimensionalpocketzModGameRules.POCKETADVENTURETOSURVIVAL)) {
					if (entity instanceof ServerPlayer _player)
						_player.setGameMode(GameType.ADVENTURE);
				}
			} else if (itemstack.getOrCreateTag().getBoolean("Bound") && (entity.level.dimension()) == (Level.OVERWORLD)) {
				{
					double _setval = x;
					entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.savedX = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = y;
					entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.savedY = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = z;
					entity.getCapability(DimensionalpocketzModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.savedZ = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof ServerPlayer _player && !_player.level.isClientSide()) {
					ResourceKey<Level> destinationType = ResourceKey.create(Registry.DIMENSION_REGISTRY,
							new ResourceLocation("dimensionalpocketz:pocket_dimension"));
					if (_player.level.dimension() == destinationType)
						return;
					ServerLevel nextLevel = _player.server.getLevel(destinationType);
					if (nextLevel != null) {
						_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
						_player.teleportTo(nextLevel, nextLevel.getSharedSpawnPos().getX(), nextLevel.getSharedSpawnPos().getY() + 1,
								nextLevel.getSharedSpawnPos().getZ(), _player.getYRot(), _player.getXRot());
						_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
						for (MobEffectInstance effectinstance : _player.getActiveEffects())
							_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), effectinstance));
						_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
					}
				}
				{
					Entity _ent = entity;
					_ent.teleportTo((itemstack.getOrCreateTag().getDouble("pocketX")), 60, (itemstack.getOrCreateTag().getDouble("pocketZ")));
					if (_ent instanceof ServerPlayer _serverPlayer) {
						_serverPlayer.connection.teleport((itemstack.getOrCreateTag().getDouble("pocketX")), 60,
								(itemstack.getOrCreateTag().getDouble("pocketZ")), _ent.getYRot(), _ent.getXRot(), Collections.emptySet());
					}
				}
				if (world.getLevelData().getGameRules().getBoolean(DimensionalpocketzModGameRules.POCKETADVENTURETOSURVIVAL)) {
					if (entity instanceof ServerPlayer _player)
						_player.setGameMode(GameType.SURVIVAL);
				}
			} else {
				{
					Entity _ent = entity;
					if (!_ent.level.isClientSide() && _ent.getServer() != null)
						_ent.getServer().getCommands().performCommand(_ent.createCommandSourceStack().withSuppressedOutput().withPermission(4),
								"say I can't use this here!");
				}
			}
		}
	}
}
