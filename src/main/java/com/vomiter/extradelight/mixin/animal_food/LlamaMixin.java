package com.vomiter.extradelight.mixin.animal_food;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.Fermentation;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;
import java.util.stream.Stream;

@Mixin(Llama.class)
public class LlamaMixin {
    @WrapOperation(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/crafting/Ingredient;of([Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;"
            )
    )
    private static Ingredient ed$extraFood(ItemLike[] items, Operation<Ingredient> original){
        if(Arrays.asList(items).contains(Items.WHEAT)){
            var extra = Stream.concat(
                    Arrays.stream(items),
                    Stream.of(
                            ExtraDelightItems.CORN_HUSK_BUNDLE.get(),
                            ExtraDelightItems.DRIED_CORN_HUSK_BUNDLE.get()
                    ));
            return original.call((Object) extra.toArray(ItemLike[]::new));
        }
        return original.call((Object) items);
    }
}
