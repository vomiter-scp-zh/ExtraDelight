package com.vomiter.extradelight.mixin;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShapelessRecipe.Serializer.class)
public class ShapelessMixin {
    @WrapOperation(
            method = "fromJson(Lnet/minecraft/resources/ResourceLocation;Lcom/google/gson/JsonObject;)Lnet/minecraft/world/item/crafting/ShapelessRecipe;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/ShapedRecipe;itemStackFromJson(Lcom/google/gson/JsonObject;)Lnet/minecraft/world/item/ItemStack;")
    )
    private static ItemStack ed$getItemId(JsonObject jsonObject, Operation<ItemStack> original){
        if(jsonObject.has("id") && !jsonObject.has("item")){
            var copy = jsonObject.deepCopy();
            copy.addProperty("item", jsonObject.get("id").getAsString());
            return original.call(copy);
        }
        return original.call(jsonObject);
    }
}
