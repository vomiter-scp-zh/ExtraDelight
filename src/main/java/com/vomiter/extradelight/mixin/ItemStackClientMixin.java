package com.vomiter.extradelight.mixin;

import com.vomiter.extradelight.DataComponents;
import com.vomiter.extradelight.common.items.dynamic.DynamicJam;
import com.vomiter.extradelight.common.items.dynamic.DynamicToast;
import com.vomiter.extradelight.common.items.dynamic.api.IDynamic;
import com.vomiter.extradelight.common.items.dynamic.client.DynamicClientHelper;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ItemStack.class)
public abstract class ItemStackClientMixin {
    @Shadow
    public abstract Item getItem();

    @Shadow
    public abstract boolean hasCustomHoverName();

    @Inject(
            method = "getHoverName",
            at = @At("HEAD"),
            cancellable = true
    )
    private void extradelight$getDynamicHoverName(CallbackInfoReturnable<Component> cir) {
        ItemStack stack = (ItemStack) (Object) this;

        if (this.hasCustomHoverName()) {
            return;
        }
        Component result = null;
        if (this.getItem() instanceof IDynamic dynamic){
            if(dynamic instanceof DynamicToast){
                result = DynamicClientHelper.getDynamicHoverNameForToast(stack);
            } else if (dynamic instanceof DynamicJam) {
                result = DynamicClientHelper.getDynamicHoverNameForJam(stack);
            }
            if(result != null) cir.setReturnValue(result);
        }
    }
}