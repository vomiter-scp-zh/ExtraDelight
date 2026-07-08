package com.vomiter.extradelight.common.recipes;

import com.vomiter.extradelight.common.recipes.ingredients.ExtraDelightCompoundIngredient;
import com.vomiter.extradelight.common.recipes.ingredients.ExtraDelightDynamicIngredient;
import com.vomiter.extradelight.common.recipes.ingredients.SizedFluidIngredient;
import net.minecraftforge.common.crafting.CraftingHelper;

public class IngredientRegistry {
    public static void register(){
        CraftingHelper.register(
                SizedFluidIngredient.Serializer.ID,
                SizedFluidIngredient.Serializer.INSTANCE
        );
        CraftingHelper.register(
                ExtraDelightCompoundIngredient.Serializer.ID,
                ExtraDelightCompoundIngredient.Serializer.INSTANCE
        );
        CraftingHelper.register(
                ExtraDelightDynamicIngredient.ID,
                ExtraDelightDynamicIngredient.SERIALIZER
        );
    }
}
