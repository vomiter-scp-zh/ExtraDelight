package com.vomiter.extradelight.mixin.recipe;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.util.AdditionalTagKeyThreadLocal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.common.crafting.conditions.ICondition;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RecipeManager.class)
public class RecipeMixin {
    @WrapMethod(
            method = "fromJson(Lnet/minecraft/resources/ResourceLocation;" +
                    "Lcom/google/gson/JsonObject;" +
                    "Lnet/minecraftforge/common/crafting/conditions/ICondition$IContext;)" +
                    "Lnet/minecraft/world/item/crafting/Recipe;",
            remap = false
    )
    private static Recipe<?> ed$setInExtraDelight(ResourceLocation recipeId, JsonObject jsonObject, ICondition.IContext context, Operation<Recipe<?>> original){
        try {
            if(ExtraDelight.MOD_ID.equals(recipeId.getNamespace())){
                AdditionalTagKeyThreadLocal.isInExtraDelight.set(true);
            }
            return original.call(recipeId, jsonObject, context);
        }
        finally {
            AdditionalTagKeyThreadLocal.isInExtraDelight.remove();
        }
    }

}
