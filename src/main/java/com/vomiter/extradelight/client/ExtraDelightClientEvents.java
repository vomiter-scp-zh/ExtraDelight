package com.vomiter.extradelight.client;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.client.dynamic_food.DynamicFoodGeometryLoader;
import com.vomiter.extradelight.common.complex.cabinet.countercabinet.CounterCabinetRenderer;
import com.vomiter.extradelight.common.complex.cabinet.countercabinet.CounterCabinetScreen;
import com.vomiter.extradelight.common.complex.cabinet.sink.SinkCabinetScreen;
import com.vomiter.extradelight.common.complex.cabinet.sink.SinkRenderer;
import com.vomiter.extradelight.common.complex.cornhuskdoll.CornHuskDollRenderer;
import com.vomiter.extradelight.common.complex.displays.food.FoodDisplayRenderer;
import com.vomiter.extradelight.common.complex.displays.food.FoodDisplayScreen;
import com.vomiter.extradelight.common.complex.displays.fruitbowl.FruitBowlRenderer;
import com.vomiter.extradelight.common.complex.displays.knife.KnifeBlockRenderer;
import com.vomiter.extradelight.common.complex.displays.knife.KnifeBlockScreen;
import com.vomiter.extradelight.common.complex.displays.spice.SpiceRackRenderer;
import com.vomiter.extradelight.common.complex.displays.spice.SpiceRackScreen;
import com.vomiter.extradelight.common.complex.displays.wreath.WreathRenderer;
import com.vomiter.extradelight.common.complex.displays.wreath.WreathScreen;
import com.vomiter.extradelight.common.complex.fluid_handler.funnel.FunnelRenderer;
import com.vomiter.extradelight.common.complex.fluid_handler.keg.KegRenderer;
import com.vomiter.extradelight.common.complex.jar.JarRenderer;
import com.vomiter.extradelight.common.complex.jardisplay.JarDisplayRenderer;
import com.vomiter.extradelight.common.complex.portable.chocolatebox.ChocolateBoxRenderer;
import com.vomiter.extradelight.common.complex.portable.picnicbasket.PicnicBasketRenderer;
import com.vomiter.extradelight.common.complex.portable.picnicbasket.PicnicBasketScreen;
import com.vomiter.extradelight.common.complex.workstations.chiller.ChillerScreen;
import com.vomiter.extradelight.common.complex.workstations.doughshaping.DoughShapingScreen;
import com.vomiter.extradelight.common.complex.workstations.dryingrack.DryingRackRenderer;
import com.vomiter.extradelight.common.complex.workstations.evaporator.EvaporatorRenderer;
import com.vomiter.extradelight.common.complex.workstations.juicer.JuicerRenderer;
import com.vomiter.extradelight.common.complex.workstations.meltingpot.MeltingPotScreen;
import com.vomiter.extradelight.common.complex.workstations.mixingbowl.MixingBowlRenderer;
import com.vomiter.extradelight.common.complex.workstations.mixingbowl.MixingBowlScreen;
import com.vomiter.extradelight.common.complex.workstations.mortar.MortarRenderer;
import com.vomiter.extradelight.common.complex.workstations.oven.OvenScreen;
import com.vomiter.extradelight.common.complex.workstations.vat.VatScreen;
import com.vomiter.extradelight.gui.StyleableScreen;
import com.vomiter.extradelight.registry.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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
        modBus.addListener(ExtraDelightClientEvents::registerParticles);
        modBus.addListener(ExtraDelightClientEvents::clientSetup);

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

    public static void registerParticles(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ExtraDelightParticles.CITRUS_PETALS.get(), PetalParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ExtraDelightParticles.HAZELNUT_PETALS.get(), PetalParticle.Factory::new);
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
        BlockColor biomeFoliageColor = (state, level, pos, tintIndex) ->
                level != null && pos != null
                        ? BiomeColors.getAverageFoliageColor(level, pos)
                        : FoliageColor.getDefaultColor();

        event.register(
                biomeFoliageColor,
                ExtraDelightBlocks.APPLE_LEAVES.get(),
                ExtraDelightBlocks.CINNAMON_LEAVES.get(),
                ExtraDelightBlocks.HAZELNUT_LEAVES.get(),
                SummerCitrus.GRAPEFRUIT_LEAVES.get(),
                SummerCitrus.LEMON_LEAVES.get(),
                SummerCitrus.LIME_LEAVES.get(),
                SummerCitrus.ORANGE_LEAVES.get()
        );

        event.register(
                (state, level, pos, tintIndex) -> FoliageColor.getEvergreenColor(),
                AestheticBlocks.WREATHS
                        .get(AestheticBlocks.WOOD.spruce.ordinal())
                        .get()
        );

        event.register(
                (state, level, pos, tintIndex) -> FoliageColor.getBirchColor(),
                AestheticBlocks.WREATHS
                        .get(AestheticBlocks.WOOD.birch.ordinal())
                        .get()
        );

        event.register(
                biomeFoliageColor,
                AestheticBlocks.getRegistryListAsBlocks(
                        AestheticBlocks.WREATHS.stream()
                                .filter(entry -> {
                                    String id = entry.getId().getPath();

                                    return !id.contains("cherry")
                                            && !id.contains("warped")
                                            && !id.contains("crimson")
                                            && !id.contains("azalea")
                                            && !id.contains("cinnamon")
                                            && !id.contains("spruce")
                                            && !id.contains("birch");
                                })
                                .toList()
                )
        );
    }

    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        BlockColors blockColors = event.getBlockColors();

        ItemColor blockItemColor = (stack, tintIndex) -> {
            if (!(stack.getItem() instanceof BlockItem blockItem)) {
                return -1;
            }

            return blockColors.getColor(
                    blockItem.getBlock().defaultBlockState(),
                    null,
                    null,
                    tintIndex
            );
        };

        event.register(
                blockItemColor,
                ExtraDelightBlocks.APPLE_LEAVES.get().asItem(),
                ExtraDelightBlocks.CINNAMON_LEAVES.get().asItem(),
                ExtraDelightBlocks.HAZELNUT_LEAVES.get().asItem(),
                SummerCitrus.GRAPEFRUIT_LEAVES.get().asItem(),
                SummerCitrus.LEMON_LEAVES.get().asItem(),
                SummerCitrus.LIME_LEAVES.get().asItem(),
                SummerCitrus.ORANGE_LEAVES.get().asItem()
        );

        event.register(
                blockItemColor,
                AestheticBlocks.getRegistryListAsItems(
                        AestheticBlocks.WREATH_ITEMS
                )
        );
    }


    public static void doFluidRenderLayer() {
        applyFluidRenderType(ExtraDelightBlocks.COOKING_OIL_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.VINEGAR_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.GRAVY_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.GLOW_BERRY_JUICE_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.SWEET_BERRY_JUICE_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.TOMATO_JUICE_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.CACTUS_JUICE_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.APPLE_CIDER_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.HOT_COCOA_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.MELON_JUICE_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.EGG_MIX_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.BBQ_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.KETCHUP_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.MAYO_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.BROTH_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.CARAMEL_SAUCE_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.MILKSHAKE_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.WHIPPED_CREAM_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.JAM_FLUID_BLOCK.get());
        //applyFluidRenderType(ExtraDelightBlocks.GOLDEN_JAM_FLUID_BLOCK.get());
        //applyFluidRenderType(ExtraDelightBlocks.GLOW_JAM_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.TEA_FLUID_BLOCK.get());
        applyFluidRenderType(Fermentation.PICKLE_JUICE_FLUID_BLOCK.get());
        applyFluidRenderType(SummerCitrus.LEMON_JUICE_FLUID_BLOCK.get());
        applyFluidRenderType(SummerCitrus.LIME_JUICE_FLUID_BLOCK.get());
        applyFluidRenderType(SummerCitrus.ORANGE_JUICE_FLUID_BLOCK.get());
        applyFluidRenderType(SummerCitrus.GRAPEFRUIT_JUICE_FLUID_BLOCK.get());
        applyFluidRenderType(ExtraDelightBlocks.EGG_WHITE_FLUID_BLOCK.get());
    }

    public static void applyFluidRenderType(LiquidBlock liquid) {
        ItemBlockRenderTypes.setRenderLayer(liquid.getFluid().getFlowing(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(liquid.getFluid().getSource(), RenderType.translucent());
    }

    public static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            doFluidRenderLayer();

            MenuScreens.register(ExtraDelightContainers.OVEN_MENU.get(), OvenScreen::new);
            MenuScreens.register(ExtraDelightContainers.MIXING_BOWL_MENU.get(), MixingBowlScreen::new);
            MenuScreens.register(ExtraDelightContainers.DOUGH_SHAPING_MENU.get(), DoughShapingScreen::new);
            MenuScreens.register(ExtraDelightContainers.MELTING_POT_MENU.get(), MeltingPotScreen::new);
            MenuScreens.register(ExtraDelightContainers.CHILLER_MENU.get(), ChillerScreen::new);
            MenuScreens.register(ExtraDelightContainers.VAT_MENU.get(), VatScreen::new);
            MenuScreens.register(ExtraDelightContainers.STYLE_MENU.get(), StyleableScreen::new);

            BlockEntityRenderers.register(ExtraDelightBlockEntities.MIXING_BOWL.get(), MixingBowlRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.MORTAR.get(), MortarRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.DRYING_RACK.get(), DryingRackRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.EVAPORATOR.get(), EvaporatorRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.JUICER.get(), JuicerRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.FUNNEL.get(), FunnelRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.CHOCOLATE_BOX.get(), ChocolateBoxRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.KEG.get(), KegRenderer::new);

            MenuScreens.register(ExtraDelightContainers.SINK_MENU.get(), SinkCabinetScreen::new);
            MenuScreens.register(ExtraDelightContainers.COUNTER_CABINET_MENU.get(), CounterCabinetScreen::new);
            MenuScreens.register(ExtraDelightContainers.FOOD_DISPLAY_MENU.get(), FoodDisplayScreen::new);
            MenuScreens.register(ExtraDelightContainers.KNIFE_BLOCK_MENU.get(), KnifeBlockScreen::new);
            MenuScreens.register(ExtraDelightContainers.SPICE_RACK_MENU.get(), SpiceRackScreen::new);
            MenuScreens.register(ExtraDelightContainers.WREATH_MENU.get(), WreathScreen::new);

            BlockEntityRenderers.register(ExtraDelightBlockEntities.SINK_BLOCK.get(), SinkRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.COUNTER_CABINET_BLOCK.get(), CounterCabinetRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.FOOD_DISPLAY.get(), FoodDisplayRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.FRUIT_BOWL.get(), FruitBowlRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.KNIFE_BLOCK.get(), KnifeBlockRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.SPICE_RACK.get(), SpiceRackRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.WREATH.get(), WreathRenderer::new);

            BlockEntityRenderers.register(ExtraDelightBlockEntities.JAR.get(), JarRenderer::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.JAR_DISPLAY.get(), JarDisplayRenderer::new);

            MenuScreens.register(ExtraDelightContainers.PICNIC_BASKET_MENU.get(), PicnicBasketScreen::new);
            BlockEntityRenderers.register(ExtraDelightBlockEntities.PICNIC_BASKET.get(), PicnicBasketRenderer::new);

            BlockEntityRenderers.register(ExtraDelightBlockEntities.CORN_HUSK_DOLL.get(), CornHuskDollRenderer::new);

        });
    }

}
