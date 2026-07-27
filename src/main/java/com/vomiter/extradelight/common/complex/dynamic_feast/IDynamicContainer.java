package com.vomiter.extradelight.common.complex.dynamic_feast;

import com.vomiter.extradelight.DataComponents;
import net.minecraft.world.item.ItemStack;

public interface IDynamicContainer {
    default void setContainer(ItemStack feast, ItemStack container){
        DataComponents.setStack(feast, DataComponents.CONTAINER, container);
    }
}
