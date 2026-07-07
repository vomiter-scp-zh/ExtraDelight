package com.vomiter.extradelight.common.complex.workstations.dryingrack;

import com.google.gson.JsonObject;
import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DryingRackSerializer implements RecipeSerializer<DryingRackRecipe> {

    @Override
    public DryingRackRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
        String group = GsonHelper.getAsString(json, "group", "");

        Ingredient ingredient = Ingredient.fromJson(json.get( "ingredient"));

        ItemStack result = ShapedRecipe.itemStackFromJson(
                JsonStackTransformer.addItemForIdFormat(GsonHelper.getAsJsonObject(json, "result"))
        );

        float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);

        int cookingTime = GsonHelper.getAsInt(json, "cookingtime", 100);

        return new DryingRackRecipe(recipeId, group, ingredient, result, experience, cookingTime);
    }

    @Override
    public @Nullable DryingRackRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String group = buffer.readUtf();

        Ingredient ingredient = Ingredient.fromNetwork(buffer);

        ItemStack result = buffer.readItem();

        float experience = buffer.readFloat();

        int cookingTime = buffer.readVarInt();

        return new DryingRackRecipe(recipeId, group, ingredient, result, experience, cookingTime);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, DryingRackRecipe recipe) {
        buffer.writeUtf(recipe.getGroup());

        recipe.ingredient.toNetwork(buffer);

        buffer.writeItem(recipe.result);

        buffer.writeFloat(recipe.experience);

        buffer.writeVarInt(recipe.cookingTime);
    }
}