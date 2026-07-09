package com.vomiter.extradelight.common.items.dynamic;

import com.mojang.datafixers.util.Pair;
import com.vomiter.extradelight.DataComponents;
import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.items.dynamic.api.IDynamic;
import com.vomiter.extradelight.common.items.dynamic.client.DynamicClientHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DynamicToast extends Item implements IDynamic {
	static final ResourceLocation base_model = ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID,
			"extra/dynamics/toast/toast");
	static final ResourceLocation missing_model = ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID,
			"extra/dynamics/toast/empty_toast");

	public DynamicToast(Properties properties) {
		super(properties);
	}

    @Override
    public ResourceLocation getBaseModel() {
        return base_model;
    }

    @Override
    public ResourceLocation getMissingModel() {
        return missing_model;
    }

    @Override
    public String getPrefix() {
        return "extra/dynamics/toast/";
    }

    @Override
	public void appendHoverText(@NotNull ItemStack stack,
                                Level context,
                                @NotNull List<Component> tooltip,
                                @NotNull TooltipFlag isAdvanced) {
        if(context != null && context.isClientSide()) DynamicClientHelper.appendTooltipForToast(stack, context, tooltip, isAdvanced);
	}

    @Override
    public FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        var items = DataComponents.getDynamicIngredients(stack);
        int nutrition = 0;
        float saturation = 0;
        int foodCount = 0;
        List<Pair<MobEffectInstance, Float>> effects = new ArrayList<>();
        for (ItemStack item : items) {
            var food = item.getFoodProperties(entity);
            if(food != null){
                nutrition += food.getNutrition();
                saturation += food.getSaturationModifier();
                effects.addAll(food.getEffects());
                foodCount ++;
            }
        }
        var foodBuilder = new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationMod(saturation / (float) foodCount);
        for (Pair<MobEffectInstance, Float> effect : effects) {
            foodBuilder.effect(
                    effect::getFirst,
                    effect.getSecond()
            );
        }
        return foodBuilder.build();
    }
}
