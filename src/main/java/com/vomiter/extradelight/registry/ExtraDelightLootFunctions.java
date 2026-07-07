package com.vomiter.extradelight.registry;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.data.loot.CopyFluidHandlerCapabilityFunction;
import com.vomiter.extradelight.data.loot.CopyItemHandlerCapabilityFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ExtraDelightLootFunctions {

    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTION_TYPES =
            DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, ExtraDelight.MOD_ID);

    public static final RegistryObject<LootItemFunctionType> COPY_ITEM_HANDLER_CAPABILITY =
            LOOT_FUNCTION_TYPES.register(
                    "copy_item_handler_capability",
                    () -> new LootItemFunctionType(new CopyItemHandlerCapabilityFunction.Serializer())
            );

    public static final RegistryObject<LootItemFunctionType> COPY_FLUID_HANDLER_CAPABILITY =
            LOOT_FUNCTION_TYPES.register(
                    "copy_fluid_handler_capability",
                    () -> new LootItemFunctionType(new CopyFluidHandlerCapabilityFunction.Serializer())
            );

    private ExtraDelightLootFunctions() {
    }
}