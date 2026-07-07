package com.vomiter.extradelight.common.complex.jar;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.registry.ExtraDelightBlocks;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.util.RenderUtil;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.renderable.BakedModelRenderable;
import net.minecraftforge.client.model.renderable.IRenderable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

public class JarItemModel extends BlockEntityWithoutLevelRenderer {
	private static JarItemModel instance;
	public JarItemModel() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	public static JarItemModel getInstance() {
		if (instance == null) {
			instance = new JarItemModel(
            );
		}
		return instance;
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack ps, MultiBufferSource mbs,
			int packedLight, int overlay) {
		if (stack.is(ExtraDelightItems.JAR.get())) {
			ps.pushPose();

			if (displayContext == ItemDisplayContext.GUI) {

				ps.mulPose(new Quaternionf().rotateXYZ((float) Math.toRadians(30), (float) Math.toRadians(225), 0));
				ps.translate(-0.05f, 1.0f, -1.9f);
				ps.scale(1.25f, 1.25f, 1.25f);
			} else if (displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND
					|| displayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND) {
				ps.translate(0, 0.35, -0.17f);
			} else {
				ps.translate(0, 0.25f, 0f);

			}
            var state = ExtraDelightBlocks.JAR.get().defaultBlockState();
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, ps, mbs, packedLight, overlay);

            VertexConsumer vertexConsumer = mbs.getBuffer(Sheets.translucentCullBlockSheet());
			Matrix4f mat = ps.last().pose();
			Matrix3f matrix3f = ps.last().normal();
            stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(f -> {
                if (f != null && !f.getFluidInTank(0).isEmpty()) {

                    FluidStack fluidStack = f.getFluidInTank(0).copy();
                    Fluid fluid = fluidStack.getFluid();
                    IClientFluidTypeExtensions fluidTypeExtensions = IClientFluidTypeExtensions.of(fluid);

//
                    RenderUtil.buildCubeAll(new Vector3f(5.75f / 16f, 0.5f / 16f, 5.75f / 16f),
                            new Vector3f(5f / 16f, ((float) f.getFluidInTank(0).getAmount() / 1000f) * (6f / 16f), 5f / 16f), vertexConsumer,
                            mat, matrix3f, fluidTypeExtensions.getTintColor(fluidStack),
                            RenderUtil.getUV(fluidTypeExtensions.getStillTexture()), packedLight, overlay, ps);
                }
            });
            ps.popPose();
        }
	}
}
