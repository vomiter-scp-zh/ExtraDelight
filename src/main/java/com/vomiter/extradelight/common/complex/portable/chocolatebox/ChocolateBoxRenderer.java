package com.vomiter.extradelight.common.complex.portable.chocolatebox;

import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ChocolateBoxRenderer implements BlockEntityRenderer<ChocolateBoxBlockEntity> {

	public ChocolateBoxRenderer(BlockEntityRendererProvider.Context cxt) {

	}

	@Override
	public void render(ChocolateBoxBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       @NotNull MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
		if (!pBlockEntity.hasLevel()) {
			return;
		}

		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

		IItemHandler itemInteractionHandler = pBlockEntity.getItems();

		Direction dir = pBlockEntity.getBlockState().getValue(ChocolateBoxBlock.FACING);

		pPoseStack.pushPose();
		// pPoseStack.mulPose(new Quaternion(0, -dir.toYRot(), 0, true));

		float xoff = 0;
		float yoff = 0;
		float zoff = 0;

		for (int i = 0; i < ChocolateBoxBlockEntity.NUM_SLOTS; i++) {
			xoff = (i % 4) * 0.15f;
			if (i % 4 == 0) {
				zoff += 0.15f;
//				yoff -= 0.1f;
			}

			ItemStack item = itemInteractionHandler.getStackInSlot(i);

			if (!item.isEmpty()) {
				BakedModel bakedmodel = itemRenderer.getModel(item, pBlockEntity.getLevel(), null, 0);
				pPoseStack.pushPose();
				pPoseStack.translate(0.5f, 0, 0.5f);
				pPoseStack.mulPose(new Quaternionf().rotateXYZ(0, (float) Math.toRadians(-dir.toYRot()), 0));
				pPoseStack.translate(xoff - 0.225f, yoff + 0.1, zoff - 0.2);

				if (i % 2 == 0)
					pPoseStack.translate(0, 0.0, 0.05);

				pPoseStack.mulPose(new Quaternionf().rotateXYZ((float) Math.toRadians(-80), 0, 0));
				float uniscale = 0.5f;
				pPoseStack.scale(uniscale, uniscale, uniscale);
				itemRenderer.render(item, ItemDisplayContext.GROUND, false, pPoseStack, pBufferSource, pPackedLight,
						pPackedOverlay, bakedmodel);
				pPoseStack.popPose();
			}
		}
		pPoseStack.popPose();
	}

}
