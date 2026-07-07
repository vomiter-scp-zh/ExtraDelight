package com.vomiter.extradelight.common.complex.cabinet.sink;

import java.util.ArrayList;
import java.util.List;

import com.vomiter.extradelight.common.complex.cabinet.countercabinet.CounterCabinetBlock;
import com.vomiter.extradelight.util.RenderUtil;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.data.ModelData;

public class SinkRenderer implements BlockEntityRenderer<SinkCabinetBlockEntity> {

    public SinkRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SinkCabinetBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (!blockEntity.hasLevel()) {
            return;
        }

        ItemStack item = blockEntity.getItemHandler().getStackInSlot(18);
        if (item.isEmpty() || !(item.getItem() instanceof BlockItem blockItem)) {
            return;
        }

        List<BakedQuad> copiedQuads = new ArrayList<>();

        var state = blockItem.getBlock().defaultBlockState();
        var model = Minecraft.getInstance().getBlockRenderer().getBlockModel(state);

        for (Direction direction : Direction.values()) {
            List<BakedQuad> quads = model.getQuads(
                    state,
                    direction,
                    blockEntity.getLevel().random,
                    ModelData.EMPTY,
                    RenderType.cutout()
            );

            if (!quads.isEmpty()) {
                copiedQuads.add(quads.get(0));
            }
        }

        if (copiedQuads.isEmpty()) {
            return;
        }

        poseStack.pushPose();

        float facingRot = blockEntity.getBlockState()
                .getValue(CounterCabinetBlock.FACING)
                .getClockWise()
                .toYRot();

        poseStack.translate(0.5F, 0.0F, 0.5F);
        poseStack.mulPose(new Quaternionf().fromAxisAngleDeg(0.0F, 1.0F, 0.0F, -facingRot - 90.0F));
        poseStack.translate(-0.5F, 0.0F, -0.5F);

        renderSinkGeometry(poseStack, bufferSource, packedLight, copiedQuads);

