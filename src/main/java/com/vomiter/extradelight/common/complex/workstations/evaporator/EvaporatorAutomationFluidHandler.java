package com.vomiter.extradelight.common.complex.workstations.evaporator;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class EvaporatorAutomationFluidHandler implements IFluidHandler {
    private final EvaporatorBlockEntity evaporator;

    public EvaporatorAutomationFluidHandler(EvaporatorBlockEntity evaporator) {
        this.evaporator = evaporator;
    }

    private IFluidHandler tank() {
        return evaporator.getFluidTank();
    }

    @Override
    public int getTanks() {
        return tank().getTanks();
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        return tank().getFluidInTank(tank);
    }

    @Override
    public int getTankCapacity(int tank) {
        return tank().getTankCapacity(tank);
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        return this.tank().isFluidValid(tank, stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (!evaporator.isInventoryEmpty()) {
            return 0;
        }

        return tank().fill(resource, action);
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        return tank().drain(resource, action);
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return tank().drain(maxDrain, action);
    }
}