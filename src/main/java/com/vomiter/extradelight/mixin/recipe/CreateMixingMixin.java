package com.vomiter.extradelight.mixin.recipe;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ProcessingOutput.class)
public class CreateMixingMixin {
    @WrapOperation(
            method = "deserialize",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/GsonHelper;getAsString(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;"),
            require = 0
    )
    private static String ed$getId(JsonObject jsonObject, String key, Operation<String> original){
        if (key.equals("item") && jsonObject.has("id") && !jsonObject.has("item") && jsonObject.get("id").isJsonPrimitive()){
            return jsonObject.get("id").getAsString();
        }
        return original.call(jsonObject, key);
    }
}
