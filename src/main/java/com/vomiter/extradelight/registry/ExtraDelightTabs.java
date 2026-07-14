package com.vomiter.extradelight.registry;

import com.vomiter.extradelight.DataComponents;
import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.items.dynamic.DynamicDelegate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ExtraDelightTabs {
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
			ExtraDelight.MOD_ID);

	public static final RegistryObject<CreativeModeTab> TAB = TABS.register("tab",
			() -> CreativeModeTab.builder().title(Component.translatable("itemGroup.extradelight.tab"))
					.icon(() -> new ItemStack(ExtraDelightItems.WOODEN_SPOON.get()))
					.displayItems((parameters, output) -> {
						for (RegistryObject<? extends Item> i : ExtraDelightItems.ITEMS.getEntries()){
                            if (i == ExtraDelightItems.EASTER_EGG) continue;
                            if (i == ExtraDelightItems.DYNAMIC_JAM){
                                DynamicDelegate.getDelegates().forEach(delegateItem -> {
                                    var ingredient = delegateItem.getDefaultInstance();

                                    var jam = ExtraDelightItems.DYNAMIC_JAM.get().getDefaultInstance();
                                    DataComponents.setDynamicIngredient(jam, List.of(ingredient.copy(), ingredient.copy(), ingredient.copy()));
                                    output.accept(jam);

                                    var toast = ExtraDelightItems.DYNAMIC_TOAST.get().getDefaultInstance();
                                    DataComponents.setDynamicIngredient(toast, List.of(jam.copy()));
                                    output.accept(toast);
                                });
                                continue;
                            }
                            if (i == ExtraDelightItems.DYNAMIC_TOAST){
                                continue;
                            }
                            output.accept(i.get());

                        }
					}).build());


	public static final RegistryObject<CreativeModeTab> AESTHETICS = TABS.register("aesthetics",
			() -> CreativeModeTab.builder().title(Component.translatable("itemGroup.extradelight.aesthetics"))
					.icon(() -> new ItemStack(AestheticBlocks.WALLPAPER_BLOCKS.get(0).get()))
					.displayItems((parameters, output) -> {
						for (RegistryObject<? extends Item> i : AestheticBlocks.ITEMS.getEntries())
							output.accept(i.get());
					}).build());


}