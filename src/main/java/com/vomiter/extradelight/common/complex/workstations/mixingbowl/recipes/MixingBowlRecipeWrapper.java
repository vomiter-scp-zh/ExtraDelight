package com.vomiter.extradelight.common.complex.workstations.mixingbowl.recipes;


import com.vomiter.extradelight.common.complex.workstations.FancyTank;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class MixingBowlRecipeWrapper extends RecipeWrapper {

	final FancyTank tank;

	public MixingBowlRecipeWrapper(IItemHandlerModifiable inv, FancyTank tank) {
		super(inv);
		this.tank = tank;
	}

	public FancyTank getTank() {
		return tank;
	}
}