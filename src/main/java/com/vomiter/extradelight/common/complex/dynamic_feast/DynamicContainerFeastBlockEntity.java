package com.vomiter.extradelight.common.complex.dynamic_feast;

import com.vomiter.extradelight.registry.ExtraDelightBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class DynamicContainerFeastBlockEntity extends BlockEntity {
    private static final String TAG_CONTAINER = "Container";

    private ItemStack container = ItemStack.EMPTY;

    public DynamicContainerFeastBlockEntity(BlockPos pos, BlockState state) {
        super(
                ExtraDelightBlockEntities.DYNAMIC_CONTAINER_FEAST.get(),
                pos,
                state
        );
    }

    public ItemStack getContainer() {
        return container.copy();
    }

    public void setContainer(ItemStack container) {
        ItemStack newContainer = container.copy();

        if (!newContainer.isEmpty()) {
            newContainer.setCount(1);
        }

        if (ItemStack.matches(this.container, newContainer)) {
            return;
        }

        this.container = newContainer;
        setChanged();

        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(
                    worldPosition,
                    getBlockState(),
                    getBlockState(),
                    3
            );
        }
    }

    public boolean hasContainer() {
        return !container.isEmpty();
    }

    public ItemStack removeContainer() {
        ItemStack result = container;
        container = ItemStack.EMPTY;

        setChanged();

        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(
                    worldPosition,
                    getBlockState(),
                    getBlockState(),
                    3
            );
        }

        return result;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        if (!container.isEmpty()) {
            tag.put(TAG_CONTAINER, container.save(new CompoundTag()));
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if (tag.contains(TAG_CONTAINER, CompoundTag.TAG_COMPOUND)) {
            container = ItemStack.of(tag.getCompound(TAG_CONTAINER));
        } else {
            container = ItemStack.EMPTY;
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(
            Connection connection,
            ClientboundBlockEntityDataPacket packet
    ) {
        CompoundTag tag = packet.getTag();

        if (tag != null) {
            load(tag);
        }
    }
}