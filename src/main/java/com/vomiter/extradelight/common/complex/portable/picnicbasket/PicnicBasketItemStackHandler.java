package com.vomiter.extradelight.common.complex.portable.picnicbasket;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class PicnicBasketItemStackHandler extends ItemStackHandler {
	public PicnicBasketItemStackHandler(int size) {
        stacks = NonNullList.withSize(size, ItemStack.EMPTY);
    }

	public PicnicBasketItemStackHandler(NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }

	public NonNullList<ItemStack> getStacks() {
		return this.stacks;
	}
}
