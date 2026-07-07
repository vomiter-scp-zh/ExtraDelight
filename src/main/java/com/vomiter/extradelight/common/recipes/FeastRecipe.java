package com.vomiter.extradelight.common.recipes;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FeastRecipe implements Recipe<SimpleRecipeWrapper> {
    private final ResourceLocation id;
	protected final String group;
	protected final BlockItem feast;
	protected final Ingredient container;
	protected final ItemStack result;
	final CookingBookCategory category = CookingBookCategory.FOOD;

	public Ingredient getContainer() {
		return container;
	}

	public FeastRecipe(ResourceLocation id, String pGroup, BlockItem feast, Ingredient pIngredient, ItemStack pResult) {
        this.id = id;
        this.group = pGroup;
		this.feast = feast;
		this.container = pIngredient;
		this.result = pResult;
	}

	public FeastRecipe(ResourceLocation id, String pGroup, ItemStack feast, Ingredient pIngredient, ItemStack pResult) {
        this.id = id;
        this.group = pGroup;
		this.feast = (BlockItem) feast.getItem();
		this.container = pIngredient;
		this.result = pResult;
	}

	public BlockItem getFeast() {
		return feast;
	}

	public ItemStack getFeastStack() {
		return new ItemStack(feast);
	}

	@Override
	public boolean matches(SimpleRecipeWrapper pContainer, Level pLevel) {
		return this.container.test(pContainer.getItem(1)) && this.feast == pContainer.getItem(0).getItem();
	}

    @Override
    public ItemStack assemble(SimpleRecipeWrapper p_44001_, RegistryAccess p_267165_) {
        return result;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }


    public static class Serializer implements RecipeSerializer<FeastRecipe> {

        @Override
        public @NotNull FeastRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");

            ItemStack outStack = ShapedRecipe.itemStackFromJson(JsonStackTransformer.addItemForIdFormat(GsonHelper.getAsJsonObject(json, "out")));
            if (!(outStack.getItem() instanceof BlockItem blockItem)) {
                throw new JsonSyntaxException("'out' must be a BlockItem: " + outStack);
            }

            Ingredient container = Ingredient.fromJson(json.get("container"));

            ItemStack result = ShapedRecipe.itemStackFromJson(
                    JsonStackTransformer.addItemForIdFormat(
                            JsonStackTransformer.addItemForIdFormat(GsonHelper.getAsJsonObject(json, "result"))
                    )
            );

            return new FeastRecipe(recipeId, group, blockItem, container, result);
        }

        @Override
        public @Nullable FeastRecipe fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();

            Ingredient container = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();

            ItemStack feastStack = buffer.readItem();
            if (!(feastStack.getItem() instanceof BlockItem blockItem)) {
                throw new IllegalStateException("Feast recipe 'out' must be a BlockItem: " + feastStack);
            }

            return new FeastRecipe(recipeId, group, blockItem, container, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FeastRecipe recipe) {
            buffer.writeUtf(recipe.group);

            recipe.container.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeItem(new ItemStack(recipe.getFeast()));
        }
    }

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return result;
    }

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.FEAST_SERIALIZER.get();
	}

	@Override
	public RecipeType<?> getType() {
		return ExtraDelightRecipes.FEAST.get();
	}

	@Override
	public String getGroup() {
		return this.group;
	}

    @Override
    public ResourceLocation getId() {
        return id;
    }
}