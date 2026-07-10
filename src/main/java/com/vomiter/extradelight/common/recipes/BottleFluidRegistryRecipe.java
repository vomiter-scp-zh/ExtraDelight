package com.vomiter.extradelight.common.recipes;

import com.google.gson.JsonObject;
import com.vomiter.extradelight.common.recipes.ingredients.SizedFluidIngredient;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;

import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BottleFluidRegistryRecipe implements Recipe<SimpleRecipeWrapper> {
	final ResourceLocation id;
    final String group;
	public Ingredient bottle;

	public Ingredient getBottle() {
		return bottle;
	}

	public SizedFluidIngredient getFluid() {
		return fluid;
	}

	public SizedFluidIngredient fluid;

	public BottleFluidRegistryRecipe(ResourceLocation id, String group, Ingredient b, SizedFluidIngredient f) {
		this.id = id;
        this.group = group;
		this.fluid = f;
		this.bottle = b;
	}

    public ResourceLocation getId(){
        return id;
    }

	@Override
	public boolean matches(SimpleRecipeWrapper input, Level level) {
		return false;
	}

	@Override
	public ItemStack assemble(SimpleRecipeWrapper input, RegistryAccess registries) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return false;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registries) {
		return ItemStack.EMPTY;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.BOTTLE_FLUID_SERIALIZER.get();
	}

	@Override
	public RecipeType<?> getType() {
		return ExtraDelightRecipes.BOTTLE_FLUID_REGISTRY.get();
	}

    public static class Serializer implements RecipeSerializer<BottleFluidRegistryRecipe> {

        @Override
        public BottleFluidRegistryRecipe fromJson(
                ResourceLocation recipeId,
                JsonObject json
        ) {
            String group = GsonHelper.getAsString(json, "group", "");

            Ingredient bottle = Ingredient.fromJson(
                    GsonHelper.getNonNull(json, "bottle")
            );

            SizedFluidIngredient fluid = SizedFluidIngredient.Serializer.INSTANCE.parse(
                    GsonHelper.getAsJsonObject(json, "fluid")
            );

            return new BottleFluidRegistryRecipe(
                    recipeId,
                    group,
                    bottle,
                    fluid
            );
        }

        @Override
        public @Nullable BottleFluidRegistryRecipe fromNetwork(
                ResourceLocation recipeId,
                FriendlyByteBuf buffer
        ) {
            String group = buffer.readUtf();

            SizedFluidIngredient fluid =
                    SizedFluidIngredient.Serializer.INSTANCE.parse(buffer);

            Ingredient bottle =
                    Ingredient.fromNetwork(buffer);

            return new BottleFluidRegistryRecipe(
                    recipeId,
                    group,
                    bottle,
                    fluid
            );
        }

        @Override
        public void toNetwork(
                FriendlyByteBuf buffer,
                BottleFluidRegistryRecipe recipe
        ) {
            buffer.writeUtf(recipe.group);

            SizedFluidIngredient.Serializer.INSTANCE.write(
                    buffer,
                    recipe.fluid
            );

            recipe.bottle.toNetwork(buffer);
        }
    }
}