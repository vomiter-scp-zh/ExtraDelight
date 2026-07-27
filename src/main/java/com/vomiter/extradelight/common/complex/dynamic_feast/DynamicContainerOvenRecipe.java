package com.vomiter.extradelight.common.complex.dynamic_feast;

import com.vomiter.extradelight.DataComponents;
import com.vomiter.extradelight.common.complex.workstations.oven.recipes.OvenRecipe;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class DynamicContainerOvenRecipe extends OvenRecipe {

    public DynamicContainerOvenRecipe(ResourceLocation id, String group, NonNullList<Ingredient> inputItems, ItemStack output, ItemStack container, float experience, int cookTime, boolean consumeContainer) {
        super(id, group, inputItems, output, container, experience, cookTime, consumeContainer);
    }

    public ItemStack assemble(RecipeWrapper inv, RegistryAccess access) {
        var result = super.assemble(inv, access).copy();
        var container = getOutputContainer();
        DataComponents.setStack(result, DataComponents.CONTAINER, container.copyWithCount(1));
        return result;
    }

    public RecipeSerializer<?> getSerializer() {
        return ExtraDelightRecipes.OVEN_DYNAMIC_CONTAINER_SERIALIZER.get();
    }

    public RecipeType<?> getType() {
        return ExtraDelightRecipes.OVEN_DYNAMIC_CONTAINER.get();
    }


}
