package com.vomiter.extradelight;

import net.minecraftforge.common.ForgeConfigSpec;

public class ExtraDelightConfig {
    public static ForgeConfigSpec spec;

    public static final String DEBUG = "debug";
    public static ForgeConfigSpec.BooleanValue ENABLE_DEBUG_MODE;

    public static final String CATEGORY_RECIPE_BOOK = "recipe_book";
    public static ForgeConfigSpec.BooleanValue ENABLE_RECIPE_BOOK_OVEN;

    public static final String CATEGORY_RECIPES = "recipes";

    public static ForgeConfigSpec.BooleanValue DISABLE_REPLACED_DELIGHT_RECIPES;

    public static final String CATEGORY_MINT_SPREAD = "mint_spread";
    public static ForgeConfigSpec.BooleanValue MINT_SPREAD;
    public static ForgeConfigSpec.IntValue MINT_SPREAD_RATE;

    public static final String CATEGORY_SPOOKY = "spooky";
    public static ForgeConfigSpec.BooleanValue ALL_YEAR;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Debug Mode").push(DEBUG);
        ENABLE_DEBUG_MODE = builder
                .comment("Enable Debug Mode for Development")
                .define("enableDebug", false);
        builder.pop();

        builder.comment("Recipe Book").push(CATEGORY_RECIPE_BOOK);
        ENABLE_RECIPE_BOOK_OVEN = builder
                .comment("Should the Oven have a Recipe Book available on its interface?")
                .define("enableRecipeBookOven", false);
        builder.pop();

        builder.comment("Recipe Integration").push(CATEGORY_RECIPES);
        DISABLE_REPLACED_DELIGHT_RECIPES = builder
                .comment(
                        "Disable selected recipes from Farmer's Delight when Extra Delight",
                        "provides a replacement recipe using its own ingredients.",
                        "",
                        "true: Better integration with Extra Delight's recipes and ingredients.",
                        "false: Keep the original recipes, making the game generally easier."
                )
                .define("disableReplacedDelightRecipes", true);
        builder.pop();

        builder.comment("Mint").push(CATEGORY_MINT_SPREAD);
        MINT_SPREAD = builder
                .comment("Should mint spread?")
                .define("shouldMintSpread", true);

        MINT_SPREAD_RATE = builder
                .comment("How fast should mint spread? (Higher number = slower)")
                .defineInRange("mintSpreadRate", 6, 1, 100);
        builder.pop();

        builder.comment("Spooky Things").push(CATEGORY_SPOOKY);
        ALL_YEAR = builder
                .comment("All Year Spooky")
                .define("allYearSpooky", false);
        builder.pop();

        spec = builder.build();
    }
}