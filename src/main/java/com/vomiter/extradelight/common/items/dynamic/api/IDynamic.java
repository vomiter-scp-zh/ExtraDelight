package com.vomiter.extradelight.common.items.dynamic.api;

import com.vomiter.extradelight.DataComponents;
import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.items.dynamic.DynamicDelegate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public interface IDynamic {

	default List<ResourceLocation> getPieces(ItemStack itemStack){
        List<ResourceLocation> i = new ArrayList<>();
        var items = DataComponents.getDynamicIngredients(itemStack);
        String prefix = getPrefix();
        for (ItemStack ingredient : items) {
            if(ingredient.getItem() instanceof IDynamic){
                for (ItemStack dynamicIngredient : DataComponents.getDynamicIngredients(ingredient)) {
                    var ingRc = DynamicDelegate.getDelegateString(dynamicIngredient.getItem());
                    if(ingRc == null) continue;
                    i.add(ExtraDelight.modLoc(prefix + "dynamic_jam/" + ingRc));
                }
            }
            else {
                var ingRc = DynamicDelegate.getDelegateString(ingredient.getItem());
                if(ingRc == null) continue;
                i.add(ExtraDelight.modLoc(prefix + ingRc));
            }
        }
        if(i.size() <= 1){
            i.add(getMissingModel());
        }

        return i;
    }

    ResourceLocation getBaseModel();
    ResourceLocation getMissingModel();
    String getPrefix();

}
