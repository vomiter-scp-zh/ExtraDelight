package com.vomiter.extradelight.common;

import com.vomiter.extradelight.common.items.EDFoods;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class ExtraDelightEvents {
    public static void init(){
        MinecraftForge.EVENT_BUS.addListener(ExtraDelightEvents::onFinishUsing);
    }

    public static void onFinishUsing(LivingEntityUseItemEvent.Finish event){
        var food = event.getItem().getFoodProperties(event.getEntity());
        var item = Optional.ofNullable(EDFoods.CONVERT_MAP.get(food)).map(Supplier::get).orElse(Items.AIR);
        var stack = item.getDefaultInstance();
        if(!stack.isEmpty()){
            if (event.getEntity() instanceof Player player){
                player.addItem(stack);
            }
        }
    }
}
