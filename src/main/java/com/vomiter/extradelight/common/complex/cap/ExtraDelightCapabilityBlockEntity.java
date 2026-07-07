package com.vomiter.extradelight.common.complex.cap;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

public abstract class ExtraDelightCapabilityBlockEntity extends BlockEntity {
    private final Map<Direction, LazyOptional<IItemHandler>> sidedItemCaps = new EnumMap<>(Direction.class);
    private final Map<Direction, LazyOptional<IFluidHandler>> sidedFluidCaps = new EnumMap<>(Direction.class);

    private LazyOptional<IItemHandler> nullSideItemCap = LazyOptional.empty();
    private LazyOptional<IFluidHandler> nullSideFluidCap = LazyOptional.empty();

    protected ExtraDelightCapabilityBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            LazyOptional<IItemHandler> itemCap = getCachedItemCap(side);
            if (itemCap.isPresent()) {
                return itemCap.cast();
            }
        }

        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            LazyOptional<IFluidHandler> fluidCap = getCachedFluidCap(side);
            if (fluidCap.isPresent()) {
                return fluidCap.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    private LazyOptional<IItemHandler> getCachedItemCap(@Nullable Direction side) {
        if (side == null) {
            if (!nullSideItemCap.isPresent()) {
                IItemHandler handler = ExtraDelightCapabilities.getItemHandler(this, null);
                nullSideItemCap = handler == null ? LazyOptional.empty() : LazyOptional.of(() -> handler);
            }

            return nullSideItemCap;
        }

        return sidedItemCaps.computeIfAbsent(side, direction -> {
            IItemHandler handler = ExtraDelightCapabilities.getItemHandler(this, direction);
            return handler == null ? LazyOptional.empty() : LazyOptional.of(() -> handler);
        });
    }

    private LazyOptional<IFluidHandler> getCachedFluidCap(@Nullable Direction side) {
        if (side == null) {
            if (!nullSideFluidCap.isPresent()) {
                IFluidHandler handler = ExtraDelightCapabilities.getFluidHandler(this, null);
                nullSideFluidCap = handler == null ? LazyOptional.empty() : LazyOptional.of(() -> handler);
            }

            return nullSideFluidCap;
        }

        return sidedFluidCaps.computeIfAbsent(side, direction -> {
            IFluidHandler handler = ExtraDelightCapabilities.getFluidHandler(this, direction);
            return handler == null ? LazyOptional.empty() : LazyOptional.of(() -> handler);
        });
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        invalidateCachedCaps();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        clearCachedCaps();
    }

    protected void refreshCachedCaps() {
        invalidateCachedCaps();
        clearCachedCaps();
    }

    private void invalidateCachedCaps() {
        nullSideItemCap.invalidate();
        nullSideFluidCap.invalidate();

        sidedItemCaps.values().forEach(LazyOptional::invalidate);
        sidedFluidCaps.values().forEach(LazyOptional::invalidate);
    }

    private void clearCachedCaps() {
        nullSideItemCap = LazyOptional.empty();
        nullSideFluidCap = LazyOptional.empty();

        sidedItemCaps.clear();
        sidedFluidCaps.clear();
    }
}