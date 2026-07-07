package com.vomiter.extradelight.common.complex.fluid_handler.keg;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class KegBlock extends RotatedPillarBlock implements EntityBlock {

	public KegBlock(Properties p_49795_) {
		super(p_49795_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new KegBlockEntity(p_153215_, p_153216_);
	}

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand hand, BlockHitResult hitResult) {
		if (pLevel.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {

			BlockEntity tileEntity = pLevel.getBlockEntity(pPos);
			if (tileEntity instanceof KegBlockEntity be) {
				be.use(pPlayer, hand);
			}

		}
		return InteractionResult.SUCCESS;
	}
}
