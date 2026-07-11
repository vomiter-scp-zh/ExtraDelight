package com.vomiter.extradelight.mixin.recipe;

import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.vomiter.extradelight.util.AdditionalTagKeyThreadLocal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.AbstractIngredient;
import net.minecraftforge.common.crafting.CompoundIngredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Arrays;

@Mixin(Ingredient.class)
public abstract class IngredientMixin {
    @Shadow
    public abstract boolean isEmpty();

    @Shadow
    @Nullable
    private ItemStack[] itemStacks;

    @Shadow
    private int invalidationCounter;

    @WrapMethod(method = "fromJson(Lcom/google/gson/JsonElement;Z)" +
            "Lnet/minecraft/world/item/crafting/Ingredient;")
    private static Ingredient ed$wrapFromJson(
            JsonElement p_289022_,
            boolean p_288974_,
            Operation<Ingredient> original){
        try{
            AdditionalTagKeyThreadLocal.isInIngredient.set(true);
            var originalResult = original.call(p_289022_, p_288974_);
            var additionalTags = AdditionalTagKeyThreadLocal.tagkeys.get();

            if(additionalTags.isEmpty() || originalResult instanceof AbstractIngredient){
                return originalResult;
            }

            var additionalTagIngredients = additionalTags.stream()
                    .map(Ingredient.TagValue::new);
            var additionalIngredient = Ingredient.fromValues(additionalTagIngredients);
            return CompoundIngredient.of(originalResult, additionalIngredient);
        } finally {
            AdditionalTagKeyThreadLocal.isInIngredient.remove();
            AdditionalTagKeyThreadLocal.tagkeys.remove();
        }
    }


    @Inject(method = "getItems", at = @At("RETURN"), cancellable = true)
    private void ed$omitEmptyCommonTag(CallbackInfoReturnable<ItemStack[]> cir){
        var originalResult = Arrays.stream(cir.getReturnValue()).distinct().toArray(ItemStack[]::new);
        boolean notEmpty = Arrays.stream(originalResult).anyMatch(stack -> !stack.is(Items.BARRIER));
        boolean hasEmpty = Arrays.stream(originalResult).anyMatch(stack -> stack.is(Items.BARRIER));
        if (notEmpty && hasEmpty){
            var skipped = Arrays.stream(originalResult)
                    .filter(stack
                            -> !stack.is(Items.BARRIER)
                            &&
                            !(
                                    stack.getHoverName().getString().contains("Empty Tag: c:")
                                    || stack.getHoverName().getString().contains("Empty Tag: forge:")
                            )
                    ).toList();
            if (!skipped.isEmpty()){
                cir.setReturnValue(skipped.toArray(ItemStack[]::new));
            }
        } else {
            cir.setReturnValue(originalResult);
        }

    }


}
