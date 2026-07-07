package com.vomiter.extradelight.util;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

public class RenderUtil {
	public static int WHITE = 0xFFFFFFFF;

	public static Vector4f ZERO4 = new Vector4f(0, 0, 0, 0);

	public static Vector4f getUV(ResourceLocation rc) {
		TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(rc);
		return getUVFromSprite(sprite);
	}

	public static Vector4f getUVFromSprite(TextureAtlasSprite sprite) {
		return new Vector4f(sprite.getU0(), sprite.getU1(), sprite.getV0(), sprite.getV1());
	}

	public static Vector4f getUVFromSprite(TextureAtlasSprite sprite, float offsetX, float offsetY, float width,
			float height) {
		float uUnit = (sprite.getU1() - sprite.getU0()) / 16;
		float vUnit = (sprite.getV1() - sprite.getV0()) / 16;

		float start0 = sprite.getU0() + (uUnit * offsetX);
		float start1 = sprite.getV0() + (vUnit * offsetY);

		float end0 = ((uUnit * width)) + start0;
		float end1 = ((vUnit * height)) + start1;

		return new Vector4f(start0, end0, start1, end1);
	}

    public static void buildPlane(Vector3f pos1, Vector3f pos2, Vector3f pos3, Vector3f pos4,
                                  VertexConsumer vertexConsumer, Matrix4f mat, Matrix3f normal, int tint, Vector4f uv, Vec3i vec3i,
                                  int light, int packedOverlay, PoseStack poseStack) {

        vertex(vertexConsumer, mat, normal, pos1, tint, uv.x, uv.w, vec3i, light, packedOverlay);
        vertex(vertexConsumer, mat, normal, pos2, tint, uv.y, uv.w, vec3i, light, packedOverlay);
        vertex(vertexConsumer, mat, normal, pos3, tint, uv.y, uv.z, vec3i, light, packedOverlay);
        vertex(vertexConsumer, mat, normal, pos4, tint, uv.x, uv.z, vec3i, light, packedOverlay);
    }

