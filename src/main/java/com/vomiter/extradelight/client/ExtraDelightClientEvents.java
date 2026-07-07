package com.vomiter.extradelight.client;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.registry.ExtraDelightBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(
        modid = ExtraDelight.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public final class ExtraDelightClientEvents {
    private ExtraDelightClientEvents() {
    }

    @SubscribeEvent
    public static void registerGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register("blockstateitem", new BlockStateItemModel.Loader());
    }

    @SubscribeEvent
    public static void RegisterExtraModels(ModelEvent.RegisterAdditional event) {
        Map<ResourceLocation, Resource> rrs = Minecraft.getInstance().getResourceManager().listResources("models/extra",
                (p_215600_) -> {
                    return p_215600_.getPath().endsWith(".json");
                });

        rrs.forEach((rl, r) -> {
            String s = rl.toString();

            s = s.substring(s.indexOf('/') + 1, s.indexOf('.'));

            ExtraDelight.LOGGER.debug(s);

            ModelResourceLocation rl2 = new ModelResourceLocation(
                    ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), s),
                    ""
            );

            event.register(rl2);
        });
    }

    @SubscribeEvent
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

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(
                (stack, tintIndex) -> FoliageColor.getDefaultColor(),
                ExtraDelightBlocks.APPLE_LEAVES.get(),
                ExtraDelightBlocks.HAZELNUT_LEAVES.get()
        );
    }
}
