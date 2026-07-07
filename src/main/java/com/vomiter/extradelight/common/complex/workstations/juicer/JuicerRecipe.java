package com.vomiter.extradelight.common.complex.workstations.juicer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.vomiter.extradelight.registry.ExtraDelightBlocks;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class JuicerRecipe extends SingleItemRecipe {
	protected final FluidStack fluidOut;
	protected final int percentChance;

	public JuicerRecipe(ResourceLocation id, String pGroup, Ingredient pIngredient, ItemStack pResult, int chance, FluidStack fluidResult) {
		super(ExtraDelightRecipes.JUICER.get(), ExtraDelightRecipes.JUICER_SERIALIZER.get(), id, pGroup, pIngredient,
				pResult);
		this.fluidOut = fluidResult;
		this.percentChance = chance;
	}

	public FluidStack getFluid() {
		return fluidOut;
	}

	public Ingredient getInput() {
		return this.ingredient;
	}

	public int getChance() {
		return this.percentChance;
	}

    @Override
    public boolean matches(Container container, Level level) {
        if (container.isEmpty()) {
            return false;
        }

        return this.ingredient.test(container.getItem(0));
    }

    public ItemStack getToastSymbol() {
		return new ItemStack(ExtraDelightBlocks.JUICER.get());
	}

    public static class Serializer implements RecipeSerializer<JuicerRecipe> {

        @Override
        public JuicerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");

            JsonElement ingredientJson = json.get("ingredient");
            if (ingredientJson == null || ingredientJson.isJsonNull()) {
                throw new JsonSyntaxException("Missing ingredient, expected to find a JsonObject or JsonArray");
            }

            Ingredient ingredient = Ingredient.fromJson(ingredientJson, false);

            ItemStack result = ItemStack.EMPTY;
            if (json.has("result") && !json.get("result").isJsonNull()) {
                result = ShapedRecipe.itemStackFromJson(
                        JsonStackTransformer
                                .addItemForIdFormat(GsonHelper.getAsJsonObject(json, "result"))
                );
            }

            int chance = GsonHelper.getAsInt(json, "chance", 100);

            FluidStack fluidOut = FluidStack.EMPTY;
            if (json.has("fluidOut") && !json.get("fluidOut").isJsonNull()) {
                var fluidObj = json.get("fluidOut").getAsJsonObject();
                ResourceLocation fluidId = ResourceLocation.tryParse(GsonHelper.getAsString(fluidObj, "id"));
                if(fluidId != null && ForgeRegistries.FLUIDS.getValue(fluidId) != null){
                    fluidOut = new FluidStack(Objects.requireNonNull(ForgeRegistries.FLUIDS.getValue(fluidId)), GsonHelper.getAsInt(fluidObj, "amount"));
                }
            }

            return new JuicerRecipe(
                    recipeId,
                    group,
                    ingredient,
                    result,
                    chance,
                    fluidOut
            );
        }

        @Override
        public @Nullable JuicerRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();

            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();

            int chance = buffer.readInt();

            FluidStack fluidOut = FluidStack.readFromPacket(buffer);

            return new JuicerRecipe(
                    recipeId,
                    group,
                    ingredient,
                    result,
                    chance,
                    fluidOut
            );
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, JuicerRecipe recipe) {
            buffer.writeUtf(recipe.group);

            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);

            buffer.writeInt(recipe.percentChance);

            recipe.fluidOut.writeToPacket(buffer);
        }
    }
}