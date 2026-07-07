package com.vomiter.extradelight.common.complex.jardisplay;

import java.util.Optional;

import com.vomiter.extradelight.DataComponents;
import com.vomiter.extradelight.common.recipes.FeastRecipe;
import com.vomiter.extradelight.common.recipes.SimpleRecipeWrapper;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class JarSingularItem extends BlockItem implements IDisplayInteractable {

	public JarSingularItem(Block block, Properties properties) {
		super(block, properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InteractionResult itemInteract(ItemStack heldItem, ItemStack interactItem, BlockState state, Level level,
                                          BlockPos pos, Player player, InteractionHand hand) {

		int servings = DataComponents.getBlockStateInt(interactItem, JarSingularBlock.SERVINGS, 0);

		if (servings == 0) {
			level.playSound(null, pos, SoundEvents.WOOD_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
//				level.destroyBlock(pos, true);
			return InteractionResult.SUCCESS;
		}

		ItemStack heldheldItem = player.getItemInHand(hand);
		Optional<FeastRecipe> r = level.getRecipeManager().getRecipeFor(ExtraDelightRecipes.FEAST.get(),
				new SimpleRecipeWrapper(interactItem, heldItem), level);

		if (r.isPresent()) {
			if (servings > 0) {
				ItemStack output = r.get().getResultItem(player.level().registryAccess()).copy();

                DataComponents.setBlockStateProperty(interactItem, JarSingularBlock.SERVINGS, servings - 1);

				if (!player.getAbilities().instabuild) {
					if (heldheldItem.isDamageableItem())
						heldheldItem.hurtAndBreak(1, player, null);
					else
						heldheldItem.shrink(1);
				}
				if (!player.getInventory().add(output)) {
					player.drop(output, false);
				}
//				if (servings - 1 <= 0) {
//					List<ItemheldItem> drops = Block.getDrops(state, (ServerLevel) level, pos, null);
//					interactItem.setCount(0);
//				}
				level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);

				return InteractionResult.SUCCESS;
			}
		} else
			player.displayClientMessage(Component.translatable("extradelight.block.recipefeast.use_container"), true);

		return InteractionResult.PASS;
	}

    @Override
    public void extractItem(Level level, BlockPos pos, Player player, JarDisplayBlockEntity jdbe, ItemStackHandler handler, ItemStack s, int index) {
        if (!player.addItem(s)) {
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY() + 0.5, pos.getZ(), s.copy()));
            handler.setStackInSlot(index, ItemStack.EMPTY);
        }

    }

	@Override
	public void convertToSingular(BlockPos pos, Level level, JarDisplayBlockEntity jdbe, ItemStack stack) {
		BlockState bs = this.getBlock().defaultBlockState();
        bs = DataComponents.applyBlockStateTag(stack, bs);
		level.setBlock(pos, bs, Block.UPDATE_CLIENTS);
	}

}
