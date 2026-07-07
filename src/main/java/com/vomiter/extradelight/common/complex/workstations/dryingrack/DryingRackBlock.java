package com.vomiter.extradelight.common.complex.workstations.dryingrack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.vomiter.extradelight.registry.ExtraDelightBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class DryingRackBlock extends Block implements EntityBlock {

	public DryingRackBlock() {
		// strength used to be (1, 1)
		super(Properties.copy(Blocks.OAK_PLANKS).strength(2.5F).sound(SoundType.WOOD)
				.noOcclusion());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Nonnull
	@Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand hand, BlockHitResult hitResult) {
		BlockEntity blockentity = pLevel.getBlockEntity(pPos);
		if (blockentity instanceof DryingRackBlockEntity) {
			DryingRackBlockEntity be = (DryingRackBlockEntity) blockentity;

			if (pPlayer.isCrouching()) {
				if (!pLevel.isClientSide) {
					be.extractItem(pPlayer);
					return InteractionResult.SUCCESS;
				}
				return InteractionResult.CONSUME;
			} else {

				ItemStack itemstack = pPlayer.getItemInHand(hand);
				if (!pLevel.isClientSide) {
					be.insertItem(itemstack);
					return InteractionResult.SUCCESS;
				}

				return InteractionResult.CONSUME;
			}
		}

		return InteractionResult.PASS;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new DryingRackBlockEntity(pPos, pState);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState,
			BlockEntityType<T> pBlockEntityType) {
		return pBlockEntityType == ExtraDelightBlockEntities.DRYING_RACK.get() ? DryingRackBlockEntity::tick : null;
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity tileEntity = level.getBlockEntity(pos);
			if (tileEntity instanceof DryingRackBlockEntity te) {
				IItemHandler items = te.getItemHandler();
				for (int i = 0; i < te.getItemHandler().getSlots(); i++) {
					level.addFreshEntity(
							new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), items.getStackInSlot(i)));
				}
				level.updateNeighbourForOutputSignal(pos, this);
			}

			super.onRemove(state, level, pos, newState, isMoving);
		}
	}
}