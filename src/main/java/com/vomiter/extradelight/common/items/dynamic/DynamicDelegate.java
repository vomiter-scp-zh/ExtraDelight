package com.vomiter.extradelight.common.items.dynamic;

import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.SummerCitrus;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicDelegate {
    private static Map<Item, String> DELEGATE_MAP;

    public static String getDelegateString(Item delegate){
        return getDelegateMap().getOrDefault(delegate, null);
    }

    public static Pair<Item, String> getDelegateFromIngredient(Ingredient ingredient){
        for (Item delegate : getDelegates()) {
            if(ingredient.test(delegate.getDefaultInstance())){
                String name = getDelegateMap().get(delegate);
                return Pair.of(delegate, name);
            }
        }
        return null;
    }

    public static List<Item> getDelegates(){
        return List.of(
                Items.APPLE,
                Items.GOLDEN_APPLE,
                Items.CARROT,
                Items.MELON_SLICE,
                Items.GLOW_BERRIES,
                Items.CHORUS_FRUIT,
                Items.SWEET_BERRIES,
                ExtraDelightItems.MINT.get(),
                SummerCitrus.LEMON.get(),
                SummerCitrus.LIME.get(),
                SummerCitrus.GRAPEFRUIT.get(),
                SummerCitrus.ORANGE.get()
        );
    }

    static Map<Item, String> getDelegateMap(){
        if(DELEGATE_MAP != null) return DELEGATE_MAP;

        HashMap<Item, String> map = new HashMap<>();
        map.put(Items.MELON_SLICE, "melon");
        getDelegates().forEach(item -> {
            map.computeIfAbsent(
                    item,
                    p -> ForgeRegistries
                            .ITEMS.getKey(p)
                            .getPath()
            );
        });
        DELEGATE_MAP = map;
        return map;
    }


}
