package com.vomiter.extradelight.common.complex.fluid_handler.tap;


import com.vomiter.extradelight.common.complex.fluid_handler.WellFluidCapability;
import com.vomiter.extradelight.registry.ExtraDelightBlockEntities;
import com.vomiter.extradelight.common.complex.cap.ExtraDelightCapabilityBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TapBlockEntity extends ExtraDelightCapabilityBlockEntity {

	WellFluidCapability water = new WellFluidCapability(Fluids.WATER);
	private final Lazy<IFluidHandler> fluidHandler = Lazy.of(() -> water);

	public TapBlockEntity(BlockPos pPos, BlockState pState) {
		super(ExtraDelightBlockEntities.TAP.get(), pPos, pState);
	}

	public IFluidHandler getFluidHandler() {
		return fluidHandler.get();
	}

	public ItemStack fill(ItemStack stack, Player player) {
		if (stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent()) {
			FluidActionResult r = FluidUtil.tryFillContainer(stack, water, Integer.MAX_VALUE, player, true);
			return r.result;
		}
		return stack;
	}

	public ItemStack drain(ItemStack stack, Player player) {
		FluidActionResult r = FluidUtil.tryEmptyContainer(stack, water, Integer.MAX_VALUE, player, true);
		return r.result;
	}
}
