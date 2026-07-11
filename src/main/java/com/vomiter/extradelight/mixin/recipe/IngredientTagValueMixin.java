package com.vomiter.extradelight.mixin.recipe;

import com.vomiter.extradelight.util.AdditionalTagKeyThreadLocal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(Ingredient.TagValue.class)
public class IngredientTagValueMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void ed$addExtraTag(TagKey tagKey, CallbackInfo ci){
        if(
                AdditionalTagKeyThreadLocal.isInIngredient.get()
                        && AdditionalTagKeyThreadLocal.isInExtraDelight.get()
        ){

            if(tagKey.isFor(ForgeRegistries.ITEMS.getRegistryKey())){
                if(tagKey.location().getNamespace().equals("c")){
                    var path = tagKey.location().getPath();
                    List<String> paths = new ArrayList<>();
                    paths.add(path);
                    if(path.endsWith("fish") && path.contains("foods/")) paths.add(path.replace("foods/","").replace("fish", "fishes"));
                    if(path.endsWith("fish")) paths.add(path.replace("fish", "fishes"));
                    if(path.contains("foods/")) paths.add(path.replace("foods/", ""));
                    if(path.endsWith("knife")) paths.add(path.replace("knife","knives"));
                    if(path.equals("foods/berry") || path.equals("berry")) paths.add("berries");


                    AdditionalTagKeyThreadLocal.tagkeys.get().addAll(
                            paths.stream().map(p -> TagKey.create(
                                    ForgeRegistries.ITEMS.getRegistryKey(),
                                    ResourceLocation.fromNamespaceAndPath("forge", p)
                                    )
                            ).collect(Collectors.toSet())
                    );
                }
            }
        }
    }
}
