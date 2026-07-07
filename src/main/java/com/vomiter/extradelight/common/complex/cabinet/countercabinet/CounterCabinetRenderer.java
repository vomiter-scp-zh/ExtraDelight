package com.vomiter.extradelight.common.complex.cabinet.countercabinet;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.vomiter.extradelight.util.RenderUtil;

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

public class CounterCabinetRenderer implements BlockEntityRenderer<CounterCabinetBlockEntity> {

    public CounterCabinetRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(CounterCabinetBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (!blockEntity.hasLevel()) {
            return;
        }

        ItemStack item = blockEntity.getItemHandler().getStackInSlot(27);
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
        poseStack.mulPose(Axis.YP.rotationDegrees(-facingRot - 90.0F));
        poseStack.translate(-0.5F, 0.0F, -0.5F);

        renderCounterGeometry(poseStack, bufferSource, packedLight, copiedQuads);

        poseStack.popPose();
    }

    private void renderCounterGeometry(PoseStack poseStack, MultiBufferSource bufferSource,
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

        Vector4f uvBottom = RenderUtil.ZERO4;
        Vector4f uvNorth = RenderUtil.ZERO4;
        Vector4f uvSouth = RenderUtil.ZERO4;
        Vector4f uvEast = RenderUtil.ZERO4;
        Vector4f uvWest = RenderUtil.ZERO4;

        if (copiedQuads.size() > 1 && copiedQuads.get(1) != null) {
            uvBottom = RenderUtil.getUVFromSprite(copiedQuads.get(1).getSprite(), 0, 0, 16, 16);
        }
        if (copiedQuads.size() > 2 && copiedQuads.get(2) != null) {
            uvNorth = RenderUtil.getUVFromSprite(copiedQuads.get(2).getSprite(), 0, 0, 16, 2);
        }
        if (copiedQuads.size() > 3 && copiedQuads.get(3) != null) {
            uvSouth = RenderUtil.getUVFromSprite(copiedQuads.get(3).getSprite(), 0, 0, 16, 2);
        }
        if (copiedQuads.size() > 4 && copiedQuads.get(4) != null) {
            uvEast = RenderUtil.getUVFromSprite(copiedQuads.get(4).getSprite(), 0, 0, 16, 2);
        }
        if (copiedQuads.size() > 5 && copiedQuads.get(5) != null) {
            uvWest = RenderUtil.getUVFromSprite(copiedQuads.get(5).getSprite(), 0, 0, 16, 2);
        }

        RenderUtil.buildCube(
                new Vector3f(0, 14F / 16F, 0),
                new Vector3f(1, 2F / 16F, 1),
                vertexConsumer,
                matrix4f,
                matrix3f,
                RenderUtil.WHITE,
                lightmap,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                null,
                uvBottom,
                uvNorth,
                uvSouth,
                uvEast,
                uvWest
        );

        if (copiedQuads.size() > 1 && copiedQuads.get(1) != null) {
            uvBottom = RenderUtil.getUVFromSprite(copiedQuads.get(1).getSprite(), 0, 0, 2, 16);
        }
        if (copiedQuads.size() > 2 && copiedQuads.get(2) != null) {
            uvNorth = RenderUtil.getUVFromSprite(copiedQuads.get(2).getSprite(), 0, 0, 16, 2);
        }
        if (copiedQuads.size() > 3 && copiedQuads.get(3) != null) {
            uvSouth = RenderUtil.getUVFromSprite(copiedQuads.get(3).getSprite(), 0, 0, 16, 2);
        }
        if (copiedQuads.size() > 4 && copiedQuads.get(4) != null) {
            uvEast = RenderUtil.getUVFromSprite(copiedQuads.get(4).getSprite(), 0, 0, 2, 2);
        }
        if (copiedQuads.size() > 5 && copiedQuads.get(5) != null) {
            uvWest = RenderUtil.getUVFromSprite(copiedQuads.get(5).getSprite(), 0, 0, 2, 2);
        }

        RenderUtil.buildCube(
                new Vector3f(0, 1, 14F / 16F),
                new Vector3f(1, 2F / 16F, 2F / 16F),
                vertexConsumer,
                matrix4f,
                matrix3f,
                RenderUtil.WHITE,
                lightmap,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                null,
                uvBottom,
                uvNorth,
                uvSouth,
                uvEast,
                uvWest
        );

        poseStack.popPose();
    }
}