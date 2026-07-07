package com.vomiter.extradelight.util.debug;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

import java.util.ArrayList;
import java.util.List;

public final class DropCapture {
    private static final ThreadLocal<CaptureContext> ACTIVE_CAPTURE = new ThreadLocal<>();

    private DropCapture() {
    }

    public static void begin(ServerLevel level) {
        ACTIVE_CAPTURE.set(new CaptureContext(level));
    }

    public static List<ItemStack> getCapturedDrops() {
        CaptureContext context = ACTIVE_CAPTURE.get();
        if (context == null) {
            return List.of();
        }

        return List.copyOf(context.drops);
    }

    public static void end() {
        ACTIVE_CAPTURE.remove();
    }

    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        CaptureContext context = ACTIVE_CAPTURE.get();
        if (context == null) {
            return;
        }

        if (event.getLevel() != context.level) {
            return;
        }

        if (!(event.getEntity() instanceof ItemEntity itemEntity)) {
            return;
        }

        ItemStack stack = itemEntity.getItem();
        if (!stack.isEmpty()) {
            context.drops.add(stack.copy());
        }

        event.setCanceled(true);
    }

    private static final class CaptureContext {
        private final ServerLevel level;
        private final List<ItemStack> drops = new ArrayList<>();

        private CaptureContext(ServerLevel level) {
            this.level = level;
        }
    }
}