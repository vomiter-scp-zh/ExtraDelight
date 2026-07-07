package com.vomiter.extradelight.data.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
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
import net.minecraftforge.items.IItemHandler;

public class CopyItemHandlerCapabilityFunction extends LootItemConditionalFunction {

    protected CopyItemHandlerCapabilityFunction(LootItemCondition[] conditions) {
        super(conditions);
    }

    public static Builder<?> copyItemHandlerCapability() {
        return simpleBuilder(CopyItemHandlerCapabilityFunction::new);
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

        IItemHandler source = blockEntity
                .getCapability(ForgeCapabilities.ITEM_HANDLER)
                .orElse(null);

        IItemHandler target = droppedStack
                .getCapability(ForgeCapabilities.ITEM_HANDLER)
                .orElse(null);

        if (source == null || target == null) {
            return droppedStack;
        }

        ItemHandlerCopyUtil.copyInto(source, target);
        return droppedStack;
    }

    @Override
    public LootItemFunctionType getType() {
        return ExtraDelightLootFunctions.COPY_ITEM_HANDLER_CAPABILITY.get();
    }

    public static class Serializer extends LootItemConditionalFunction.Serializer<CopyItemHandlerCapabilityFunction> {

        @Override
        public void serialize(
                JsonObject json,
                CopyItemHandlerCapabilityFunction function,
                JsonSerializationContext context
        ) {
            super.serialize(json, function, context);
        }

        @Override
        public CopyItemHandlerCapabilityFunction deserialize(
                JsonObject json,
                JsonDeserializationContext context,
                LootItemCondition[] conditions
        ) {
            return new CopyItemHandlerCapabilityFunction(conditions);
        }
    }
}