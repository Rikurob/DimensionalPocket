package net.mcreator.dimensionalpockets.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.dimensionalpockets.init.DimensionalpocketsModBlocks;

public class EnderDustRightClickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.FIRE) {
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), DimensionalpocketsModBlocks.DIMENSIONAL_FIRE.defaultBlockState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == DimensionalpocketsModBlocks.DIMENSIONAL_FIRE) {
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), DimensionalpocketsModBlocks.OVERWORLD_PORTAL.defaultBlockState(), 3);
			world.setBlock(new BlockPos((int) x, (int) (y + 1), (int) z), DimensionalpocketsModBlocks.OVERWORLD_PORTAL.defaultBlockState(), 3);
		}
	}
}
