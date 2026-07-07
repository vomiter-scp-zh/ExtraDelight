package com.vomiter.extradelight.common.items.garlic;

import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

import com.vomiter.extradelight.registry.ExtraDelightItems;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GarlicCureItem extends Item {

	public GarlicCureItem(Properties properties) {
		super(properties);
	}

	@NotNull
	@Override
	public ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level worldIn,
			@NotNull LivingEntity entityLiving) {
		if (!worldIn.isClientSide) {
			if (ModList.get().isLoaded("vampirism") && !ExtraDelightItems.GARLIC_CURE.get().isEmpty()){
                entityLiving.curePotionEffects(ExtraDelightItems.GARLIC_CURE.get());
            }
		}
		return super.finishUsingItem(stack, worldIn, entityLiving);
	}
}