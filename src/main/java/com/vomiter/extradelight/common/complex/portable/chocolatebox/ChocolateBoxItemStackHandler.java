package com.vomiter.extradelight.common.complex.portable.chocolatebox;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ChocolateBoxItemStackHandler extends ItemStackHandler {
	public ChocolateBoxItemStackHandler(int size) {
        stacks = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    public ChocolateBoxItemStackHandler(NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }
    
	public NonNullList<ItemStack> getStacks() {
		return this.stacks;
	}
}
