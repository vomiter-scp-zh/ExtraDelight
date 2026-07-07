package com.vomiter.extradelight.common.complex.cap;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class BlockEntityTagItemHandlerProvider implements ICapabilityProvider {

    private static final String BLOCK_ENTITY_TAG = "BlockEntityTag";
    private static final String INV_TAG = "inv";

    private final ItemStack stack;
    private final ItemStackHandler handler;
    private final LazyOptional<IItemHandler> optional;

    public BlockEntityTagItemHandlerProvider(ItemStack stack, int slots) {
        this.stack = stack;

        this.handler = new ItemStackHandler(slots) {
            @Override
            protected void onContentsChanged(int slot) {
                save();
            }
        };

        load();

        this.optional = LazyOptional.of(() -> this.handler);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    private void load() {
        CompoundTag blockEntityTag = stack.getTagElement(BLOCK_ENTITY_TAG);

        if (blockEntityTag != null && blockEntityTag.contains(INV_TAG, Tag.TAG_COMPOUND)) {
            handler.deserializeNBT(blockEntityTag.getCompound(INV_TAG));
        }
    }

    private void save() {
        CompoundTag blockEntityTag = stack.getOrCreateTagElement(BLOCK_ENTITY_TAG);
        blockEntityTag.put(INV_TAG, handler.serializeNBT());
    }
}