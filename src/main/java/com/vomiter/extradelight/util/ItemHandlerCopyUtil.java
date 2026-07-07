package com.vomiter.extradelight.util;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public final class ItemHandlerCopyUtil {

    public static boolean copyInto(IItemHandler source, IItemHandler target) {
        boolean allInserted = true;

        for (int slot = 0; slot < source.getSlots(); slot++) {
            ItemStack sourceStack = source.getStackInSlot(slot);

            if (sourceStack.isEmpty()) {
                continue;
            }

            ItemStack remaining = ItemHandlerHelper.insertItemStacked(
                    target,
                    sourceStack.copy(),
                    false
            );

            if (!remaining.isEmpty()) {
                allInserted = false;
            }
        }

        return allInserted;
    }

    private ItemHandlerCopyUtil() {
    }
}