    private static void vertex(VertexConsumer consumer, Matrix4f pose, Matrix3f normal,
                               Vector3f pos, int tint, float u, float v, Vec3i normalVec, int light, int overlay) {

        int a = tint >> 24 & 255;
        int r = tint >> 16 & 255;
        int g = tint >> 8 & 255;
        int b = tint & 255;

        consumer.vertex(pose, pos.x(), pos.y(), pos.z())
                .color(r, g, b, a)
                .uv(u, v)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normal, normalVec.getX(), normalVec.getY(), normalVec.getZ())
                .endVertex();
    }

	public static void buildCubeAll(Vector3f start, Vector3f size, VertexConsumer vertexConsumer, Matrix4f mat,
			Matrix3f matrix3f, int tint, Vector4f uv, int light, int overlay, PoseStack ps) {
		RenderUtil.buildPlane(new Vector3f(start.x, start.y + size.y, start.z),
				new Vector3f(start.x, start.y + size.y, start.z + size.z),
				new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z),
				new Vector3f(start.x + size.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint, uv,
				Direction.UP.getNormal(), light, overlay, ps);

		RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y, start.z + size.z),
				new Vector3f(start.x + size.x, start.y, start.z),
				new Vector3f(start.x + size.x, start.y + size.y, start.z),
				new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z), vertexConsumer, mat, matrix3f, tint,
				uv, Direction.EAST.getNormal(), light, overlay, ps);

		RenderUtil.buildPlane(new Vector3f(start.x, start.y, start.z), new Vector3f(start.x, start.y, start.z + size.z),
				new Vector3f(start.x, start.y + size.y, start.z + size.z),
				new Vector3f(start.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint, uv,
				Direction.WEST.getNormal(), light, overlay, ps);

		RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y, start.z), new Vector3f(start.x, start.y, start.z),
				new Vector3f(start.x, start.y + size.y, start.z),
				new Vector3f(start.x + size.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint, uv,
				Direction.NORTH.getNormal(), light, overlay, ps);

		RenderUtil.buildPlane(new Vector3f(start.x, start.y, start.z + size.z),
				new Vector3f(start.x + size.x, start.y, start.z + size.z),
				new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z),
				new Vector3f(start.x, start.y + size.y, start.z + size.z), vertexConsumer, mat, matrix3f, tint, uv,
				Direction.SOUTH.getNormal(), light, overlay, ps);

		RenderUtil.buildPlane(new Vector3f(start.x, start.y, start.z), new Vector3f(start.x + size.x, start.y, start.z),
				new Vector3f(start.x + size.x, start.y, start.z + size.z),
				new Vector3f(start.x, start.y, start.z + size.z), vertexConsumer, mat, matrix3f, tint, uv,
				Direction.DOWN.getNormal(), light, overlay, ps);
	}

	public static void buildCube(Vector3f start, Vector3f size, VertexConsumer vertexConsumer, Matrix4f mat,
			Matrix3f matrix3f, int tint, int light, int overlay, PoseStack ps, Vector4f uvUp, Vector4f uvDown,
			Vector4f uvNorth, Vector4f uvSouth, Vector4f uvEast, Vector4f uvWest) {
		if (uvDown != null)
			RenderUtil.buildPlane(new Vector3f(start.x, start.y + size.y, start.z),
					new Vector3f(start.x, start.y + size.y, start.z + size.z),
					new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z),
					new Vector3f(start.x + size.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint,
					uvDown, Direction.UP.getNormal(), light, overlay, ps);

		if (uvEast != null)
			RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y, start.z + size.z),
					new Vector3f(start.x + size.x, start.y, start.z),
					new Vector3f(start.x + size.x, start.y + size.y, start.z),
					new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z), vertexConsumer, mat, matrix3f,
					tint, uvEast, Direction.EAST.getNormal(), light, overlay, ps);

		if (uvWest != null)
			RenderUtil.buildPlane(new Vector3f(start.x, start.y, start.z),
					new Vector3f(start.x, start.y, start.z + size.z),
					new Vector3f(start.x, start.y + size.y, start.z + size.z),
					new Vector3f(start.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint, uvWest,
					Direction.WEST.getNormal(), light, overlay, ps);

		if (uvNorth != null)
			RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y, start.z),
					new Vector3f(start.x, start.y, start.z), new Vector3f(start.x, start.y + size.y, start.z),
					new Vector3f(start.x + size.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint,
					uvNorth, Direction.NORTH.getNormal(), light, overlay, ps);

		if (uvSouth != null)
			RenderUtil.buildPlane(new Vector3f(start.x, start.y, start.z + size.z),
					new Vector3f(start.x + size.x, start.y, start.z + size.z),
					new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z),
					new Vector3f(start.x, start.y + size.y, start.z + size.z), vertexConsumer, mat, matrix3f, tint,
					uvSouth, Direction.SOUTH.getNormal(), light, overlay, ps);

		if (uvUp != null)
			RenderUtil.buildPlane(new Vector3f(start.x, start.y, start.z),
					new Vector3f(start.x + size.x, start.y, start.z),
					new Vector3f(start.x + size.x, start.y, start.z + size.z),
					new Vector3f(start.x, start.y, start.z + size.z), vertexConsumer, mat, matrix3f, tint, uvUp,
					Direction.DOWN.getNormal(), light, overlay, ps);
	}

	public static void buildInvertedCube(Vector3f start, Vector3f size, VertexConsumer vertexConsumer, Matrix4f mat,
			Matrix3f matrix3f, int tint, int light, Vector4f uv, int overlay, PoseStack ps) {
		RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z),
				new Vector3f(start.x, start.y + size.y, start.z + size.z),
				new Vector3f(start.x, start.y + size.y, start.z),
				new Vector3f(start.x + size.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint, uv,
				Direction.DOWN.getNormal(), light, overlay, ps);

		RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y, start.z),
				new Vector3f(start.x + size.x, start.y, start.z + size.z),
				new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z),
				new Vector3f(start.x + size.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint, uv,
				Direction.EAST.getNormal(), light, overlay, ps);

		RenderUtil.buildPlane(new Vector3f(start.x, start.y, start.z + size.z), new Vector3f(start.x, start.y, start.z),
				new Vector3f(start.x, start.y + size.y, start.z),
				new Vector3f(start.x, start.y + size.y, start.z + size.z), vertexConsumer, mat, matrix3f, tint, uv,
				Direction.WEST.getNormal(), light, overlay, ps);

		RenderUtil.buildPlane(new Vector3f(start.x, start.y, start.z), new Vector3f(start.x + size.x, start.y, start.z),
				new Vector3f(start.x + size.x, start.y + size.y, start.z),
				new Vector3f(start.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint, uv,
				Direction.NORTH.getNormal(), light, overlay, ps);

		RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y, start.z + size.z),
				new Vector3f(start.x, start.y, start.z + size.z),
				new Vector3f(start.x, start.y + size.y, start.z + size.z),
				new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z),

				vertexConsumer, mat, matrix3f, tint, uv, Direction.SOUTH.getNormal(), light, overlay, ps);

		RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y, start.z + size.z),
				new Vector3f(start.x + size.x, start.y, start.z), new Vector3f(start.x, start.y, start.z),
				new Vector3f(start.x, start.y, start.z + size.z), vertexConsumer, mat, matrix3f, tint, uv,
				Direction.DOWN.getNormal(), light, overlay, ps);
	}

	public static void buildInvertedCubePillar(Vector3f start, Vector3f size, VertexConsumer vertexConsumer,
			Matrix4f mat, Matrix3f matrix3f, int tint, int light, Vector4f uvSides, Vector4f uvTop, Vector4f uvBottom,
			int overlay, PoseStack ps) {
		if (uvTop != null)
			RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z),
					new Vector3f(start.x, start.y + size.y, start.z + size.z),
					new Vector3f(start.x, start.y + size.y, start.z),
					new Vector3f(start.x + size.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint,
					uvTop, Direction.DOWN.getNormal(), light, overlay, ps);
		if (uvSides != null) {
			RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y, start.z),
					new Vector3f(start.x + size.x, start.y, start.z + size.z),
					new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z),
					new Vector3f(start.x + size.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint,
					uvSides, Direction.EAST.getNormal(), light, overlay, ps);

			RenderUtil.buildPlane(new Vector3f(start.x, start.y, start.z + size.z),
					new Vector3f(start.x, start.y, start.z), new Vector3f(start.x, start.y + size.y, start.z),
					new Vector3f(start.x, start.y + size.y, start.z + size.z), vertexConsumer, mat, matrix3f, tint,
					uvSides, Direction.WEST.getNormal(), light, overlay, ps);

			RenderUtil.buildPlane(new Vector3f(start.x, start.y, start.z),
					new Vector3f(start.x + size.x, start.y, start.z),
					new Vector3f(start.x + size.x, start.y + size.y, start.z),
					new Vector3f(start.x, start.y + size.y, start.z), vertexConsumer, mat, matrix3f, tint, uvSides,
					Direction.NORTH.getNormal(), light, overlay, ps);

			RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y, start.z + size.z),
					new Vector3f(start.x, start.y, start.z + size.z),
					new Vector3f(start.x, start.y + size.y, start.z + size.z),
					new Vector3f(start.x + size.x, start.y + size.y, start.z + size.z),

					vertexConsumer, mat, matrix3f, tint, uvSides, Direction.SOUTH.getNormal(), light, overlay, ps);
		}
		if (uvBottom != null)
			RenderUtil.buildPlane(new Vector3f(start.x + size.x, start.y, start.z + size.z),
					new Vector3f(start.x + size.x, start.y, start.z), new Vector3f(start.x, start.y, start.z),
					new Vector3f(start.x, start.y, start.z + size.z), vertexConsumer, mat, matrix3f, tint, uvBottom,
					Direction.UP.getNormal(), light, overlay, ps);
	}
}