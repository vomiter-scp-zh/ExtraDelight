package com.vomiter.extradelight.util.debug;

import com.vomiter.extradelight.ExtraDelight;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExtraDelight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ExtraDelightDebugEvents {
    private ExtraDelightDebugEvents() {
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ExtraDelightDropTestCommand.register(event.getDispatcher());
        ExtraDelightItemCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        DropCapture.onEntityJoinLevel(event);
    }
}