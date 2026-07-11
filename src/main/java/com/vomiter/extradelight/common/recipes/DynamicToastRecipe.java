package com.vomiter.extradelight.common.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.vomiter.extradelight.DataComponents;
import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.items.dynamic.DynamicToast;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DynamicToastRecipe extends ShapelessRecipe {
    private final ItemStack result;

	public DynamicToastRecipe(ResourceLocation id, String group, CraftingBookCategory category, ItemStack result,
                              NonNullList<Ingredient> ingredients) {
		super(id, group, category, result, ingredients);
        this.result = result;
	}

//	public DynamicToastRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern,
//			ItemStack result, String graphic) {
//		super(group, category, pattern, result);
//		this.graphic = graphic;
//
//	}

    @Override
    public boolean isSpecial() {
        return true;
    }

    public String getGraphic() {
        return "";
	}

	@Override
	public ItemStack assemble(CraftingContainer input, RegistryAccess registries) {
		ItemStack stack = super.getResultItem(registries).copy();
		if (stack.getItem() instanceof DynamicToast) {
            DataComponents.setDynamicIngredient(stack, input.getItems());

            /*
			int nutrition = 0;
			float saturation = 0;
			List<FoodProperties.PossibleEffect> effects = new ArrayList<FoodProperties.PossibleEffect>();

			NonNullList<ItemStack> l = NonNullList.create();
			for (ItemStack s : input.items())
				if (s != null && !s.isEmpty()) {
					l.add(s);
					if (s.has(DataComponents.FOOD)) {
						FoodProperties f = s.get(DataComponents.FOOD);
						nutrition += f.nutrition();
						saturation += f.saturation();

						effects.addAll(f.effects());
					} else
						ExtraDelight.logger
								.error(s.getDescriptionId() + " doesn't have a food component! How did we get here?!");
				}

			stack.set(ExtraDelightComponents.DYNAMIC_FOOD.get(), new DynamicItemComponent(List.of(graphic)));
			stack.set(ExtraDelightComponents.ITEMSTACK_HANDLER.get(), ItemContainerContents.fromItems(l));

			FoodProperties food = new FoodProperties(nutrition, saturation / input.items().size(), false, 1.6F,
					java.util.Optional.empty(), effects);

			stack.set(DataComponents.FOOD, food);

             */
		} else {
			ExtraDelight.LOGGER.error("DynamicToastRecipe result not DynamicToast!");
		}
		return stack;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.DYNAMIC_TOAST_SERIALIZER.get();
	}

    public static class Serializer implements RecipeSerializer<DynamicToastRecipe> {
        static int MAX_WIDTH = 3;
        static int MAX_HEIGHT = 3;

        public DynamicToastRecipe fromJson(ResourceLocation p_44290_, JsonObject p_44291_) {
            String s = GsonHelper.getAsString(p_44291_, "group", "");
            CraftingBookCategory craftingbookcategory = CraftingBookCategory.CODEC.byName(GsonHelper.getAsString(p_44291_, "category", null), CraftingBookCategory.MISC);
            NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(p_44291_, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nonnulllist.size() > MAX_WIDTH * MAX_HEIGHT) {
                throw new JsonParseException("Too many ingredients for shapeless recipe. The maximum is " + (MAX_WIDTH * MAX_HEIGHT));
            } else {
                ItemStack itemstack = ShapedRecipe.itemStackFromJson(
                        JsonStackTransformer.addItemForIdFormat(
                                GsonHelper.getAsJsonObject(p_44291_, "result")
                        )
                );
                return new DynamicToastRecipe(p_44290_, s, craftingbookcategory, itemstack, nonnulllist);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray p_44276_) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < p_44276_.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(p_44276_.get(i), false);
                if (true || !ingredient.isEmpty()) {
                    // FORGE: Skip checking if an ingredient is empty during shapeless recipe deserialization to prevent complex ingredients from caching tags too early. Can not be done using a config value due to sync issues.
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        public DynamicToastRecipe fromNetwork(ResourceLocation p_44293_, FriendlyByteBuf p_44294_) {
            String s = p_44294_.readUtf();
            CraftingBookCategory craftingbookcategory = p_44294_.readEnum(CraftingBookCategory.class);
            int i = p_44294_.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

            nonnulllist.replaceAll(ignored -> Ingredient.fromNetwork(p_44294_));

            ItemStack itemstack = p_44294_.readItem();
            return new DynamicToastRecipe(p_44293_, s, craftingbookcategory, itemstack, nonnulllist);
        }

        public void toNetwork(FriendlyByteBuf p_44281_, DynamicToastRecipe p_44282_) {
            p_44281_.writeUtf(p_44282_.getGroup());
            p_44281_.writeEnum(p_44282_.category());
            p_44281_.writeVarInt(p_44282_.getIngredients().size());

            for(Ingredient ingredient : p_44282_.getIngredients()) {
                ingredient.toNetwork(p_44281_);
            }

            p_44281_.writeItem(p_44282_.result);
        }
    }

}