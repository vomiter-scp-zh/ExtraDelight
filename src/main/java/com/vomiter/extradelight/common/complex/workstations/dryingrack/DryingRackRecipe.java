package com.vomiter.extradelight.common.complex.workstations.dryingrack;

import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class DryingRackRecipe implements Recipe<SimpleContainer> {

    protected final ResourceLocation id;
	protected final String group;
	protected final Ingredient ingredient;
	protected final ItemStack result;
	protected final float experience;
	protected final int cookingTime;

	public DryingRackRecipe(ResourceLocation id, String pGroup, Ingredient pIngredient, ItemStack pResult, float pExperience,
			int pCookingTime) {
        this.id = id;
		this.group = pGroup;
		this.ingredient = pIngredient;
		this.result = pResult;
		this.experience = pExperience;
		this.cookingTime = pCookingTime;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.of(null, ingredient);
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.DRYING_RACK_SERIALIZER.get();
	}

	@Override
	public boolean matches(SimpleContainer pContainer, Level pLevel) {
		return ingredient.test(pContainer.getItem(0));
	}

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		// TODO Auto-generated method stub
		return true;
	}

    public Ingredient getInput() {
		return ingredient;
	}

	public int getCookingTime() {
		return this.cookingTime;
	}

	@Override
	public RecipeType<?> getType() {
		// TODO Auto-generated method stub
		return ExtraDelightRecipes.DRYING_RACK.get();
	}

	@Override
	public ItemStack assemble(SimpleContainer input, RegistryAccess registries) {
		return this.result.copy();
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registries) {
		// TODO Auto-generated method stub
		return result;
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
