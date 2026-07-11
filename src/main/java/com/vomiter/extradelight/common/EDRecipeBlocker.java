package com.vomiter.extradelight.common;

import com.vomiter.extradelight.ExtraDelightConfig;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;
import java.util.stream.Stream;

public class EDRecipeBlocker {
    static final List<ResourceLocation> REPLACED_RECIPES = Stream.of(
            "apple_pie",
            "chocolate_pie",
            "fruit_salad",
            "honey_cookie",
            "honey_glazed_ham_block",
            "melon_popsicle",
            "mixed_salad",
            "pie_crust",
            "roast_chicken_block",
            "shepherds_pie_block",
            "sweet_berry_cheesecake",
            "sweet_berry_cookie",
            "wheat_dough_from_egg",
            "wheat_dough_from_water"
    ).map(s -> ResourceLocation.fromNamespaceAndPath("farmersdelight", s))
            .toList();
    public static boolean shouldBlock(ResourceLocation id, Recipe recipe, RegistryAccess registryAccess){
        if(ExtraDelightConfig.DISABLE_REPLACED_DELIGHT_RECIPES.get()){
            return REPLACED_RECIPES.contains(id);
        }
        return false;
    }
}
