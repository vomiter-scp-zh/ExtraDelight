package com.vomiter.extradelight.common.complex.workstations.evaporator.recipes;

import com.google.gson.JsonObject;
import com.vomiter.extradelight.registry.ExtraDelightBlocks;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;

import com.vomiter.extradelight.common.fluids.SizedFluidIngredient;
import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EvaporatorRecipe implements Recipe<EvaporatorRecipeWrapper> {
    final ResourceLocation id;
	final String group;
	protected final int cookTime;

	public int getCookTime() {
		return cookTime;
	}

	public ResourceLocation getOutput() {
		return output;
	}

	final SizedFluidIngredient fluid;

	public SizedFluidIngredient getFluid() {
		return fluid;
	}

	final ResourceLocation output;
	final ResourceLocation display;

	final ItemStack outItem;

	public ResourceLocation getDisplay() {
		return display;
	}

	public EvaporatorRecipe(ResourceLocation id, String pGroup, SizedFluidIngredient fluid, int time, ResourceLocation lootTable,
			ResourceLocation displayBlock, ItemStack outItem) {
        this.id = id;
		this.group = pGroup;
		this.cookTime = time;
		this.fluid = fluid;
		this.output = lootTable;
		this.display = displayBlock;
		this.outItem = outItem;
	}

    @Override
    public ResourceLocation getId(){
        return id;
    }

	@Override
	public boolean matches(EvaporatorRecipeWrapper input, Level level) {
		return this.fluid.test(input.tank.getFluid());
	}

	public ItemStack getToastSymbol() {
		return new ItemStack(ExtraDelightBlocks.EVAPORATOR.get());
	}

	@Override
	public ItemStack assemble(EvaporatorRecipeWrapper input, RegistryAccess registries) {
		return this.getResultItem(registries).copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registries) {
		return outItem;
	}

	public ItemStack getResultItem() {
		return outItem;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.EVAPORATOR_SERIALIZER.get();
	}

	@Override
	public RecipeType<?> getType() {
		return ExtraDelightRecipes.EVAPORATOR.get();
	}
	
	@Override
	public String getGroup() {
		return this.group;
	}

    public static class Serializer implements RecipeSerializer<EvaporatorRecipe> {

        @Override
        public EvaporatorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");

            SizedFluidIngredient fluid = SizedFluidIngredient.Serializer.INSTANCE.parse(
                    GsonHelper.getAsJsonObject(json, "fluid")
            );

            int time = GsonHelper.getAsInt(json, "time");

            ResourceLocation lootTable = ResourceLocation.tryParse(
                    GsonHelper.getAsString(json, "loottable")
            );

            ResourceLocation displayBlock = ResourceLocation.tryParse(
                    GsonHelper.getAsString(json, "display_block")
            );

            ItemStack outItem = ShapedRecipe.itemStackFromJson(
                    JsonStackTransformer.addItemForIdFormat(
                            GsonHelper.getAsJsonObject(json, "outItem")
                    )
            );

            return new EvaporatorRecipe(
                    recipeId,
                    group,
                    fluid,
                    time,
                    lootTable,
                    displayBlock,
                    outItem
            );
        }

        @Override
        public @Nullable EvaporatorRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();

            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            if (!(ingredient instanceof SizedFluidIngredient fluid)) {
                throw new IllegalStateException("Evaporator recipe fluid must be a SizedFluidIngredient");
            }

            int time = buffer.readVarInt();

            ResourceLocation lootTable = buffer.readResourceLocation();
            ResourceLocation displayBlock = buffer.readResourceLocation();

            ItemStack outItem = buffer.readItem();

            return new EvaporatorRecipe(
                    recipeId,
                    group,
                    fluid,
                    time,
                    lootTable,
                    displayBlock,
                    outItem
            );
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, EvaporatorRecipe recipe) {
            buffer.writeUtf(recipe.group);

            recipe.fluid.toNetwork(buffer);

            buffer.writeVarInt(recipe.cookTime);

            buffer.writeResourceLocation(recipe.output);
            buffer.writeResourceLocation(recipe.display);

            buffer.writeItem(recipe.outItem);
        }
    }
}