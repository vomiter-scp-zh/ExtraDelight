package com.vomiter.extradelight.common.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class CoffeeItem extends DrinkableItem {

	public CoffeeItem(Properties properties) {
		super(properties, true, true);
	}

	@Override
	public void affectConsumer(@NotNull ItemStack stack, @NotNull Level level, LivingEntity consumer) {
		consumer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, FoodValues.MEDIUM_DURATION));

	}

}