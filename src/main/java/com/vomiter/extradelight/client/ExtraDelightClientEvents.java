package com.vomiter.extradelight.client;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.client.dynamic_food.DynamicFoodGeometryLoader;
import com.vomiter.extradelight.registry.ExtraDelightBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class ExtraDelightClientEvents {
    public static void init(IEventBus modBus) {
        modBus.addListener(ExtraDelightClientEvents::registerGeometryLoaders);
        modBus.addListener(ExtraDelightClientEvents::registerExtraModels);
        modBus.addListener(ExtraDelightClientEvents::registerBlockColors);
        modBus.addListener(ExtraDelightClientEvents::registerItemColors);

        MinecraftForge.EVENT_BUS.addListener(ExtraDelightClientEvents::addToolTip);
    }

    public static void addToolTip(ItemTooltipEvent event) {
        if (feasts.stream().anyMatch(s -> event.getItemStack().is(s.get()))) {
            event.getToolTip()
                    .add(Component.translatable(ExtraDelight.MOD_ID + ".tooltip.feast").withStyle(ChatFormatting.BLUE));
        }

        if (servings.stream().anyMatch(s -> event.getItemStack().is(s.get()))) {
            event.getToolTip().add(
                    Component.translatable(ExtraDelight.MOD_ID + ".tooltip.serving").withStyle(ChatFormatting.BLUE));
        }

        if (!ModList.get().isLoaded("butchercraft"))
            if (butchercraft.stream().anyMatch(s -> event.getItemStack().is(s.get()))) {
                event.getToolTip().add(Component.translatable(ExtraDelight.MOD_ID + ".tooltip.butchercraft")
                        .withStyle(ChatFormatting.RED));
            }
    }

    public static Set<Supplier<Item>> feasts = new HashSet<Supplier<Item>>();

    public static Set<Supplier<Item>> servings = new HashSet<Supplier<Item>>();

    public static Set<Supplier<Item>> butchercraft = new HashSet<Supplier<Item>>();


    public static void registerGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(DynamicFoodGeometryLoader.ID.getPath(), new DynamicFoodGeometryLoader());
        event.register(BlockStateItemModel.ID.getPath(), new BlockStateItemModel.Loader());
    }

    public static void registerExtraModels(ModelEvent.RegisterAdditional event) {
        Map<ResourceLocation, Resource> rrs
                = Minecraft.getInstance().getResourceManager().listResources("models/extra",
                (p_215600_) -> {
                    return p_215600_.getPath().endsWith(".json");
                });

        rrs.forEach((rl, r) -> {
            String s = rl.toString();

            s = s.substring(s.indexOf('/') + 1, s.indexOf('.'));

            ExtraDelight.LOGGER.debug(s);

            ResourceLocation rl2 = ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), s);

            event.register(rl2);
        });
    }

    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(
                (state, level, pos, tintIndex) -> {
                    if (level == null || pos == null) {
                        return FoliageColor.getDefaultColor();
                    }

                    return BiomeColors.getAverageFoliageColor(level, pos);
                },
                ExtraDelightBlocks.APPLE_LEAVES.get(),
                ExtraDelightBlocks.HAZELNUT_LEAVES.get()
        );
    }

    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(
                (stack, tintIndex) -> FoliageColor.getDefaultColor(),
                ExtraDelightBlocks.APPLE_LEAVES.get(),
                ExtraDelightBlocks.HAZELNUT_LEAVES.get()
        );
    }
}
