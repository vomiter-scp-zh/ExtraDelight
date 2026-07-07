package com.vomiter.extradelight.gui;

import com.vomiter.extradelight.util.BottleFluidRegistry;
import com.vomiter.extradelight.common.complex.workstations.FancyTank;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FancyTankInSlot extends SlotItemHandler {
	FancyTank tank;

	public FancyTankInSlot(IItemHandler itemHandler, FancyTank tank, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		this.tank = tank;
	}

	@Override
	public int getMaxStackSize(ItemStack stack) {
		return calcFluidInSlotSize(stack);
	}

	private int calcFluidInSlotSize(ItemStack pSlot) {
		if (pSlot.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent() && pSlot.getItem() != Items.BUCKET) {
			if (pSlot.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(null).getFluidInTank(0).isEmpty())
				return 0;
			return 1;
		} else {
//			if (pSlot.getItem() == Items.BUCKET)
//				return (tank.getCapacity() - tank.getTotalAmount()) / 1000;
			if (!BottleFluidRegistry.getFluidFromBottle(pSlot).isEmpty())
				return tank.getVacancy() / 250;
			return 0;
		}
	}

}