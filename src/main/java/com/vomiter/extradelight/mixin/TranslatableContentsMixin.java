package com.vomiter.extradelight.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TranslatableContents.class)
public class TranslatableContentsMixin {
    @WrapOperation(
            method = "decompose",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/locale/Language;getOrDefault(Ljava/lang/String;)Ljava/lang/String;"),
            require = 0
    )
    private String mndfix$fixTranslate1(Language instance, String key, Operation<String> original){
        var originalResult = original.call(instance, key);
        if(originalResult.startsWith("tooltip.farmersdelight")){
            return original.call(instance, key.replace("tooltip.farmersdelight", "farmersdelight.tooltip"));
        } else if (originalResult.startsWith("farmersdelight.tooltip")) {
            return original.call(instance, key.replace("farmersdelight.tooltip", "tooltip.farmersdelight"));
        }
        return originalResult;
    }

}