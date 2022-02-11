
package net.dimensionalpocketz.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

import net.dimensionalpocketz.procedures.PocketDimensionUseProcedure;
import net.dimensionalpocketz.procedures.PocketDimensionItemRightClickedOnBlockProcedure;
import net.dimensionalpocketz.procedures.PocketDimensionItemMakeItemGlowProcedure;
import net.dimensionalpocketz.init.DimensionalpocketzModTabs;

import java.util.List;

public class PocketDimensionItemItem extends Item {
	public PocketDimensionItemItem() {
		super(new Item.Properties().tab(DimensionalpocketzModTabs.TAB_DIMENSIONAL_POCKETS).stacksTo(1).fireResistant().rarity(Rarity.RARE));
		setRegistryName("pocket_dimension_item");
	}

	@Override
	public boolean hasCraftingRemainingItem() {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemstack) {
		return new ItemStack(this);
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 100;
	}

	@Override
	public boolean isFoil(ItemStack itemstack) {
		Player entity = Minecraft.getInstance().player;
		Level world = entity.level;
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		return PocketDimensionItemMakeItemGlowProcedure.execute(itemstack);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TextComponent("Teleports you to a pocket dimension."));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		ItemStack itemstack = ar.getObject();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		PocketDimensionUseProcedure.execute(world, x, y, z, entity, itemstack);
		return ar;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		InteractionResult retval = super.useOn(context);
		PocketDimensionItemRightClickedOnBlockProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(),
				context.getClickedPos().getZ(), context.getPlayer());
		return retval;
	}
}
