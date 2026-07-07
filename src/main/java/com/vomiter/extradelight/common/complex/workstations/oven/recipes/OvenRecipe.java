package com.vomiter.extradelight.common.complex.workstations.oven.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OvenRecipe implements Recipe<RecipeWrapper> {
	public static final int INPUT_SLOTS = 9;

    private final ResourceLocation id;
	private final String group;
//	private final OvenRecipeBookTab tab;
	private final NonNullList<Ingredient> inputItems;
	public final ItemStack output;
	private final ItemStack container;
	private final float experience;
	private final int cookTime;
	private final boolean consumeContainer;

	public OvenRecipe(
            ResourceLocation id,
            String group, /* @Nullable OvenRecipeBookTab tab, */
            NonNullList<Ingredient> inputItems,
            ItemStack output,
            ItemStack container,
            float experience,
            int cookTime,
            boolean consumeContainer
    ) {
        this.id = id;
        this.group = group;
//		this.tab = tab;
		this.inputItems = inputItems;
		this.output = output;

		if (!container.isEmpty()) {
			this.container = container;
		} else {
			this.container = ItemStack.EMPTY;
		}

		this.experience = experience;
		this.cookTime = cookTime;
		this.consumeContainer = consumeContainer;
	}

//	@Override
//	public ResourceLocation getId() {
//		return this.id;
//	}

	public ItemStack getContainerOverride() {
		return this.container;
	}

	@Override
	public String getGroup() {
		return this.group;
	}

	public boolean shouldConsumeContainer() {
		return this.consumeContainer;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.inputItems;
	}

	public ItemStack getOutputContainer() {
		return this.container;
	}

	public float getExperience() {
		return this.experience;
	}

	public int getCookTime() {
		return this.cookTime;
	}

	@Override
	public boolean matches(RecipeWrapper inv, Level level) {
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		int i = 0;

		for (int j = 0; j < INPUT_SLOTS; ++j) {
			ItemStack itemstack = inv.getItem(j);
			if (!itemstack.isEmpty()) {
				++i;
				inputs.add(itemstack);
			}
		}
		return i == this.inputItems.size() && RecipeMatcher.findMatches(inputs, this.inputItems) != null
				&& inv.getItem(INPUT_SLOTS + 1).getItem() == this.container.getItem();
	}

    @Override
    public @NotNull ItemStack assemble(@NotNull RecipeWrapper recipeWrapper, @NotNull RegistryAccess registryAccess) {
        return this.output.copy();
    }

    @Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= this.inputItems.size();
	}

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess registryAccess) {
        return this.output;
    }

    @Override
	public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.OVEN_SERIALIZER.get();
	}

	@Override
	public RecipeType<?> getType() {
		return ExtraDelightRecipes.OVEN.get();
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(ExtraDelightItems.OVEN.get());
	}

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<OvenRecipe> {

        @Override
        public @NotNull OvenRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");

            NonNullList<Ingredient> ingredients = NonNullList.create();
            JsonArray ingredientArray = GsonHelper.getAsJsonArray(json, "ingredients");

            for (JsonElement element : ingredientArray) {
                Ingredient ingredient = Ingredient.fromJson(element, false);

                if (!ingredient.isEmpty()) {
                    ingredients.add(ingredient);
                }
            }

            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for oven recipe");
            }

            var resultObj = GsonHelper.getAsJsonObject(json, "result");
            ItemStack result = ShapedRecipe.itemStackFromJson(JsonStackTransformer.addItemForIdFormat(resultObj));

            ItemStack container = ItemStack.EMPTY;
            if (json.has("container") && !json.get("container").isJsonNull()) {
                container = ShapedRecipe.itemStackFromJson(
                        JsonStackTransformer.addItemForIdFormat(GsonHelper.getAsJsonObject(json, "container"))
                );
            }

            float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);
            int cookTime = GsonHelper.getAsInt(json, "cookingtime", 200);
            boolean consumeContainer = GsonHelper.getAsBoolean(json, "consumeContainer");

            return new OvenRecipe(
                    id,
                    group,
                    ingredients,
                    result,
                    container,
                    experience,
                    cookTime,
                    consumeContainer
            );
        }

        @Override
        public @Nullable OvenRecipe fromNetwork(@NotNull ResourceLocation id, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();

            int size = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); i++) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack result = buffer.readItem();
            ItemStack container = buffer.readItem();

            float experience = buffer.readFloat();
            int cookTime = buffer.readVarInt();
            boolean consumeContainer = buffer.readBoolean();

            return new OvenRecipe(
                    id,
                    group,
                    ingredients,
                    result,
                    container,
                    experience,
                    cookTime,
                    consumeContainer
            );
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, OvenRecipe recipe) {
            buffer.writeUtf(recipe.group);

            buffer.writeVarInt(recipe.inputItems.size());

            for (Ingredient ingredient : recipe.inputItems) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.output);
            buffer.writeItem(recipe.container);

            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.cookTime);
            buffer.writeBoolean(recipe.consumeContainer);
        }
    }

}
