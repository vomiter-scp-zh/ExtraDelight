package com.vomiter.extradelight.common.complex.workstations.vat.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import com.vomiter.extradelight.common.recipes.ingredients.SizedFluidIngredient;
import com.vomiter.extradelight.common.complex.workstations.vat.VatBlockEntity;

import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.RecipeMatcher;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VatRecipe implements Recipe<VatRecipeWrapper> {

    final ResourceLocation id;
	final ItemStack containerItem;

	final String group;
	final ItemStack result;
	final NonNullList<Ingredient> ingredients;
	final NonNullList<StageIngredient> stageIngredients;
	final SizedFluidIngredient fluid;

	protected final int stages;

	public int getStages() {
		return stages;
	}

	public VatRecipe(ResourceLocation id, String pGroup, NonNullList<Ingredient> pIngredients,
			NonNullList<StageIngredient> pStageIngredients, SizedFluidIngredient pFluids, ItemStack pResult, int stages,
			ItemStack usedItem) {
//		this.cookTime = time;
        this.id = id;
		this.containerItem = usedItem;
		this.group = pGroup;
		this.result = pResult;

		this.ingredients = pIngredients;
		this.stageIngredients = pStageIngredients;
		this.fluid = pFluids;
		this.stages = stages;
	}

    @Override
    public ResourceLocation getId(){
        return id;
    }

	public String getGroup() {
		return this.group;
	}

	public NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public NonNullList<StageIngredient> getStageIngredients() {
		return this.stageIngredients;
	}

	public SizedFluidIngredient getFluid() {
		return this.fluid;
	}

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
	public boolean matches(VatRecipeWrapper input, Level level) {
		List<ItemStack> inputs = new ArrayList<>();
		int i = 0;

		for (int j = 0; j < 6; ++j) {
			ItemStack itemstack = input.getItem(j);
			if (!itemstack.isEmpty()) {
				++i;
				inputs.add(itemstack);
			}
		}

		return i == this.ingredients.size() && RecipeMatcher.findMatches(inputs, this.ingredients) != null
				&& fluid.test(input.getTank().getFluid())
				&& ItemStack.isSameItem(containerItem, input.getItem(VatBlockEntity.CONTAINER_SLOT))
				&& input.getItem(VatBlockEntity.CONTAINER_SLOT).getCount() >= containerItem.getCount();
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack assemble(SimpleContainer pInv, RegistryAccess p_267165_) {
		return this.result.copy();
	}

	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return pWidth * pHeight >= this.ingredients.size();
	}

	public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.VAT_SERIALIZER.get();
	}

//	public int getTime() {
//		return cookTime;
//	}

	public ItemStack getUsedItem() {
		return this.containerItem;
	}

	@Override
	public ItemStack assemble(VatRecipeWrapper input, RegistryAccess registries) {
		return this.result.copy();
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registries) {
		return this.result;
	}

	@Override
	public RecipeType<?> getType() {
		return ExtraDelightRecipes.VAT.get();
	}

	public static class StageIngredient {
		public static final StageIngredient EMPTY = new StageIngredient(Ingredient.EMPTY, 0, false);
		public Ingredient ingredient;
		public int time;
		public boolean lid;

		public StageIngredient(Ingredient i, int t, boolean l) {
			ingredient = i;
			time = t;
			lid = l;
		}
    }

    public static class Serializer implements RecipeSerializer<VatRecipe> {
        @Override
        public VatRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
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
                throw new JsonParseException("No ingredients for vat recipe");
            }

            NonNullList<StageIngredient> stageIngredients = NonNullList.create();
            JsonArray stageArray = GsonHelper.getAsJsonArray(json, "stage_ingredients");

            for (JsonElement element : stageArray) {
                JsonObject stageObj = element.getAsJsonObject();
                var ingredientElement = stageObj.get("ingredient");
                Ingredient stageIng;
                stageIng = Ingredient.fromJson(ingredientElement);
                int time = GsonHelper.getAsInt(stageObj, "time");
                boolean lid = GsonHelper.getAsBoolean(stageObj, "lid");

                stageIngredients.add(new StageIngredient(stageIng, time, lid));
            }

            if (stageIngredients.isEmpty()) {
                throw new JsonParseException("No StageIngredients for vat recipe");
            }

            ItemStack usedItem = ItemStack.EMPTY;
            if (json.has("usedItem") && !json.get("usedItem").isJsonNull()) {
                usedItem = ShapedRecipe.itemStackFromJson(
                        JsonStackTransformer.addItemForIdFormat(GsonHelper.getAsJsonObject(json, "usedItem"))
                );
            }

            SizedFluidIngredient fluidIngredient;
            if (json.has("fluids") && !json.get("fluids").isJsonNull()) {
                fluidIngredient = SizedFluidIngredient.Serializer.INSTANCE.parse(GsonHelper.getAsJsonObject(json, "fluids"));
            } else {
                fluidIngredient = new SizedFluidIngredient(Fluids.EMPTY, null, 0);
            }

            JsonObject resultObj = GsonHelper.getAsJsonObject(json, "result");
            ItemStack result = ShapedRecipe.itemStackFromJson(JsonStackTransformer.addItemForIdFormat(resultObj));

            int stages = GsonHelper.getAsInt(json, "stages");

            return new VatRecipe(
                    recipeId,
                    group,
                    ingredients,
                    stageIngredients,
                    fluidIngredient,
                    result,
                    stages,
                    usedItem
            );
        }

        @Override
        public @Nullable VatRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();

            int ingredientCount = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientCount, Ingredient.EMPTY);

            for (int i = 0; i < ingredientCount; i++) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }

            int stageIngredientCount = buffer.readVarInt();
            NonNullList<StageIngredient> stageIngredients = NonNullList.withSize(stageIngredientCount, null);

            for (int i = 0; i < stageIngredientCount; i++) {
                Ingredient ingredient = Ingredient.fromNetwork(buffer);
                int time = buffer.readVarInt();
                boolean lid = buffer.readBoolean();

                stageIngredients.set(i, new StageIngredient(ingredient, time, lid));
            }

            SizedFluidIngredient fluidIngredient = SizedFluidIngredient.Serializer.INSTANCE.parse(buffer);

            ItemStack result = buffer.readItem();

            int stages = buffer.readVarInt();

            ItemStack usedItem = buffer.readItem();

            return new VatRecipe(
                    recipeId,
                    group,
                    ingredients,
                    stageIngredients,
                    fluidIngredient,
                    result,
                    stages,
                    usedItem
            );
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, VatRecipe recipe) {
            buffer.writeUtf(recipe.group);

            buffer.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeVarInt(recipe.stageIngredients.size());
            for (StageIngredient stageIngredient : recipe.stageIngredients) {
                stageIngredient.ingredient.toNetwork(buffer);
                buffer.writeVarInt(stageIngredient.time);
                buffer.writeBoolean(stageIngredient.lid);
            }

            SizedFluidIngredient.Serializer.INSTANCE.write(buffer, recipe.getFluid());

            buffer.writeItem(recipe.result);

            buffer.writeVarInt(recipe.stages);

            buffer.writeItem(recipe.getUsedItem());
        }
    }
}