package com.vomiter.extradelight.data.loot;

import com.mojang.serialization.Codec;
import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.data.loot.FoodLoot;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ExtraDelightLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ExtraDelight.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> FOOD_LOOT =
            LOOT_MODIFIER_SERIALIZERS.register("food_loot", () -> FoodLoot.CODEC);
}