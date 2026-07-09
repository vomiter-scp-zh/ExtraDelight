package com.vomiter.extradelight.common.items.dynamic.client;

import com.vomiter.extradelight.DataComponents;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DynamicClientHelper {
    public static Component getDynamicHoverNameForJam(ItemStack stack) {
        var items = DataComponents.getDynamicIngredients(stack);
        for (ItemStack item : items) {
            var itemId = ForgeRegistries.ITEMS.getKey(item.getItem());
            if (itemId == null) continue;
            if(I18n.exists("extradelight.jam." + itemId.getPath())){
                return Component.translatable("extradelight.jam." + itemId.getPath());
            }
        }
        return null;
    }

    public static Component getDynamicHoverNameForToast(ItemStack stack) {
        var items = DataComponents.getDynamicIngredients(stack);
        for (ItemStack item : items) {
            var itemsInJam = DataComponents.getDynamicIngredients(item);
            for (ItemStack itemInJam : itemsInJam) {
                var itemJamId = ForgeRegistries.ITEMS.getKey(itemInJam.getItem());
                if(I18n.exists("extradelight.jam." + itemJamId.getPath())){
                    return Component
                            .translatable(
                                    "item.extradelight.dynamic_toast",
                                    itemInJam.getHoverName()
                            );
                }
            }
        }
        return null;
    }

    public static void appendTooltipForJam(ItemStack stack, Level context, List<Component> tooltip, TooltipFlag isAdvanced){
        var items = DataComponents.getDynamicIngredients(stack);
        if(context != null && context.isClientSide()){
            if (Screen.hasShiftDown()) {
                if (!items.isEmpty()) {
                    tooltip.add(Component.translatable("tooltip.dynamic.ingredients"));
                    for (ItemStack s : items) {
                        tooltip.add(Component.literal(" - ").append(Component.translatable(s.getDescriptionId())));
                    }
                }
            } else {
                tooltip.add(Component.translatable("tooltip.see_more")
                        .withStyle(style -> style.withColor(0xFFAAAAAA)));
            }
        }
    }

    public static void appendTooltipForToast(ItemStack stack, Level context, List<Component> tooltip, TooltipFlag isAdvanced) {
        var items = DataComponents.getDynamicIngredients(stack);
        if(context != null && context.isClientSide()){
            if (Screen.hasShiftDown()) {
                if (!items.isEmpty()) {
                    tooltip.add(Component.translatable("tooltip.dynamic.ingredients"));
                    for (ItemStack s : items) {
                        tooltip.add(Component.literal(" - ")
                                .append(s.getHoverName()));
                    }
                }
            } else {
                tooltip.add(Component.translatable("tooltip.see_more")
                        .withStyle(style -> style.withColor(0xFFAAAAAA)));
            }
        }
    }
}
