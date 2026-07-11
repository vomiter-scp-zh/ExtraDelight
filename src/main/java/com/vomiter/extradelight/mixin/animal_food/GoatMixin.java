package com.vomiter.extradelight.mixin.animal_food;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.Fermentation;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.goat.GoatAi;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;
import java.util.stream.Stream;

@Mixin(GoatAi.class)
public class GoatMixin {
    @WrapOperation(
            method = "getTemptations",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/crafting/Ingredient;of([Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;"
            )
    )
    private static Ingredient ed$extraFood(ItemLike[] items, Operation<Ingredient> original){
        if(Arrays.asList(items).contains(Items.WHEAT)){
            var extra = Stream.concat(
                    Arrays.stream(items),
                    Stream.of(Fermentation.SOYBEAN_POD.get(), Fermentation.SOYBEANS.get())
            );
            return original.call((Object) extra.toArray(ItemLike[]::new));
        }
        return original.call((Object) items);
    }

}
