package com.vomiter.extradelight.mixin;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.util.GsonHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;

import java.util.Objects;

@Mixin(CuttingBoardRecipe.Serializer.class)
public class CuttingMixin {
    @WrapOperation(
            method = "fromJson(Lnet/minecraft/resources/ResourceLocation;Lcom/google/gson/JsonObject;)Lvectorwing/farmersdelight/common/crafting/CuttingBoardRecipe;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/GsonHelper;getAsString(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;")
    )
    private String ed$getSound(JsonObject jsonObject, String key, String fallback, Operation<String> original){
        if(Objects.equals(key, "sound")){
            if(jsonObject.has(key) && jsonObject.get(key).isJsonObject() && jsonObject.get(key).getAsJsonObject().has("sound_id")){
                return GsonHelper.getAsString(jsonObject.get(key).getAsJsonObject(), "sound_id", fallback);
            }
        }
        return original.call(jsonObject, key, fallback);
    }
}
