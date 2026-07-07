package com.vomiter.extradelight.common.items;

import com.vomiter.extradelight.registry.ExtraDelightMobEffects;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class SourJuiceItem extends DrinkableItem {
	final int potency;
	final int chance;

	public SourJuiceItem(Properties properties, int potency, int chance) {
		super(properties, false, true);
		this.potency = potency;
		this.chance = chance;
	}

	@Override
	public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
		consumer.heal(2.0F);
		consumer.addEffect(new MobEffectInstance(ExtraDelightMobEffects.SOUR_PUCKER.get(), chance, potency));
	}
}