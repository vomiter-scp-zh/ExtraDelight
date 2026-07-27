package com.vomiter.extradelight.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.vomiter.extradelight.DataComponents;
import com.vomiter.extradelight.common.complex.dynamic_feast.DynamicContainerFeastBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;

@Mixin(value = CookingPotBlockEntity.class, remap = false)
public abstract class CookingPotMixin {
    @Shadow
    @Final
    private ItemStackHandler inventory;

    @Shadow
    public abstract boolean isContainerValid(ItemStack containerItem);

    @WrapMethod(method = "useHeldItemOnMeal")
    private ItemStack ed$useHeld(ItemStack container, Operation<ItemStack> original){
        ItemStack cached = container.copyWithCount(1);
        var result = original.call(container);
        if (result.getItem() instanceof DynamicContainerFeastBlockItem){
            DataComponents.setStack(result, DataComponents.CONTAINER, cached);
        }
        return result;
    }

    @WrapMethod(method = "useStoredContainersOnMeal")
    private void ed$useStored(Operation<Void> original){
        ItemStack mealStack = this.inventory.getStackInSlot(6);
        ItemStack containerInputStack = this.inventory.getStackInSlot(7);
        ItemStack cachedContainer = containerInputStack.copyWithCount(1);
        ItemStack outputStack = this.inventory.getStackInSlot(8);
        if(mealStack.getItem() instanceof DynamicContainerFeastBlockItem){
            if (this.isContainerValid(containerInputStack) && outputStack.getCount() < outputStack.getMaxStackSize()) {
                int smallerStackCount = Math.min(mealStack.getCount(), containerInputStack.getCount());
                int mealCount = Math.min(smallerStackCount, mealStack.getMaxStackSize() - outputStack.getCount());
                ItemStack simulatedMealStack = mealStack.copy();
                DataComponents.setStack(simulatedMealStack, DataComponents.CONTAINER, cachedContainer);
                if (outputStack.isEmpty()) {
                    containerInputStack.shrink(mealCount);
                    this.inventory.setStackInSlot(8, mealStack.split(mealCount));
                    DataComponents.setStack(inventory.getStackInSlot(8), DataComponents.CONTAINER, cachedContainer);
                } else if (ItemStack.isSameItemSameTags(outputStack, simulatedMealStack)) {
                    mealStack.shrink(mealCount);
                    containerInputStack.shrink(mealCount);
                    outputStack.grow(mealCount);
                }
            }

        } else original.call();

    }
}
