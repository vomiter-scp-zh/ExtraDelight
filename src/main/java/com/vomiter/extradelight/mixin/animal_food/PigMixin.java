package com.vomiter.extradelight.mixin.animal_food;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.Fermentation;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;
import java.util.stream.Stream;

@Mixin(Pig.class)
public class PigMixin {
    @WrapOperation(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/crafting/Ingredient;of([Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;"
            )
    )
    private static Ingredient ed$extraFood(ItemLike[] items, Operation<Ingredient> original){
        if(Arrays.asList(items).contains(Items.CARROT)){
            var extra = Stream.concat(
                    Arrays.stream(items),
                    Stream.of(ExtraDelightItems.CORN_COB.get(), ExtraDelightItems.GINGER.get(),
                            ExtraDelightItems.MALLOW_ROOT.get(), Fermentation.CUCUMBER.get())
            );
            return original.call((Object) extra.toArray(ItemLike[]::new));
        }
        return original.call((Object) items);
    }
}
