package com.vomiter.extradelight.common.items.corn;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class CornSilkTeaItem extends DrinkableItem {
	public CornSilkTeaItem(Properties properties) {
		super(properties, false, true);
	}

	@Override
	public void affectConsumer(@NotNull ItemStack stack, @NotNull Level level, LivingEntity consumer) {
		consumer.heal(2.0F);
		consumer.addEffect(new MobEffectInstance(ModEffects.COMFORT.get(), FoodValues.SHORT_DURATION));

	}
}