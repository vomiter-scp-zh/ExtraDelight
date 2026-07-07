package com.vomiter.extradelight.common.items.garlic;

import com.vomiter.extradelight.registry.ExtraDelightItems;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;


import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class GarlicCureDrinkableItem extends DrinkableItem {

	public GarlicCureDrinkableItem(Properties properties) {
		super(properties);
	}

	public GarlicCureDrinkableItem(Properties properties, boolean hasFoodEffectTooltip) {
		super(properties, hasFoodEffectTooltip);
	}

	public GarlicCureDrinkableItem(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
		super(properties, hasFoodEffectTooltip, hasCustomTooltip);
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
