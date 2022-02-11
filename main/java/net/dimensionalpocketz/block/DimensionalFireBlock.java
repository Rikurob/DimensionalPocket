
package net.dimensionalpocketz.block;

import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import net.dimensionalpocketz.init.DimensionalpocketzModBlocks;

import java.util.List;
import java.util.Collections;

public class DimensionalFireBlock extends FallingBlock {
	public DimensionalFireBlock() {
		super(BlockBehaviour.Properties.of(Material.FIRE)
				.sound(new ForgeSoundType(1.0f, 1.0f, () -> new SoundEvent(new ResourceLocation("block.fire.extinguish")),
						() -> new SoundEvent(new ResourceLocation("block.fire.ambient")),
						() -> new SoundEvent(new ResourceLocation("block.fire.ambient")),
						() -> new SoundEvent(new ResourceLocation("block.fire.ambient")),
						() -> new SoundEvent(new ResourceLocation("block.fire.ambient"))))
				.strength(1f, 10f).lightLevel(s -> 3).noCollission().noOcclusion().hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true).isRedstoneConductor((bs, br, bp) -> false));
		setRegistryName("dimensional_fire");
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return context.getItemInHand().getItem() != this.asItem();
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderLayer() {
		ItemBlockRenderTypes.setRenderLayer(DimensionalpocketzModBlocks.DIMENSIONAL_FIRE, renderType -> renderType == RenderType.translucent());
	}

}
