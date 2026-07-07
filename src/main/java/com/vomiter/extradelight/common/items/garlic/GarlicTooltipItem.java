package com.vomiter.extradelight.common.items.garlic;

import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.common.items.ToolTipConsumableItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

public class GarlicTooltipItem extends ToolTipConsumableItem {

	public GarlicTooltipItem(Properties properties) {
		super(properties);
	}

	public GarlicTooltipItem(Properties properties, boolean hasFoodEffectTooltip) {
		super(properties, hasFoodEffectTooltip);
	}

	public GarlicTooltipItem(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
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
