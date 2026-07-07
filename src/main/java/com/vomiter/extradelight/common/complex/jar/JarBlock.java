package com.vomiter.extradelight.common.complex.jar;


import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.complex.jardisplay.IDisplayInteractable;
import com.vomiter.extradelight.common.complex.jardisplay.JarDisplayBlock;
import com.vomiter.extradelight.common.complex.jardisplay.JarDisplayBlockEntity;
import com.vomiter.extradelight.registry.Fermentation;
import com.vomiter.extradelight.util.BlockEntityUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

public class JarBlock extends Block implements EntityBlock {
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 9.0D, 11.0D);

	public JarBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new JarBlockEntity(p_153215_, p_153216_);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (!level.isClientSide) {
            var stack = player.getItemInHand(hand);
			if (stack.getItem() instanceof IDisplayInteractable bi) {
				ItemStack clone = this.getCloneItemStack(state, result, level, pos, player).copy();

				level.setBlock(pos,
						Fermentation.JAR_DISPLAY_BLOCK.get().defaultBlockState().setValue(JarDisplayBlock.FACING,
								result.getDirection() == Direction.UP || result.getDirection() == Direction.DOWN
										? player.getDirection()
										: result.getDirection().getOpposite()),
						UPDATE_ALL);
//					level.setBlockEntity(new JarDisplayBlockEntity(pos, state));

				BlockEntity be = level.getBlockEntity(pos);
				if (be != null && be instanceof JarDisplayBlockEntity jdbe) {
					BlockEntityUtils.Inventory.insertItem(jdbe.getItems(), clone, JarDisplayBlockEntity.NUM_SLOTS);
					BlockEntityUtils.Inventory.insertItem(jdbe.getItems(), stack, JarDisplayBlockEntity.NUM_SLOTS);
				}
				// Something went wrong, put it back!
				else {
					level.setBlock(pos, state, UPDATE_ALL);
					ExtraDelight.LOGGER.error("Jar Display Entity Invalid!");
				}

				return InteractionResult.SUCCESS;
			} else {
				BlockEntity be = level.getBlockEntity(pos);
				if (be != null && be instanceof JarBlockEntity jdbe) {
					jdbe.use(player, hand);
				}
			}

		}
		return InteractionResult.PASS;
	}

	@Override
    public ItemStack getCloneItemStack(BlockGetter context, BlockPos pos, BlockState state) {
		ItemStack stack = new ItemStack(this);
		BlockEntity ent = context.getBlockEntity(pos);
		if (ent instanceof JarBlockEntity jbe) {
            stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(
                    stackCap -> stackCap.fill(jbe.getTank().getFluid(), IFluidHandler.FluidAction.EXECUTE)
            );
        }
		return stack;
	}
}
