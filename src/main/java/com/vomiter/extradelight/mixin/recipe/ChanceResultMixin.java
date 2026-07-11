package com.vomiter.extradelight.mixin.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.vomiter.extradelight.ExtraDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

@Mixin(value = ChanceResult.class, remap = false)
public class ChanceResultMixin {

    @WrapMethod(method = "deserialize")
    private static ChanceResult ed$getItemId(
            JsonElement json,
            Operation<ChanceResult> original
    ) {
        if (!json.isJsonObject()) {
            return original.call(json);
        }

        JsonObject object = json.getAsJsonObject();
        JsonElement itemElement = object.get("item");


        if (itemElement == null || !itemElement.isJsonObject()) {
            return original.call(json);
        }

        try {
            JsonObject itemObject = itemElement.getAsJsonObject();

            String id = GsonHelper.getAsString(itemObject, "id");
            int count = GsonHelper.getAsInt(itemObject, "count", 1);
            float chance = GsonHelper.getAsFloat(object, "chance", 1.0F);

            ResourceLocation itemId = ResourceLocation.tryParse(id);
            Item item = ForgeRegistries.ITEMS.getValue(itemId);

            return new ChanceResult(new ItemStack(item, count), chance);
        } catch (RuntimeException exception) {
            ExtraDelight.LOGGER.warn(
                    "Failed to parse ChanceResult using 1.21 item format; falling back to the original deserializer. JSON: {}",
                    json,
                    exception
            );

            return original.call(json);
        }
    }
    /**
     * 1.21 format:
     *   "result": [
     *       {
     *       "chance": 0.1,
     *       "item": {
     *         "count": 1,
     *         "id": "minecraft:flint"
     *       }
     *     }
     *   ],
     *
     *   1.20 format:
     *     "result": [
     *     {
     *       "count": 4,
     *       "item": "minecraft:brick"
     *     }
     *   ],
     */

}
