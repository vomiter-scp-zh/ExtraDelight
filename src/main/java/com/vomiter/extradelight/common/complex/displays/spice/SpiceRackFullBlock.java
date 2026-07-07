package com.vomiter.extradelight.common.complex.displays.spice;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;

public class SpiceRackFullBlock extends SpiceRackBlock implements SimpleWaterloggedBlock {

	public SpiceRackFullBlock() {
		super();
		this.registerDefaultState(
				this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}
}
