package com.vomiter.extradelight;

import com.mojang.logging.LogUtils;
import com.vomiter.extradelight.client.ExtraDelightClientEvents;
import com.vomiter.extradelight.common.ExtraCompostable;
import com.vomiter.extradelight.common.ExtraDelightEvents;
import com.vomiter.extradelight.common.complex.cap.ExtraDelightCapabilities;
import com.vomiter.extradelight.common.recipes.IngredientRegistry;
import com.vomiter.extradelight.data.DataGen;
import com.vomiter.extradelight.data.loot.ExtraDelightLootModifiers;
import com.vomiter.extradelight.network.NetworkHandler;
import com.vomiter.extradelight.registry.*;
import com.vomiter.extradelight.worldgen.ExtraDelightFeatures;
import com.vomiter.extradelight.worldgen.ExtraDelightWorldGen;
import com.vomiter.extradelight.worldgen.placers.FoliagePlacerRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
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
        ExtraDelightBanners.BANNER_PATTERNS.register(modBus);
        ExtraDelightPaintings.PAINTING_VARIANTS.register(modBus);
        IngredientRegistry.register();

        MinecraftForge.EVENT_BUS.addGenericListener(ItemStack.class, ExtraDelightCapabilities::attachItemCapabilities);
        ExtraDelightEvents.init();

        if(FMLEnvironment.dist.isClient()){
            ExtraDelightClientEvents.init(modBus);
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
            ExtraCompostable.setup();
        });
    }
}
