package com.vomiter.extradelight.common.recipes;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Pair;

import com.vomiter.extradelight.DataComponents;
import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.items.dynamic.DynamicJam;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

public class DynamicJamRecipe extends CookingPotRecipe {

	private final String graphic;

	public DynamicJamRecipe(ResourceLocation id, String group, CookingPotRecipeBookTab tab, NonNullList<Ingredient> inputItems,
                            ItemStack output, ItemStack container, float experience, int cookTime, String graphic) {
		super(id, group, tab, inputItems, output, container, experience, cookTime);
		this.graphic = graphic;

	}

	public String getGraphic() {
		return graphic;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess provider) {
		ItemStack stack = super.getResultItem(provider);
		if (stack.getItem() instanceof DynamicJam jam) {
			List<ItemStack> stacks = new ArrayList<ItemStack>();
			for (Ingredient i : this.getIngredients()) {
				if (i.getItems().length > 0)
					stacks.add(i.getItems()[0]);
			}

		} else {
			ExtraDelight.LOGGER.error("DynamicJamRecipe result not DynamicJam!");
		}

		return stack;
	}

	@Override
	public ItemStack assemble(RecipeWrapper inv, RegistryAccess provider) {
		ItemStack stack = this.getResultItem(provider).copy();
		if (stack.getItem() instanceof DynamicJam) {

			int nutrition = 0;
			float saturation = 0;
			List<Pair<MobEffectInstance, Float>> effects = new ArrayList<>();

			List<ItemStack> l = new ArrayList<>();
			for (int i = 0; i < inv.getContainerSize() - 2; i++) {
				ItemStack s = inv.getItem(i);
				if (s != null && !s.isEmpty()) {
					l.add(s);
                    var food = s.getFoodProperties(null);
                    if(food != null){
                        nutrition += food.getNutrition();
                        saturation += food.getSaturationModifier();
                        effects.addAll(food.getEffects());
                    }
                }
			}
            DataComponents.setDynamicIngredient(stack, l);

//			stack.set(ExtraDelightComponents.DYNAMIC_FOOD.get(), new DynamicItemComponent(List.of(graphic)));
//			stack.set(ExtraDelightComponents.ITEMSTACK_HANDLER.get(), ItemContainerContents.fromItems(l));


			//stack.set(DataComponents.FOOD, food);
		} else {
			ExtraDelight.LOGGER.error("DynamicToastRecipe result not DynamicToast!");
		}
		return stack;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.DYNAMIC_JAM_SERIALIZER.get();
	}

//	@Override
//	public RecipeType<?> getType() {
//		return ExtraDelightRecipes.DYNAMIC_JAM.get();
//	}

    public static class Serializer implements RecipeSerializer<DynamicJamRecipe> {

        public Serializer() {
        }

        @Override
        public DynamicJamRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");

            CookingPotRecipeBookTab tab = null;
            if (json.has("recipe_book_tab")) {
                tab = CookingPotRecipeBookTab.findByName(GsonHelper.getAsString(json, "recipe_book_tab", ""));
            }

            JsonArray ingredientsJson = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> ingredients = NonNullList.create();

            for (JsonElement element : ingredientsJson) {
                Ingredient ingredient = Ingredient.fromJson(element);
                if (!ingredient.isEmpty()) {
                    ingredients.add(ingredient);
                }
            }

            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for dynamic jam recipe");
            }

            ItemStack result = ShapedRecipe.itemStackFromJson(
                    JsonStackTransformer.addItemForIdFormat(
                        GsonHelper.getAsJsonObject(json, "result")
                    )
            );

            ItemStack container = ItemStack.EMPTY;
            if (json.has("container")) {
                container = ShapedRecipe.itemStackFromJson(
                        JsonStackTransformer.addItemForIdFormat(GsonHelper.getAsJsonObject(json, "container"))
                );
            }

            float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);
            int cookingTime = GsonHelper.getAsInt(json, "cookingtime", 200);
            String graphic = GsonHelper.getAsString(json, "graphic", "");

            return new DynamicJamRecipe(
                    recipeId,
                    group,
                    tab,
                    ingredients,
                    result,
                    container,
                    experience,
                    cookingTime,
                    graphic
            );
        }

        @Override
        public @Nullable DynamicJamRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            CookingPotRecipeBookTab tab = CookingPotRecipeBookTab.findByName(buffer.readUtf());

            int size = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); i++) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack result = buffer.readItem();
            ItemStack container = buffer.readItem();

            float experience = buffer.readFloat();
            int cookingTime = buffer.readVarInt();
            String graphic = buffer.readUtf();

            return new DynamicJamRecipe(
                    recipeId,
                    group,
                    tab,
                    ingredients,
                    result,
                    container,
                    experience,
                    cookingTime,
                    graphic
            );
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, DynamicJamRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeUtf(recipe.getRecipeBookTab() != null ? recipe.getRecipeBookTab().toString() : "");

            buffer.writeVarInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.getResultItem(null));
            buffer.writeItem(recipe.getOutputContainer());

            buffer.writeFloat(recipe.getExperience());
            buffer.writeVarInt(recipe.getCookTime());
            buffer.writeUtf(recipe.getGraphic());
        }
    }
}
