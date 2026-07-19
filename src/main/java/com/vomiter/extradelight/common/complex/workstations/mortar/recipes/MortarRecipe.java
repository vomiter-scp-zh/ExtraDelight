package com.vomiter.extradelight.common.complex.workstations.mortar.recipes;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.vomiter.extradelight.registry.ExtraDelightBlocks;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;

import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.nbt.TagParser;
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
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class MortarRecipe extends SingleItemRecipe {
	protected final int grinds;
	protected final FluidStack fluidOut;
    final ResourceLocation id;

	public MortarRecipe( ResourceLocation id, String pGroup, Ingredient pIngredient, ItemStack pResult, FluidStack fluidResult, int grinds) {
		super(ExtraDelightRecipes.MORTAR.get(), ExtraDelightRecipes.MORTAR_SERIALIZER.get(), id, pGroup, pIngredient,
				pResult);

		this.grinds = grinds;
		this.fluidOut = fluidResult;
        this.id = id;
    }

	public int getGrinds() {
		return grinds;
	}

	public FluidStack getFluid() {
		return fluidOut;
	}

    @Override
    public boolean isSpecial() {
        return true;
    }


    @Override
    public boolean matches(Container input, Level p_44003_) {
        return this.ingredient.test(input.getItem(0));
    }

    public ItemStack getToastSymbol() {
		return new ItemStack(ExtraDelightBlocks.MORTAR_STONE.get());
	}

    public static class Serializer implements RecipeSerializer<MortarRecipe> {

        @Override
        public MortarRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");

            Ingredient ingredient = Ingredient.fromJson(json.get( "ingredient"));

            ItemStack result = ItemStack.EMPTY;
            if (json.has("result") && !json.get("result").isJsonNull()) {
                JsonObject resultJson = JsonStackTransformer.addItemForIdFormat(GsonHelper.getAsJsonObject(json, "result"));
                if (resultJson.has("item")) {
                    result = ShapedRecipe.itemStackFromJson((resultJson));
                }
            }

            FluidStack fluidOut = FluidStack.EMPTY;
            if (json.has("fluidOut") && !json.get("fluidOut").isJsonNull()) {
                JsonObject fluidJson = GsonHelper.getAsJsonObject(json, "fluidOut");
                if (fluidJson.has("id")) {
                    fluidOut = readFluidStack(fluidJson);
                }
            }

            int grinds = GsonHelper.getAsInt(json, "grinds", 200);

            return new MortarRecipe(recipeId, group, ingredient, result, fluidOut, grinds);
        }

        @Override
        public @Nullable MortarRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();

            Ingredient ingredient = Ingredient.fromNetwork(buffer);

            ItemStack result = buffer.readItem();

            FluidStack fluidOut = FluidStack.readFromPacket(buffer);

            int grinds = buffer.readInt();

            return new MortarRecipe(recipeId, group, ingredient, result, fluidOut, grinds);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MortarRecipe recipe) {
            buffer.writeUtf(recipe.group);

            recipe.ingredient.toNetwork(buffer);

            buffer.writeItem(recipe.result);

            recipe.fluidOut.writeToPacket(buffer);

            buffer.writeInt(recipe.grinds);
        }

        private static FluidStack readFluidStack(JsonObject json) {
            ResourceLocation fluidId = ResourceLocation.tryParse(GsonHelper.getAsString(json, "id"));
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidId);

            if (fluid == null || fluid == Fluids.EMPTY) {
                throw new JsonSyntaxException("Unknown fluid '" + fluidId + "'");
            }

            int amount = GsonHelper.getAsInt(json, "amount", 1000);
            FluidStack stack = new FluidStack(fluid, amount);

            if (json.has("nbt")) {
                try {
                    stack.setTag(TagParser.parseTag(GsonHelper.getAsString(json, "nbt")));
                } catch (CommandSyntaxException e) {
                    throw new JsonSyntaxException("Invalid fluid NBT: " + e.getMessage());
                }
            }

            return stack;
        }
    }
}