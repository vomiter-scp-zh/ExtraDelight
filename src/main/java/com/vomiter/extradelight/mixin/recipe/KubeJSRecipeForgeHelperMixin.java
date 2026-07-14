package com.vomiter.extradelight.mixin.recipe;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.util.AdditionalTagKeyThreadLocal;
import dev.latvian.mods.kubejs.platform.forge.RecipeForgeHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = RecipeForgeHelper.class, remap = false)
public class KubeJSRecipeForgeHelperMixin {

    @WrapMethod(method = "fromJson")
    private Recipe<?> ed$setExtraDelightScope(
            RecipeSerializer<?> serializer,
            ResourceLocation id,
            JsonObject json,
            Operation<Recipe<?>> original
    ) {
        boolean previous =
                AdditionalTagKeyThreadLocal.isInExtraDelight.get();

        try {
            if (ExtraDelight.MOD_ID.equals(id.getNamespace())) {
                AdditionalTagKeyThreadLocal.isInExtraDelight.set(true);
            }

            return original.call(serializer, id, json);
        } finally {
            if (previous) {
                AdditionalTagKeyThreadLocal.isInExtraDelight.set(true);
            } else {
                AdditionalTagKeyThreadLocal.isInExtraDelight.remove();
            }
        }
    }
}