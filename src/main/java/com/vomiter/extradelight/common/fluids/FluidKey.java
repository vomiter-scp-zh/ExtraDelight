package com.vomiter.extradelight.common.fluids;

import net.minecraftforge.fluids.FluidStack;

import java.util.Objects;
import java.util.Optional;

public class FluidKey {
	private final FluidStack fluid;
	
	public FluidKey(FluidStack fluid) {
		this.fluid = new FluidStack(fluid.getFluid(), 1);
        if(fluid.getTag() != null) this.fluid.setTag(fluid.getTag().copy());
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof FluidKey other)) return false;
		return FluidStack.areFluidStackTagsEqual(this.fluid,other.fluid);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(
                fluid.getFluid(),
                Optional.ofNullable(fluid.getTag())
        );
	}
	
	public FluidStack createStack(int amount) {
		FluidStack res = new FluidStack(fluid.getFluid(),amount);
        if(fluid.getTag() != null) res.setTag(fluid.getTag().copy());
		return res;
	}
}