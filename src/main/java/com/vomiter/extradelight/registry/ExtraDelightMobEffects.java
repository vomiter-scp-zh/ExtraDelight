package com.vomiter.extradelight.registry;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.effects.PickledEffect;
import com.vomiter.extradelight.common.effects.SourPuckerEffect;
import com.vomiter.extradelight.common.effects.SunshineEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ExtraDelightMobEffects {
	private static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT,
			ExtraDelight.MOD_ID);

	public static final RegistryObject<PickledEffect> PICKLED = EFFECTS.register("pickled",
			PickledEffect::new);
	public static final RegistryObject<SourPuckerEffect> SOUR_PUCKER = EFFECTS.register("sour_pucker",
			SourPuckerEffect::new);
	public static final RegistryObject<SunshineEffect> SUNSHINE = EFFECTS.register("sunshine",
			SunshineEffect::new);

	public static void register(IEventBus modBus) {
		EFFECTS.register(modBus);
	}
}