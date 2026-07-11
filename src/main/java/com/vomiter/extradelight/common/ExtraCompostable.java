package com.vomiter.extradelight.common;

import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.Fermentation;
import com.vomiter.extradelight.registry.SummerCitrus;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public final class ExtraCompostable {

    private ExtraCompostable() {
    }

    public static void setup() {
        add(0.2F,
                ExtraDelightItems.APPLE_LEAVES.get(),
                ExtraDelightItems.BREAD_SLICE.get(),
                ExtraDelightItems.CACTUS.get(),
                ExtraDelightItems.CHILI.get(),
                ExtraDelightItems.CHILI_SEEDS.get(),
                ExtraDelightItems.CINNAMON_BARK.get(),
                ExtraDelightItems.CINNAMON_LEAVES.get(),
                ExtraDelightItems.CINNAMON_SAPLING.get(),
                ExtraDelightItems.COFFEE_CHERRIES.get(),
                ExtraDelightItems.CORN_COB.get(),
                ExtraDelightItems.CORN_HUSK.get(),
                ExtraDelightItems.CORN_ON_COB.get(),
                ExtraDelightItems.CORN_SEEDS.get(),
                ExtraDelightItems.CORN_SILK.get(),
                Fermentation.CUCUMBER.get(),
                Fermentation.CUCUMBER_SEED.get(),
                ExtraDelightItems.DRIED_CORN_HUSK.get(),
                ExtraDelightItems.GARLIC.get(),
                ExtraDelightItems.GARLIC_CLOVE.get(),
                ExtraDelightItems.GINGER.get(),
                ExtraDelightItems.GINGER_CUTTING.get(),
                SummerCitrus.GRAPEFRUIT_LEAVES.get(),
                SummerCitrus.GRAPEFRUIT_SAPLING.get(),
                ExtraDelightItems.HAZELNUT_LEAVES.get(),
                ExtraDelightItems.HAZELNUT_SAPLING.get(),
                ExtraDelightItems.HAZELNUTS.get(),
                ExtraDelightItems.HAZELNUTS_IN_SHELL.get(),
                SummerCitrus.LEMON_LEAVES.get(),
                SummerCitrus.LEMON_SAPLING.get(),
                SummerCitrus.LIME_LEAVES.get(),
                SummerCitrus.LIME_SAPLING.get(),
                ExtraDelightItems.MALLOW_ROOT.get(),
                ExtraDelightItems.MARSHMALLOW.get(),
                SummerCitrus.MELON_CHUNKS.get(),
                SummerCitrus.MELON_RIND.get(),
                ExtraDelightItems.MINT.get(),
                SummerCitrus.ORANGE_LEAVES.get(),
                SummerCitrus.ORANGE_SAPLING.get(),
                ExtraDelightItems.PEANUTS.get(),
                ExtraDelightItems.PEANUTS_IN_SHELL.get(),
                ExtraDelightItems.SLICED_APPLE.get(),
                Fermentation.SLICED_CUCUMBER_ITEM.get(),
                ExtraDelightItems.SLICED_GINGER.get(),
                SummerCitrus.SLICED_GRAPEFRUIT.get(),
                SummerCitrus.SLICED_LEMON.get(),
                SummerCitrus.SLICED_LIME.get(),
                ExtraDelightItems.SLICED_ONION.get(),
                SummerCitrus.SLICED_ORANGE.get(),
                ExtraDelightItems.SLICED_POTATO.get(),
                ExtraDelightItems.SLICED_TOMATO.get(),
                Fermentation.SOYBEAN_POD.get(),
                Fermentation.SOYBEANS.get(),
                ExtraDelightItems.UNSHUCKED_CORN.get(),
                ExtraDelightItems.WILD_CHILI_BLOCK.get(),
                Fermentation.WILD_CUCUMBER_ITEM.get(),
                ExtraDelightItems.WILD_GARLIC_BLOCK.get(),
                ExtraDelightItems.WILD_GINGER.get(),
                ExtraDelightItems.WILD_MALLOW_ROOT_BLOCK.get(),
                ExtraDelightItems.WILD_PEANUT_BLOCK.get(),
                Fermentation.WILD_SOYBEAN_ITEM.get()
        );

        add(0.85F,
                ExtraDelightItems.CORN_COB_BUNDLE.get(),
                ExtraDelightItems.CORN_HUSK_BUNDLE.get(),
                ExtraDelightItems.DRIED_CORN_HUSK_BUNDLE.get()
        );

        add(1.0F,
                ExtraDelightItems.BAD_FOOD.get()
        );
    }

    private static void add(float chance, ItemLike... items) {
        for (ItemLike item : items) {
            ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
        }
    }
}