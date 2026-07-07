package com.vomiter.extradelight.common.items;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class XocolatlItem extends DrinkableItem {
	public XocolatlItem(Properties properties) {
		super(properties, false, true);
	}

	@Override
	public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
		consumer.addEffect(
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE,
                        FoodValues.MEDIUM_DURATION)
        );
		
		Iterator<MobEffectInstance> itr = consumer.getActiveEffects().iterator();
		ArrayList<MobEffect> compatibleEffects = new ArrayList<>();

		while (itr.hasNext()) {//loop all effect
			MobEffectInstance effect = itr.next();
			if (
                    effect.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)
                            && effect.getEffect().getCurativeItems().contains(Items.MILK_BUCKET.getDefaultInstance())
                    //effects that can be cured by milk
            ) {
				compatibleEffects.add(effect.getEffect());
			}
		}

		if (!compatibleEffects.isEmpty()) {
			MobEffectInstance selectedEffect
                    = consumer.getEffect(compatibleEffects.get(level.random.nextInt(compatibleEffects.size())));
            //get a random curable effect

			if (selectedEffect != null) {
				consumer.removeEffect(selectedEffect.getEffect());
			}
		}

	}
}