package com.vomiter.extradelight.common.recipes;

import java.util.Arrays;
import java.util.List;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.ItemStack;

public class SimpleRecipeWrapper extends SimpleContainer {
	List<ItemStack> items;

	public SimpleRecipeWrapper(ItemStack...itemStacks)
	{
		items = Arrays.asList(itemStacks);
	}
	
	@Override
	public ItemStack getItem(int index) {
		return items.get(index);
	}
}