package com.vomiter.extradelight.common.complex.workstations.meltingpot;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;

import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class MeltingPotRecipe implements Recipe<SimpleContainer> {
	final ResourceLocation id;
    final String group;
	public Ingredient input;
	public int cooktime;
	public FluidStack result;

	public MeltingPotRecipe(ResourceLocation id, Ingredient in, int time, FluidStack out, String group) {
		this.id = id;
        this.group = group;
		this.input = in;
		this.cooktime = time;
		this.result = out;
	}

	@Override
	public boolean matches(SimpleContainer input, Level level) {
		return this.input.test(input.getItem(0));
	}

    @Override
    public boolean isSpecial() {
        return true;
    }


    @Override
	public ItemStack assemble(SimpleContainer input, RegistryAccess registries) {
		return new ItemStack(Items.STICK); // Because other mods expect something regardless
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registries) {
		return new ItemStack(Items.STICK); // Because other mods expect something regardless
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.MELTING_POT_SERIALIZER.get();
	}

	@Override
	public RecipeType<?> getType() {
		return ExtraDelightRecipes.MELTING_POT.get();
	}

	@Override
	public String getGroup() {
		return this.group;
	}

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public static class Serializer implements RecipeSerializer<MeltingPotRecipe> {

        @Override
        public MeltingPotRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(json.get( "ingredient"));

            int cooktime = GsonHelper.getAsInt(json, "cooktime", 100);

            FluidStack result = readFluidStack(GsonHelper.getAsJsonObject(json, "result"));

            String group = GsonHelper.getAsString(json, "group", "");

            return new MeltingPotRecipe(recipeId, ingredient, cooktime, result, group);
        }

        @Override
        public @Nullable MeltingPotRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();

            Ingredient ingredient = Ingredient.fromNetwork(buffer);

            int cooktime = buffer.readVarInt();

            FluidStack result = FluidStack.readFromPacket(buffer);

            return new MeltingPotRecipe(recipeId, ingredient, cooktime, result, group);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MeltingPotRecipe recipe) {
            buffer.writeUtf(recipe.group);

            recipe.input.toNetwork(buffer);

            buffer.writeVarInt(recipe.cooktime);

            recipe.result.writeToPacket(buffer);
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
