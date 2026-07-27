package com.vomiter.extradelight.common.complex.dynamic_feast;

import com.vomiter.extradelight.common.blocks.RecipeFeastBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DynamicContainerFeastBlock extends RecipeFeastBlock implements EntityBlock {
    public DynamicContainerFeastBlock(
            Properties properties,
            boolean hasLeftovers,
            VoxelShape... shapes
    ) {
        super(properties, hasLeftovers, shapes);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DynamicContainerFeastBlockEntity(pos, state);
    }

    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder params) {
        List<ItemStack> drops = new ArrayList<>(super.getDrops(state, params));

        BlockEntity blockEntity =
                params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);

        if (blockEntity instanceof DynamicContainerFeastBlockEntity feastBlockEntity) {
            ItemStack container = feastBlockEntity.getContainer();
            Item defaultContainer = asItem().getCraftingRemainingItem();

            if (!container.isEmpty()) {
                if(drops.removeIf(item -> item.is(defaultContainer))){
                    drops.add(container);
                }
            }
        }

        return drops;
    }
}