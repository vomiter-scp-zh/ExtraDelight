package com.vomiter.extradelight.common.complex.cap;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;

public class BlockEntityTagFluidHandlerProvider implements ICapabilityProvider {

    private static final String BLOCK_ENTITY_TAG = "BlockEntityTag";
    private static final String FLUID_TAG = "fluid";

    private final ItemStack stack;
    private final JarFluidHandler handler;
    private final LazyOptional<IFluidHandlerItem> optional;

    public BlockEntityTagFluidHandlerProvider(ItemStack stack, int capacity) {
        this.stack = stack;
        this.handler = new JarFluidHandler(capacity);
        this.handler.load();
        this.optional = LazyOptional.of(() -> this.handler);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER_ITEM) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    private class JarFluidHandler implements IFluidHandlerItem {

        private final FluidTank tank;

        private JarFluidHandler(int capacity) {
            this.tank = new FluidTank(capacity) {
                @Override
                protected void onContentsChanged() {
                    save();
                }
            };
        }

        private void load() {
            CompoundTag blockEntityTag = stack.getTagElement(BLOCK_ENTITY_TAG);

            if (blockEntityTag != null && blockEntityTag.contains(FLUID_TAG, Tag.TAG_COMPOUND)) {
                tank.readFromNBT(blockEntityTag.getCompound(FLUID_TAG));
            }
        }

        private void save() {
            CompoundTag blockEntityTag = stack.getOrCreateTagElement(BLOCK_ENTITY_TAG);
            CompoundTag fluidTag = tank.writeToNBT(new CompoundTag());
            blockEntityTag.put(FLUID_TAG, fluidTag);
        }

        @Override
        public ItemStack getContainer() {
            return stack;
        }

        @Override
        public int getTanks() {
            return tank.getTanks();
        }

        @Override
        public FluidStack getFluidInTank(int tankIndex) {
            return tank.getFluidInTank(tankIndex);
        }

        @Override
        public int getTankCapacity(int tankIndex) {
            return tank.getTankCapacity(tankIndex);
        }

        @Override
        public boolean isFluidValid(int tankIndex, FluidStack stack) {
            return tank.isFluidValid(tankIndex, stack);
        }

        @Override
        public int fill(FluidStack resource, FluidAction action) {
            return tank.fill(resource, action);
        }

        @Override
        public FluidStack drain(FluidStack resource, FluidAction action) {
            return tank.drain(resource, action);
        }

        @Override
        public FluidStack drain(int maxDrain, FluidAction action) {
            return tank.drain(maxDrain, action);
        }
    }
}