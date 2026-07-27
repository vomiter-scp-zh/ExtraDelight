package com.vomiter.extradelight.common.complex.dynamic_feast;

import com.vomiter.extradelight.DataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class DynamicContainerFeastBlockItem extends BlockItem implements IDynamicContainer {
    public DynamicContainerFeastBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    private ItemStack getContainer(ItemStack stack) {
        ItemStack container =
                DataComponents.getStack(stack, DataComponents.CONTAINER);

        return container == null ? ItemStack.EMPTY : container.copy();
    }

    @Override
    protected boolean updateCustomBlockEntityTag(
            BlockPos pos,
            Level level,
            @Nullable Player player,
            ItemStack stack,
            BlockState state
    ) {
        boolean vanillaUpdated = super.updateCustomBlockEntityTag(
                pos,
                level,
                player,
                stack,
                state
        );

        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (!(blockEntity instanceof DynamicContainerFeastBlockEntity feastBlockEntity)) {
            return vanillaUpdated;
        }

        ItemStack container = getContainer(stack);

        if (container.isEmpty()) {
            return vanillaUpdated;
        }

        feastBlockEntity.setContainer(container);
        return true;
    }
}