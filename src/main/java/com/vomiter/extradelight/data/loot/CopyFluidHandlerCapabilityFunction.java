package com.vomiter.extradelight.data.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.registry.ExtraDelightLootFunctions;
import com.vomiter.extradelight.util.ItemHandlerCopyUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

public class CopyFluidHandlerCapabilityFunction extends LootItemConditionalFunction {

    protected CopyFluidHandlerCapabilityFunction(LootItemCondition[] conditions) {
        super(conditions);
    }

    public static Builder<?> copyItemHandlerCapability() {
        return simpleBuilder(CopyFluidHandlerCapabilityFunction::new);
    }

    @Override
    protected ItemStack run(ItemStack droppedStack, LootContext context) {
        if (droppedStack.isEmpty() || droppedStack.getCount() != 1) {
            return droppedStack;
        }

        BlockEntity blockEntity = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
        if (blockEntity == null) {
            return droppedStack;
        }

        IFluidHandler source = blockEntity
                .getCapability(ForgeCapabilities.FLUID_HANDLER)
                .orElse(null);

        IFluidHandler target = droppedStack
                .getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM)
                .orElse(null);
        if(target == null) ExtraDelight.LOGGER.info("[ExtraDelight Debug]Fluid Handler of {} is null", droppedStack);

        if (source == null || target == null) {
            return droppedStack;
        }

        var amountFilled = target.fill(source.getFluidInTank(0), IFluidHandler.FluidAction.EXECUTE);
        ExtraDelight.LOGGER.info("[ExtraDelight Debug]Fluid Filled = {}", amountFilled);
        return droppedStack;
    }

    @Override
    public LootItemFunctionType getType() {
        return ExtraDelightLootFunctions.COPY_FLUID_HANDLER_CAPABILITY.get();
    }

    public static class Serializer extends LootItemConditionalFunction.Serializer<CopyFluidHandlerCapabilityFunction> {

        @Override
        public void serialize(
                JsonObject json,
                CopyFluidHandlerCapabilityFunction function,
                JsonSerializationContext context
        ) {
            super.serialize(json, function, context);
        }

        @Override
        public CopyFluidHandlerCapabilityFunction deserialize(
                JsonObject json,
                JsonDeserializationContext context,
                LootItemCondition[] conditions
        ) {
            return new CopyFluidHandlerCapabilityFunction(conditions);
        }
    }
}