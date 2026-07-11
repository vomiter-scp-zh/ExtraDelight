package com.vomiter.extradelight.mixin.recipe;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

@Mixin(value = CookingPotRecipe.Serializer.class, remap = false)
public class CookingMixin {
    @WrapOperation(
            method = "fromJson(Lnet/minecraft/resources/ResourceLocation;Lcom/google/gson/JsonObject;)Lvectorwing/farmersdelight/common/crafting/CookingPotRecipe;",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/crafting/CraftingHelper;getItemStack(Lcom/google/gson/JsonObject;Z)Lnet/minecraft/world/item/ItemStack;")
    )
    private static ItemStack ed$getItemId(JsonObject jsonObject, boolean readNBT, Operation<ItemStack> original){
        if(jsonObject.has("id") && !jsonObject.has("item")){
            var copy = jsonObject.deepCopy();
            copy.addProperty("item", jsonObject.get("id").getAsString());
            return original.call(copy, readNBT);
        }
        return original.call(jsonObject, readNBT);
    }

}
