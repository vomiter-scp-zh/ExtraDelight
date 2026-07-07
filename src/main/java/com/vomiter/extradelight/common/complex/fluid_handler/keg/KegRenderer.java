package com.vomiter.extradelight.common.complex.fluid_handler.keg;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.util.RenderUtil;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class KegRenderer implements BlockEntityRenderer<KegBlockEntity> {
    private static final boolean DEBUG_RENDER = !FMLEnvironment.production && false;
    private static int debugTicks = 0;

    final ResourceLocation kegTop = ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID, "block/keg_top");
    final ResourceLocation kegBottom = ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID, "block/keg_bottom");
    final ResourceLocation kegSide = ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID, "block/keg_side");

    public KegRenderer(BlockEntityRendererProvider.Context context) {
        debug("KegRenderer constructed. context={}", context);
    }

    @Override
    public void render(KegBlockEntity tank, float tickDelta, PoseStack ps, MultiBufferSource mbs, int light,
                       int overlay) {
        if (tank == null) {
            debug("render skipped: tank is null");
            return;
        }

        BlockPos pos = tank.getBlockPos();
        BlockState state = tank.getBlockState();

        debugThrottled("render enter. pos={}, state={}, tickDelta={}, light={}, overlay={}, removed={}, levelNull={}",
                pos, state, tickDelta, light, overlay, tank.isRemoved(), tank.getLevel() == null);

        VertexConsumer vertexConsumer = mbs.getBuffer(Sheets.translucentCullBlockSheet());
        debugThrottled("vertex consumer acquired. pos={}, consumerClass={}",
                pos, vertexConsumer.getClass().getName());

        ps.pushPose();

        try {
            Matrix4f mat = ps.last().pose();
            Matrix3f matrix3f = ps.last().normal();

            debugThrottled("render shell begin. pos={}, sideUV={}, topUV={}, bottomUV={}",
                    pos, RenderUtil.getUV(kegSide), RenderUtil.getUV(kegTop), RenderUtil.getUV(kegBottom));

            RenderUtil.buildInvertedCubePillar(
                    new Vector3f(0, 0, 0),
                    new Vector3f(1, 1, 1),
                    vertexConsumer,
                    mat,
                    matrix3f,
                    0xffffffff,
                    LightTexture.FULL_SKY,
                    RenderUtil.getUV(kegSide),
                    RenderUtil.getUV(kegTop),
                    RenderUtil.getUV(kegBottom),
                    overlay,
                    ps
            );

            debugThrottled("render shell end. pos={}", pos);
        } catch (Throwable t) {
            debugError("render shell failed. pos=" + pos, t);
        } finally {
            ps.popPose();
        }

        FluidStack fluidStack = tank.getTank().getFluid();
        int amount = fluidStack.getAmount();
        int capacity = tank.getTank().getCapacity();
        float fullness = tank.getFullness();

        debugThrottled("tank fluid state. pos={}, empty={}, fluid={}, amount={}, capacity={}, fullness={}",
                pos, fluidStack.isEmpty(), fluidStack.getFluid(), amount, capacity, fullness);

        if (fluidStack.isEmpty()) {
            debugThrottled("render fluid skipped: empty fluid. pos={}", pos);
            return;
        }

        ps.pushPose();

        try {
            Matrix4f mat = ps.last().pose();
            Matrix3f matrix3f = ps.last().normal();

            Fluid fluid = fluidStack.getFluid();
            IClientFluidTypeExtensions fluidTypeExtensions = IClientFluidTypeExtensions.of(fluid);

            ResourceLocation stillTexture = fluidTypeExtensions.getStillTexture();
            int tintColor = fluidTypeExtensions.getTintColor(fluidStack);

            Vector3f from = new Vector3f(1f / 16f, 1f / 16f, 1f / 16f);
            Vector3f to = new Vector3f(
                    14f / 16f,
                    fullness * (14f / 16f),
                    14f / 16f
            );

            debugThrottled("render fluid begin. pos={}, fluid={}, stillTexture={}, tintColor=0x{}, from={}, to={}",
                    pos, fluid, stillTexture, Integer.toHexString(tintColor), from, to);

            RenderUtil.buildCubeAll(
                    from,
                    to,
                    vertexConsumer,
                    mat,
                    matrix3f,
                    tintColor,
                    RenderUtil.getUV(stillTexture),
                    LightTexture.FULL_SKY,
                    overlay,
                    ps
            );

            debugThrottled("render fluid end. pos={}", pos);
        } catch (Throwable t) {
            debugError("render fluid failed. pos=" + pos + ", fluid=" + fluidStack, t);
        } finally {
            ps.popPose();
        }
    }

    private static void debug(String message, Object... args) {
        if (DEBUG_RENDER) {
            ExtraDelight.LOGGER.info("[KegRenderer] " + message, args);
        }
    }

    private static void debugThrottled(String message, Object... args) {
        if (!DEBUG_RENDER) {
            return;
        }

        debugTicks++;

        if (debugTicks % 60 == 0) {
            ExtraDelight.LOGGER.info("[KegRenderer] " + message, args);
        }
    }

    private static void debugError(String message, Throwable throwable) {
        if (DEBUG_RENDER) {
            ExtraDelight.LOGGER.error("[KegRenderer] " + message, throwable);
        }
    }
}