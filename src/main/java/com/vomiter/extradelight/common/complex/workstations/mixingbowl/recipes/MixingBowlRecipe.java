package com.vomiter.extradelight.common.complex.workstations.mixingbowl.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import com.vomiter.extradelight.common.recipes.ingredients.SizedFluidIngredient;
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
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MixingBowlRecipe implements Recipe<MixingBowlRecipeWrapper> {
    final ResourceLocation id;
	protected final int stirs;
	final ItemStack container;
	final Ingredient utensil;

	final String group;
	final ItemStack result;
	final NonNullList<Ingredient> ingredients;
	final List<SizedFluidIngredient> fluids;
//	private final boolean isSimple;

	public MixingBowlRecipe(ResourceLocation id, String pGroup, NonNullList<Ingredient> pIngredients, List<SizedFluidIngredient> pFluids,
                            ItemStack pResult, int stirs, ItemStack container, Ingredient utensil) {
        this.id = id;
        this.stirs = stirs;
		this.container = container;
		this.utensil = utensil;
		this.group = pGroup;
		this.result = pResult;

		this.ingredients = pIngredients;
		this.fluids = pFluids;
	}

	public String getGroup() {
		return this.group;
	}

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }


    public @NotNull NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public List<SizedFluidIngredient> getFluids() {
		return this.fluids;
	}

	@Override
	public boolean matches(MixingBowlRecipeWrapper input, Level level) {
		List<ItemStack> inputs = new ArrayList<>();
		int i = 0;

		for (int j = 0; j < 9; ++j) {
			ItemStack itemstack = input.getItem(j);
			if (!itemstack.isEmpty()) {
				++i;
//				if (isSimple)
//					stackedcontents.accountStack(itemstack, 1);
//				else
				inputs.add(itemstack);
			}
		}
		
		boolean itemMatchFlag = (this.ingredients == null || this.ingredients.isEmpty() || ItemStack.isSameItem(this.ingredients.get(0).getItems()[0],ItemStack.EMPTY)) ? // some failsafes
				i == 0 
				: i == this.ingredients.size() && RecipeMatcher.findMatches(inputs, this.ingredients) != null;
		return itemMatchFlag
				&& matchFluids(input.getTank().getAsList()) && ItemStack.isSameItem(container, input.getItem(9))
				&& input.getItem(9).getCount() >= container.getCount();
	}

    @Override
    public ItemStack assemble(MixingBowlRecipeWrapper p_44001_, RegistryAccess p_267165_) {
        return result.copy();
    }

    boolean matchFluids(List<FluidStack> f) {
//		if (this.fluids.size() < f.size())
//			return false;

		int count = 0;
		for (int i = 0; i < fluids.size(); i++) {
			for (int j = 0; j < f.size(); j++) {
				SizedFluidIngredient f1 = fluids.get(i);
				FluidStack f2 = f.get(j);

				if (f1.test(f2)) {
					count++;
				}
			}
		}

		if (count == fluids.size())
			return true;
		return false;
	}


	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return pWidth * pHeight >= this.ingredients.size();
	}

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess p_267052_) {
        return result.copy();
    }

    public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.MIXING_BOWL_SERIALIZER.get();
	}

	public int getStirs() {
		return stirs;
	}

	public ItemStack getContainer() {
		return this.container;
	}

	public Ingredient getUtensil() {
		return this.utensil;
	}

	@Override
	public RecipeType<?> getType() {
		return ExtraDelightRecipes.MIXING_BOWL.get();
	}

    public static class Serializer implements RecipeSerializer<MixingBowlRecipe> {
        @Override
        public MixingBowlRecipe fromJson(ResourceLocation id, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");

            NonNullList<Ingredient> ingredients = NonNullList.create();
            JsonArray ingredientArray = GsonHelper.getAsJsonArray(json, "ingredients");

            for (JsonElement element : ingredientArray) {
                ingredients.add(Ingredient.fromJson(element));
            }

            List<SizedFluidIngredient> fluids = new ArrayList<>();
            JsonArray fluidArray = GsonHelper.getAsJsonArray(json, "fluids");

            for (JsonElement element : fluidArray) {
                if (!element.isJsonObject()) {
                    throw new JsonSyntaxException("Expected fluid ingredient to be a JsonObject");
                }

                fluids.add(SizedFluidIngredient.Serializer.INSTANCE.parse(element.getAsJsonObject()));
            }

            ItemStack result = ShapedRecipe.itemStackFromJson(
                    JsonStackTransformer.addItemForIdFormat(
                            GsonHelper.getAsJsonObject(json, "result")
                    )
            );

            int stirs = GsonHelper.getAsInt(json, "stirs", 100);

            ItemStack container = ItemStack.EMPTY;
            if (json.has("container") && !json.get("container").isJsonNull()) {
                container = ShapedRecipe.itemStackFromJson(
                        JsonStackTransformer.addItemForIdFormat(
                                GsonHelper.getAsJsonObject(json, "container")
                        )
                );
            }

            Ingredient utensil = Ingredient.fromJson(json.get( "utensil"));

            return new MixingBowlRecipe(id, group, ingredients, fluids, result, stirs, container, utensil);
        }

        @Override
        public @Nullable MixingBowlRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();

            int ingredientCount = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientCount, Ingredient.EMPTY);

            for (int i = 0; i < ingredientCount; ++i) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }

            int fluidCount = buffer.readVarInt();
            List<SizedFluidIngredient> fluids = new ArrayList<>(fluidCount);

            for (int i = 0; i < fluidCount; ++i) {
                fluids.add(SizedFluidIngredient.Serializer.INSTANCE.parse(buffer));
            }

            ItemStack result = buffer.readItem();
            int stirs = buffer.readVarInt();
            ItemStack container = buffer.readItem();
            Ingredient utensil = Ingredient.fromNetwork(buffer);

            return new MixingBowlRecipe(id, group, ingredients, fluids, result, stirs, container, utensil);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MixingBowlRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());

            buffer.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeVarInt(recipe.getFluids().size());
            for (SizedFluidIngredient fluid : recipe.getFluids()) {
                SizedFluidIngredient.Serializer.INSTANCE.write(buffer, fluid);
            }

            buffer.writeItem(recipe.result);
            buffer.writeVarInt(recipe.getStirs());
            buffer.writeItem(recipe.getContainer());
            recipe.utensil.toNetwork(buffer);
        }
    }
}