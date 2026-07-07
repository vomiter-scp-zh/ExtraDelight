package com.vomiter.extradelight.common.complex.workstations.evaporator.recipes;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class EvaporatorRecipeWrapper extends SimpleContainer {

	final FluidTank tank;

	public EvaporatorRecipeWrapper(FluidTank tank) {
		this.tank = tank;
	}

	public FluidTank getTank() {
		return tank;
	}

	@Override
	public ItemStack getItem(int index) {
		return ItemStack.EMPTY;
	}

	@Override
	public int getContainerSize() {
		return 0;
	}
	
	@Override
	public boolean isEmpty() {
        return false;
    }
}
