package com.vomiter.extradelight.common.complex.jar;

import java.util.List;
import java.util.function.Consumer;


import com.vomiter.extradelight.common.complex.jardisplay.IDisplayInteractable;
import com.vomiter.extradelight.common.complex.jardisplay.JarDisplayBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemStackHandler;
import vectorwing.farmersdelight.client.ClientSetup;

public class JarItem extends BlockItem implements IDisplayInteractable {

	public JarItem(JarBlock jarBlock, Properties p_41383_) {
		super(jarBlock, p_41383_);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level context, List<Component> tooltipComponents,
			TooltipFlag tooltipFlag) {
        stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(
                f -> {
                    if (f != null && !f.getFluidInTank(0).isEmpty()) {
                        ResourceLocation r = BuiltInRegistries.FLUID.getKey(f.getFluidInTank(0).getFluid());
                        tooltipComponents.add(Component.literal("Contains: " + f.getFluidInTank(0).getAmount() + "mb - ")
                                .append(Component.translatable(f.getFluidInTank(0).getFluid().getFluidType().getDescriptionId()))
                                .withStyle(ChatFormatting.WHITE));
                    }
                }
        );
	}

	@Override
	public InteractionResult itemInteract(ItemStack heldItem, ItemStack interactItem, BlockState state, Level level,
                                          BlockPos pos, Player player, InteractionHand hand) {
		
		return InteractionResult.CONSUME;
	}

	@Override
	public void extractItem(Level level, BlockPos pos, Player player, JarDisplayBlockEntity jdbe,
                            ItemStackHandler handler, ItemStack s, int index) {

		if (!player.addItem(s)) {
			level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY() + 0.5, pos.getZ(), s.copy()));
			handler.setStackInSlot(index, ItemStack.EMPTY);
		}

	}

	@Override
	public void convertToSingular(BlockPos pos, Level level, JarDisplayBlockEntity jdbe, ItemStack stack) {
		BlockState bs = this.getBlock().defaultBlockState();
        level.setBlock(pos, bs, Block.UPDATE_ALL);
        BlockEntity be = level.getBlockEntity(pos);
        stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(
                f -> {
                    if (be instanceof JarBlockEntity jbe)
                        jbe.getTank().fill(f.getFluidInTank(0).copy(), IFluidHandler.FluidAction.EXECUTE);
                }
        );
	}

    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private static BlockEntityWithoutLevelRenderer renderer = new JarItemModel();

            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }

            public HumanoidModel.@org.jetbrains.annotations.Nullable ArmPose getArmPose(LivingEntity living, InteractionHand hand, ItemStack stack) {
                return stack.getOrCreateTag().contains("FlipTimeStamp") ? ClientSetup.SKILLET_FLIP : null;
            }
        });
    }

}