        poseStack.popPose();
    }

    private void renderSinkGeometry(PoseStack poseStack, MultiBufferSource bufferSource,
                                    int lightmap, List<BakedQuad> copiedQuads) {
        poseStack.pushPose();

        poseStack.translate(-0.001F, 0.001F, -0.001F);
        float scale = 1.002F;
        poseStack.scale(scale, scale, scale);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(
                RenderType.entityTranslucent(InventoryMenu.BLOCK_ATLAS)
        );

        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();

        Vector4f setUVTop = RenderUtil.ZERO4;
        Vector4f setUVBottom = RenderUtil.ZERO4;
        Vector4f setUVNorth = RenderUtil.ZERO4;
        Vector4f setUVSouth = RenderUtil.ZERO4;
        Vector4f setUVEast = RenderUtil.ZERO4;
        Vector4f setUVWest = RenderUtil.ZERO4;

        // Basin
        if (copiedQuads.size() > 1 && copiedQuads.get(1) != null) {
            setUVBottom = RenderUtil.getUVFromSprite(copiedQuads.get(1).getSprite(), 1, 1, 15, 15);
        }
        if (copiedQuads.size() > 2 && copiedQuads.get(2) != null) {
            setUVEast = RenderUtil.getUVFromSprite(copiedQuads.get(2).getSprite(), 2, 2, 12, 5);
        }

        RenderUtil.buildInvertedCubePillar(
                new Vector3f(1.015F / 16F, 12F / 16F, 1.015F / 16F),
                new Vector3f(13.97F / 16F, 4F / 16F, 13F / 16F),
                vertexConsumer,
                matrix4f,
                matrix3f,
                RenderUtil.WHITE,
                lightmap,
                setUVEast,
                null,
                setUVBottom,
                OverlayTexture.NO_OVERLAY,
                poseStack
        );

        // Back
        if (copiedQuads.size() > 1 && copiedQuads.get(1) != null) {
            setUVBottom = RenderUtil.getUVFromSprite(copiedQuads.get(1).getSprite(), 0, 0, 2, 16);
        }
        if (copiedQuads.size() > 2 && copiedQuads.get(2) != null) {
            setUVNorth = RenderUtil.getUVFromSprite(copiedQuads.get(2).getSprite(), 0, 0, 16, 4);
        }
        if (copiedQuads.size() > 3 && copiedQuads.get(3) != null) {
            setUVSouth = RenderUtil.getUVFromSprite(copiedQuads.get(3).getSprite(), 0, 0, 16, 4);
        }
        if (copiedQuads.size() > 4 && copiedQuads.get(4) != null) {
            setUVEast = RenderUtil.getUVFromSprite(copiedQuads.get(4).getSprite(), 0, 0, 2, 4);
        }
        if (copiedQuads.size() > 5 && copiedQuads.get(5) != null) {
            setUVWest = RenderUtil.getUVFromSprite(copiedQuads.get(5).getSprite(), 0, 0, 2, 4);
        }

        RenderUtil.buildCube(
                new Vector3f(0, 14F / 16F, 14F / 16F),
                new Vector3f(1, 4F / 16F, 2F / 16F),
                vertexConsumer,
                matrix4f,
                matrix3f,
                RenderUtil.WHITE,
                lightmap,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                null,
                setUVBottom,
                setUVNorth,
                setUVSouth,
                setUVEast,
                setUVWest
        );

        if (copiedQuads.size() > 1 && copiedQuads.get(1) != null) {
            setUVBottom = RenderUtil.getUVFromSprite(copiedQuads.get(1).getSprite(), 0, 4, 2, 8);
        }
        if (copiedQuads.size() > 2 && copiedQuads.get(2) != null) {
            setUVNorth = RenderUtil.getUVFromSprite(copiedQuads.get(2).getSprite(), 4, 0, 8, 2);
        }
        if (copiedQuads.size() > 3 && copiedQuads.get(3) != null) {
            setUVSouth = RenderUtil.getUVFromSprite(copiedQuads.get(3).getSprite(), 4, 0, 8, 2);
        }
        if (copiedQuads.size() > 4 && copiedQuads.get(4) != null) {
            setUVEast = RenderUtil.getUVFromSprite(copiedQuads.get(4).getSprite(), 4, 0, 2, 2);
        }
        if (copiedQuads.size() > 5 && copiedQuads.get(5) != null) {
            setUVWest = RenderUtil.getUVFromSprite(copiedQuads.get(5).getSprite(), 4, 0, 2, 2);
        }

        RenderUtil.buildCube(
                new Vector3f(4F / 16F, 18F / 16F, 14F / 16F),
                new Vector3f(8F / 16F, 2F / 16F, 2F / 16F),
                vertexConsumer,
                matrix4f,
                matrix3f,
                RenderUtil.WHITE,
                lightmap,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                null,
                setUVBottom,
                setUVNorth,
                setUVSouth,
                setUVEast,
                setUVWest
        );

        if (copiedQuads.size() > 1 && copiedQuads.get(1) != null) {
            setUVBottom = RenderUtil.getUVFromSprite(copiedQuads.get(1).getSprite(), 0, 15, 15, 1);
        }
        if (copiedQuads.size() > 2 && copiedQuads.get(2) != null) {
            setUVNorth = RenderUtil.getUVFromSprite(copiedQuads.get(2).getSprite(), 4, 0, 8, 2);
        }
        if (copiedQuads.size() > 5 && copiedQuads.get(5) != null) {
            setUVWest = RenderUtil.getUVFromSprite(copiedQuads.get(5).getSprite(), 1, 0, 15, 2);
        }

        RenderUtil.buildCube(
                new Vector3f(0, 14F / 16F, 0),
                new Vector3f(1.02F / 16F, 2F / 16F, 14F / 16F),
                vertexConsumer,
                matrix4f,
                matrix3f,
                RenderUtil.WHITE,
                lightmap,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                null,
                setUVBottom,
                setUVNorth,
                null,
                null,
                setUVWest
        );

        if (copiedQuads.size() > 1 && copiedQuads.get(1) != null) {
            setUVBottom = RenderUtil.getUVFromSprite(copiedQuads.get(1).getSprite(), 0, 0, 15, 1);
        }
        if (copiedQuads.size() > 2 && copiedQuads.get(2) != null) {
            setUVNorth = RenderUtil.getUVFromSprite(copiedQuads.get(2).getSprite(), 4, 0, 1, 2);
        }
        if (copiedQuads.size() > 4 && copiedQuads.get(4) != null) {
            setUVEast = RenderUtil.getUVFromSprite(copiedQuads.get(4).getSprite(), 1, 0, 15, 2);
        }

        RenderUtil.buildCube(
                new Vector3f(14.98F / 16F, 14F / 16F, 0),
                new Vector3f(1.01F / 16F, 2F / 16F, 14F / 16F),
                vertexConsumer,
                matrix4f,
                matrix3f,
                RenderUtil.WHITE,
                lightmap,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                null,
                setUVBottom,
                setUVNorth,
                null,
                setUVEast,
                null
        );

        if (copiedQuads.size() > 1 && copiedQuads.get(1) != null) {
            setUVBottom = RenderUtil.getUVFromSprite(copiedQuads.get(1).getSprite(), 15, 0, 1, 14);
        }
        if (copiedQuads.size() > 2 && copiedQuads.get(2) != null) {
            setUVNorth = RenderUtil.getUVFromSprite(copiedQuads.get(2).getSprite(), 1, 0, 14, 2);
        }

        RenderUtil.buildCube(
                new Vector3f(1F / 16F, 14F / 16F, 0),
                new Vector3f(14F / 16F, 2F / 16F, 1.042F / 16F),
                vertexConsumer,
                matrix4f,
                matrix3f,
                RenderUtil.WHITE,
                lightmap,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                null,
                setUVBottom,
                setUVNorth,
                null,
                null,
                null
        );

        poseStack.popPose();
    }
}