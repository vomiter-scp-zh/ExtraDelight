package com.vomiter.extradelight.util.debug;

import com.mojang.brigadier.CommandDispatcher;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public final class ExtraDelightItemCommand {
    private static final int BARREL_SIZE = 27;

    private ExtraDelightItemCommand() {
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("extradelight_items")
                        .requires(source -> source.hasPermission(2))
                        .then(
                                Commands.argument("pos", BlockPosArgument.blockPos())
                                        .executes(context -> fillBarrels(
                                                context.getSource(),
                                                BlockPosArgument.getLoadedBlockPos(
                                                        context,
                                                        "pos"
                                                )
                                        ))
                        )
        );
    }

    private static int fillBarrels(
            CommandSourceStack source,
            BlockPos startPos
    ) {
        ServerLevel level = source.getLevel();

        List<Item> items = ExtraDelightItems.ITEMS
                .getEntries()
                .stream()
                .map(RegistryObject::get)
                .filter(item -> item != null && item != net.minecraft.world.item.Items.AIR)
                .toList();

        if (items.isEmpty()) {
            source.sendFailure(Component.literal(
                    "Extra Delight 的物品註冊表中沒有物品。"
            ));
            return 0;
        }

        int barrelCount = (items.size() + BARREL_SIZE - 1) / BARREL_SIZE;

        for (int barrelIndex = 0; barrelIndex < barrelCount; barrelIndex++) {
            BlockPos barrelPos = startPos.north(barrelIndex);

            level.setBlockAndUpdate(
                    barrelPos,
                    Blocks.BARREL.defaultBlockState()
            );

            if (!(level.getBlockEntity(barrelPos) instanceof BarrelBlockEntity barrel)) {
                source.sendFailure(Component.literal(
                        "無法在 " + formatPos(barrelPos) + " 建立木桶。"
                ));
                return 0;
            }

            int firstItemIndex = barrelIndex * BARREL_SIZE;
            int lastItemIndex = Math.min(
                    firstItemIndex + BARREL_SIZE,
                    items.size()
            );

            for (int itemIndex = firstItemIndex; itemIndex < lastItemIndex; itemIndex++) {
                int slot = itemIndex - firstItemIndex;
                barrel.setItem(slot, new ItemStack(items.get(itemIndex)));
            }

            barrel.setChanged();
        }

        source.sendSuccess(
                () -> Component.literal(
                        "已將 " + items.size()
                                + " 個 Extra Delight 物品放入 "
                                + barrelCount
                                + " 個木桶。"
                ),
                true
        );

        return barrelCount;
    }

    private static String formatPos(BlockPos pos) {
        return pos.getX() + ", " + pos.getY() + ", " + pos.getZ();
    }
}