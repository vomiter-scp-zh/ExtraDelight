package com.vomiter.extradelight.common.complex.workstations.vat.recipes;


import com.vomiter.extradelight.common.complex.workstations.FancyTank;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class VatRecipeWrapper extends RecipeWrapper {

	final FancyTank tank;

	public VatRecipeWrapper(IItemHandlerModifiable inv, FancyTank fluid) {
		super(inv);
		this.tank = fluid;
	}

	public FancyTank getTank() {
		return tank;
	}
}
