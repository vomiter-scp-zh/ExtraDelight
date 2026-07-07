package com.vomiter.extradelight.common.complex.workstations.juicer;

import com.vomiter.extradelight.util.BlockEntityUtils;
import com.vomiter.extradelight.util.BottleFluidRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class JuicerBlock extends Block implements EntityBlock/* , IStyleable */ {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public static final IntegerProperty STYLE = IntegerProperty.create("style", 0, Styles.values().length - 1);
	protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public static enum Styles {
		OAK, SPRUCE
	};

	public JuicerBlock(Properties p_49795_) {
		super(p_49795_);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
				.setValue(WATERLOGGED, false).setValue(STYLE, 0));
	}

	@Override
	public boolean useShapeForLightOcclusion(BlockState pState) {
		return true;
	}

	@Override
    public boolean isPathfindable(BlockState state, BlockGetter context, BlockPos pState, PathComputationType pType) {
		return false;
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPE;
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level level = context.getLevel();
		FluidState fluid = level.getFluidState(context.getClickedPos());

		BlockState state = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection())
				.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);

		return state;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new JuicerBlockEntity(p_153215_, p_153216_);
	}

    @Override
    public @NotNull InteractionResult use(
            @NotNull BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hitResult
    ) {
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (!(blockEntity instanceof JuicerBlockEntity juicer)) {
            return InteractionResult.PASS;
        }

        ItemStack stack = player.getItemInHand(hand);
        ItemStack offhandStack = player.getOffhandItem();

        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        ItemStack bottleResult = BottleFluidRegistry.getBottleFromFluid(juicer.getFluidTank().getFluid());

        if (stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent()) {

            FluidUtil.interactWithFluidHandler(player, hand, juicer.getFluidTank());
            return InteractionResult.SUCCESS;
        }

        if (!bottleResult.isEmpty()
                && (ItemStack.isSameItem(bottleResult.getCraftingRemainingItem(), stack)
                || ItemStack.isSameItem(bottleResult.getCraftingRemainingItem(), offhandStack))) {


            if (juicer.getFluidTank().drain(250, IFluidHandler.FluidAction.SIMULATE).getAmount() == 250) {

                juicer.getFluidTank().drain(250, IFluidHandler.FluidAction.EXECUTE);

                BlockEntityUtils.Inventory.givePlayerItemStack(bottleResult, player, level, pos);
                stack.shrink(1);

                juicer.setChanged();
                level.sendBlockUpdated(pos, state, state, 3);

                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        }

        if (player.isCrouching()) {

            juicer.extractItem(player);

            juicer.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);

            return InteractionResult.SUCCESS;
        }

        if (juicer.getInsertedItem().isEmpty()) {

            if (stack.isEmpty()) {
                return InteractionResult.PASS;
            }

            juicer.insertItem(stack);

            juicer.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);

            return InteractionResult.SUCCESS;
        }

        if (stack.isEmpty()) {

            juicer.grind(player);

            juicer.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level,
			BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		return state;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, WATERLOGGED, STYLE);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity tileEntity = level.getBlockEntity(pos);
			if (tileEntity instanceof JuicerBlockEntity te) {
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

//	@Override
//	public int numStyles() {
//		return Styles.values().length;
//	}
//
//	@Override
//	public int getCurrentStyle(BlockState state) {
//		return state.getValue(STYLE);
//	}
//
//	@Override
//	public void setNextStyle(Level level, BlockPos pos, BlockState state) {
//		int next = state.getValue(STYLE) + 1;
//		if (state.getValue(STYLE) >= numStyles() - 1) {
//			next = 0;
//		}
//
//		for (int i = 0; i < 10; i++)
//			level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SNOW_BLOCK.defaultBlockState()),
//					pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, 0, 0, 0);
//		level.playSound(null, pos, SoundEvents.SLIME_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
//
//		BlockState nextState = state.setValue(STYLE, next);
//		level.setBlock(pos, nextState, 3);
//	}
//
//	@Override
//	public void setPrevStyle(Level level, BlockPos pos, BlockState state) {
//		int next = state.getValue(STYLE) - 1;
//		if (next < 0) {
//			next = this.numStyles() - 1;
//		}
//
//		for (int i = 0; i < 10; i++)
//			level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SNOW_BLOCK.defaultBlockState()),
//					pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, 0, 0, 0);
//		level.playSound(null, pos, SoundEvents.SLIME_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
//
//		BlockState nextState = state.setValue(STYLE, next);
//		level.setBlock(pos, nextState, 3);
//	}
//
//	@Override
//	public BlockState getState(int i) {
//		return this.defaultBlockState().setValue(STYLE, i);
//	}
//
//	@Override
//	public void setStyle(Level level, BlockPos pos, BlockState state, int style) {
//		BlockState nextState = state.setValue(STYLE, style);
//		level.setBlock(pos, nextState, 3);
//	}
//
//	@Override
//	public boolean isPatreonStyle(int style) {
//		return false;
//	}
//
//	@Override
//	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents,
//			TooltipFlag tooltipFlag) {
//		MutableComponent textEmpty = Component.translatable(ExtraDelight.MOD_ID + ".tooltip.styleable");
//		tooltipComponents.add(textEmpty.withStyle(ChatFormatting.AQUA));
//	}

}