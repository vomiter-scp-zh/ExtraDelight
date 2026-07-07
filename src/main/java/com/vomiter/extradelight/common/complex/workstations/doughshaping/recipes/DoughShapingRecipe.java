package com.vomiter.extradelight.common.complex.workstations.doughshaping.recipes;

import com.google.gson.JsonObject;

import com.vomiter.extradelight.registry.ExtraDelightBlocks;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class DoughShapingRecipe extends SingleItemRecipe {

	public DoughShapingRecipe(ResourceLocation id, String pGroup, Ingredient pIngredient, ItemStack pResult) {
		super(
                ExtraDelightRecipes.DOUGH_SHAPING.get(),
                ExtraDelightRecipes.DOUGH_SHAPING_SERIALIZER.get(),
                id,
                pGroup,
				pIngredient,
                pResult
        );
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	public boolean matches(Container input, Level level) {
		return this.ingredient.test(input.getItem(0));
	}

	public ItemStack getToastSymbol() {
		return new ItemStack(ExtraDelightBlocks.DOUGH_SHAPING.get());
	}

	@Override
	public String getGroup() {
		return this.group;
	}

    public static class Serializer implements RecipeSerializer<DoughShapingRecipe> {

        @Override
        public DoughShapingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");

            Ingredient ingredient = Ingredient.fromJson(json.get( "ingredient"));

            ItemStack result = ShapedRecipe.itemStackFromJson(
                    JsonStackTransformer.addItemForIdFormat(GsonHelper.getAsJsonObject(json, "result"))
            );

            return new DoughShapingRecipe(recipeId, group, ingredient, result);
        }

        @Override
        public @Nullable DoughShapingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();

            Ingredient ingredient = Ingredient.fromNetwork(buffer);

            ItemStack result = buffer.readItem();

            return new DoughShapingRecipe(recipeId, group, ingredient, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, DoughShapingRecipe recipe) {
            buffer.writeUtf(recipe.group);

            recipe.ingredient.toNetwork(buffer);

            buffer.writeItem(recipe.result);
        }
    }
}