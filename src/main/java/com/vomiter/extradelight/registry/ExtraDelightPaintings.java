package com.vomiter.extradelight.registry;

import com.vomiter.extradelight.ExtraDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ExtraDelightPaintings {
	public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister
			.create(Registries.PAINTING_VARIANT, ExtraDelight.MOD_ID);

	public static RegistryObject<PaintingVariant> BIG_ORANGE_SLICE = PAINTING_VARIANTS.register(
			"big_orange_slice",
			() -> new PaintingVariant(16, 16));

	public static RegistryObject<PaintingVariant> BIG_LIME_SLICE = PAINTING_VARIANTS.register(
			"big_lime_slice",
			() -> new PaintingVariant(16, 16));
	
	public static RegistryObject<PaintingVariant> BIG_LEMON_SLICE = PAINTING_VARIANTS.register(
			"big_lemon_slice",
			() -> new PaintingVariant(16, 16));
	
	public static RegistryObject<PaintingVariant> BIG_GRAPEFRUIT_SLICE = PAINTING_VARIANTS.register(
			"big_grapefruit_slice",
			() -> new PaintingVariant(16, 16));
}