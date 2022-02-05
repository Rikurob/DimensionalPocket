package net.dimensionalpocket.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.dimensionalpocket.init.DimensionalpocketModItems;
import net.dimensionalpocket.init.DimensionalpocketModBlocks;

public class EnderDustRightClickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.FIRE) {
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(DimensionalpocketModItems.ENDER_DUST);
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), DimensionalpocketModBlocks.DIMENSIONAL_FIRE.defaultBlockState(), 3);
		} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == DimensionalpocketModBlocks.DIMENSIONAL_FIRE
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.AIR) {
			if ((world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE
					&& (world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE
					&& (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE
					&& (world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE
					&& (world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) z))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE
					&& (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE) {
				world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(DimensionalpocketModItems.ENDER_DUST);
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
							_player.inventoryMenu.getCraftSlots());
				}
				world.setBlock(new BlockPos((int) x, (int) y, (int) z), DimensionalpocketModBlocks.OVERWORLD_PORTAL.defaultBlockState(), 3);
				world.setBlock(new BlockPos((int) x, (int) (y + 1), (int) z), DimensionalpocketModBlocks.OVERWORLD_PORTAL.defaultBlockState(), 3);
				{
					Direction _dir = Direction.WEST;
					BlockPos _pos = new BlockPos((int) x, (int) y, (int) z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
				{
					Direction _dir = Direction.WEST;
					BlockPos _pos = new BlockPos((int) x, (int) (y + 1), (int) z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE
					&& (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE
					&& (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE
					&& (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE
					&& (world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) z))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE
					&& (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == DimensionalpocketModBlocks.ENDER_STONE) {
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(DimensionalpocketModItems.ENDER_DUST);
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
							_player.inventoryMenu.getCraftSlots());
				}
				world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
				world.setBlock(new BlockPos((int) x, (int) y, (int) z), DimensionalpocketModBlocks.OVERWORLD_PORTAL.defaultBlockState(), 3);
				world.setBlock(new BlockPos((int) x, (int) (y + 1), (int) z), DimensionalpocketModBlocks.OVERWORLD_PORTAL.defaultBlockState(), 3);
				{
					Direction _dir = Direction.NORTH;
					BlockPos _pos = new BlockPos((int) x, (int) y, (int) z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
				{
					Direction _dir = Direction.NORTH;
					BlockPos _pos = new BlockPos((int) x, (int) (y + 1), (int) z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			}
		}
	}
}
