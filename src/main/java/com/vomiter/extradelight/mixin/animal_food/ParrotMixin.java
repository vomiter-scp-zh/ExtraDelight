package com.vomiter.extradelight.mixin.animal_food;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.Fermentation;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

@Mixin(Parrot.class)
public class ParrotMixin {
    @WrapOperation(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/google/common/collect/Sets;newHashSet([Ljava/lang/Object;)Ljava/util/HashSet;"
            )
    )
    private static HashSet<Object> ed$extraFood(Object[] items, Operation<HashSet<Object>> original){
        if(!(Arrays.stream(items).findFirst().get() instanceof Item)) return original.call(items);
        if(Arrays.asList(items).contains(Items.WHEAT_SEEDS)){
            var extra = Stream.concat(
                    Arrays.stream(items),
                    Stream.of(ExtraDelightItems.CORN_SEEDS.get(), ExtraDelightItems.CHILI_SEEDS.get(),
                            ExtraDelightItems.HAZELNUTS.get(), ExtraDelightItems.PEANUTS.get(),
                            ExtraDelightItems.SUNFLOWER_SEEDS.get(), Fermentation.CUCUMBER_SEED.get(), Fermentation.SOYBEANS.get())
            );
            return original.call((Object) extra.toArray(Object[]::new));
        }
        return original.call((Object) items);
    }
}
