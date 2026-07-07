package com.vomiter.extradelight.common.complex.workstations.chiller;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class ChillerRecipeWrapper extends RecipeWrapper {
	FluidStack tank;

	public ChillerRecipeWrapper(IItemHandlerModifiable inv, FluidStack tank) {
		super(inv);
		this.tank = tank;
	}

}
