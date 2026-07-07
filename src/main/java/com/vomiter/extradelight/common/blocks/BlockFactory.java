package com.vomiter.extradelight.common.blocks;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public final class BlockFactory {
    private BlockFactory() {
    }

    public static FenceGateBlock fenceGate(Properties properties, WoodType woodType) {
        return new FenceGateBlock(properties, woodType);
    }

    public static FenceGateBlock fenceGate(WoodType woodType, Properties properties) {
        return new FenceGateBlock(properties, woodType);
    }

    public static DoorBlock door(Properties properties, BlockSetType blockSetType) {
        return new DoorBlock(properties, blockSetType);
    }

    public static DoorBlock door(BlockSetType blockSetType, Properties properties) {
        return new DoorBlock(properties, blockSetType);
    }

    public static TrapDoorBlock trapDoor(Properties properties, BlockSetType blockSetType) {
        return new TrapDoorBlock(properties, blockSetType);
    }

    public static TrapDoorBlock trapDoor(BlockSetType blockSetType, Properties properties) {
        return new TrapDoorBlock(properties, blockSetType);
    }
}