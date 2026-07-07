package com.vomiter.extradelight.common.effects;

import com.vomiter.extradelight.registry.ExtraDelightMobEffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class SunshineEffect extends MobEffect {

	public SunshineEffect() {
		super(MobEffectCategory.BENEFICIAL, 0xffff00);
	}

	@Override
	public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {

		amplifier = breakdownEffect(livingEntity, amplifier, MobEffects.BLINDNESS);
		amplifier = breakdownEffect(livingEntity, amplifier, MobEffects.DARKNESS);
    }

	private int breakdownEffect(LivingEntity livingEntity, int amplifier, MobEffect blindness) {
		if (amplifier >= 0)
			if (livingEntity.hasEffect(blindness)) {
				MobEffectInstance mbi = livingEntity.getEffect(blindness);

				int i = mbi.getAmplifier() - 1;
				livingEntity.removeEffect(blindness);
				if (i > -1)
					livingEntity.addEffect(new MobEffectInstance(blindness, mbi.getDuration(), i));

				amplifier--;

				int mbi2 = livingEntity.getEffect(ExtraDelightMobEffects.SUNSHINE.get()).getDuration();

				livingEntity.removeEffect(ExtraDelightMobEffects.SUNSHINE.get());

				if (amplifier >= 0)
					livingEntity.addEffect(new MobEffectInstance(ExtraDelightMobEffects.SUNSHINE.get(), mbi2, amplifier));
			}

		return amplifier;
	}

}