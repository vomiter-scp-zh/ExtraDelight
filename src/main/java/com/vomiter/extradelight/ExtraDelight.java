package com.vomiter.extradelight;

import com.mojang.logging.LogUtils;
import com.vomiter.extradelight.common.complex.jar.JarRenderer;
import com.vomiter.extradelight.common.complex.portable.picnicbasket.PicnicBasketRenderer;
import com.vomiter.extradelight.common.complex.portable.picnicbasket.PicnicBasketScreen;
import com.vomiter.extradelight.common.complex.jardisplay.JarDisplayRenderer;
import com.vomiter.extradelight.common.recipes.IngredientRegistry;
import com.vomiter.extradelight.registry.*;
import com.vomiter.extradelight.common.complex.cabinet.countercabinet.CounterCabinetRenderer;
import com.vomiter.extradelight.common.complex.cabinet.countercabinet.CounterCabinetScreen;
import com.vomiter.extradelight.common.complex.displays.food.FoodDisplayRenderer;
import com.vomiter.extradelight.common.complex.displays.food.FoodDisplayScreen;
import com.vomiter.extradelight.common.complex.displays.fruitbowl.FruitBowlRenderer;
import com.vomiter.extradelight.common.complex.displays.knife.KnifeBlockRenderer;
import com.vomiter.extradelight.common.complex.displays.knife.KnifeBlockScreen;
import com.vomiter.extradelight.common.complex.displays.spice.SpiceRackRenderer;
import com.vomiter.extradelight.common.complex.displays.spice.SpiceRackScreen;
import com.vomiter.extradelight.common.complex.displays.wreath.WreathRenderer;
import com.vomiter.extradelight.common.complex.displays.wreath.WreathScreen;
import com.vomiter.extradelight.common.complex.cabinet.sink.SinkCabinetScreen;
import com.vomiter.extradelight.common.complex.cabinet.sink.SinkRenderer;
import com.vomiter.extradelight.common.complex.cap.ExtraDelightCapabilities;
import com.vomiter.extradelight.common.complex.portable.chocolatebox.ChocolateBoxRenderer;
import com.vomiter.extradelight.common.complex.fluid_handler.funnel.FunnelRenderer;
import com.vomiter.extradelight.common.complex.fluid_handler.keg.KegRenderer;
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
import com.vomiter.extradelight.data.DataGen;
import com.vomiter.extradelight.data.loot.ExtraDelightLootModifiers;
import com.vomiter.extradelight.gui.StyleableScreen;
import com.vomiter.extradelight.network.NetworkHandler;
import com.vomiter.extradelight.worldgen.ExtraDelightFeatures;
import com.vomiter.extradelight.worldgen.ExtraDelightWorldGen;
import com.vomiter.extradelight.worldgen.placers.FoliagePlacerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@Mod(ExtraDelight.MOD_ID)
public class ExtraDelight
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "extradelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation modLoc(String s) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, s);
    }

    public ExtraDelight(FMLJavaModLoadingContext context) {
        ForgeMod.enableMilkFluid();

        IEventBus modBus = context.getModEventBus();
        modBus.addListener(DataGen::gatherData);
        modBus.addListener(this::commonSetup);
        context.registerConfig(ModConfig.Type.COMMON, ExtraDelightConfig.spec);

        AestheticBlocks.setup();
        AestheticBlocks.BLOCKS.register(modBus);
        AestheticBlocks.ITEMS.register(modBus);

        ExtraDelightBlocks.register(modBus);
        ExtraDelightBlockEntities.TILES.register(modBus);
        ExtraDelightContainers.MENU_TYPES.register(modBus);
        ExtraDelightItems.ITEMS.register(modBus);
        ExtraDelightFluids.register(modBus);
        ExtraDelightRecipes.RECIPE_TYPES.register(modBus);
        ExtraDelightRecipes.RECIPE_SERIALIZERS.register(modBus);
        ExtraDelightTabs.TABS.register(modBus);
        ExtraDelightMobEffects.register(modBus);
        ExtraDelightWorldGen.FEATURES.register(modBus);
        ExtraDelightFeatures.FEATURES.register(modBus);
        FoliagePlacerRegistry.PLACER.register(modBus);
        ExtraDelightParticles.PARTICLE_TYPES.register(modBus);
        ExtraDelightLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modBus);
        ExtraDelightLootFunctions.LOOT_FUNCTION_TYPES.register(modBus);
        IngredientRegistry.register();

        MinecraftForge.EVENT_BUS.addGenericListener(ItemStack.class, ExtraDelightCapabilities::attachItemCapabilities);

        if(FMLEnvironment.dist.isClient()){
            modBus.addListener(this::clientSetup);
        }

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DataComponents.setUpBuiltInChill();
            NetworkHandler.setupPackets();
            ExtraDelightCapabilities.registerCapabilities();
            ExtraDelightCapabilities.setupSlotCountMap();
            ExtraDelightItems.setup();
            ExtraDelightBlocks.setup();

        });
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
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

        });
    }

}
