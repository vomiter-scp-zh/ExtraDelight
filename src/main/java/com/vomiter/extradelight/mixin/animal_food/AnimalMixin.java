package com.vomiter.extradelight.mixin.animal_food;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.Fermentation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Animal.class)
public class AnimalMixin {
    @WrapMethod(method = "isFood")
    private boolean ed$extraFood(ItemStack stack, Operation<Boolean> original){
        var self = (Animal)(Object)this;
        if (self.getType().equals(EntityType.COW) || self.getType().equals(EntityType.SHEEP)){
            var food = Ingredient.of(
                    Items.WHEAT,
                    ExtraDelightItems.CORN_HUSK.get(),
                    ExtraDelightItems.DRIED_CORN_HUSK.get(),
                    Fermentation.SOYBEAN_POD.get(),
                    Fermentation.SOYBEANS.get()
            );
            return food.test(stack);
        }
        if(self.getType().equals(EntityType.GOAT)){
            var food = Ingredient.of(
                    Items.WHEAT,
                    Fermentation.SOYBEAN_POD.get(),
                    Fermentation.SOYBEANS.get()
            );
            return food.test(stack);
        }

        return original.call(stack);
    }
}
