package com.vomiter.extradelight.common.items.dynamic.api;

import java.util.List;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface IDynamic {
	List<ResourceLocation> getPieces(ItemStack itemStack);
}
