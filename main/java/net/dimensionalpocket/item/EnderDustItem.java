
package net.dimensionalpocket.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import net.dimensionalpocket.procedures.EnderDustRightClickedOnBlockProcedure;
import net.dimensionalpocket.init.DimensionalpocketModTabs;

public class EnderDustItem extends Item {
	public EnderDustItem() {
		super(new Item.Properties().tab(DimensionalpocketModTabs.TAB_DIMENSIONAL_POCKETS).stacksTo(64).rarity(Rarity.COMMON));
		setRegistryName("ender_dust");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		InteractionResult retval = super.useOn(context);
		EnderDustRightClickedOnBlockProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(),
				context.getClickedPos().getZ(), context.getPlayer());
		return retval;
	}
}
