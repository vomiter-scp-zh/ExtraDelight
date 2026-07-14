package com.vomiter.extradelight.client.dynamic_food;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;

public class DynamicFoodChildBakedGeometry implements BakedModel {
	private final List<BakedModel> childrenModels;
    private static final float LAYER_OFFSET = 0.0005F;


    public DynamicFoodChildBakedGeometry(List<BakedModel> childrenModels) {
		this.childrenModels = childrenModels;
	}

    private static BakedQuad offsetQuad(BakedQuad quad, float offset) {
        if (offset == 0.0F) {
            return quad;
        }

        int[] vertices = quad.getVertices().clone();
        int vertexStride = vertices.length / 4;

        for (int vertex = 0; vertex < 4; vertex++) {
            int zIndex = vertex * vertexStride + 2;

            float z = Float.intBitsToFloat(vertices[zIndex]);
            vertices[zIndex] = Float.floatToRawIntBits(z + offset);
        }

        return new BakedQuad(
                vertices,
                quad.getTintIndex(),
                quad.getDirection(),
                quad.getSprite(),
                quad.isShade()
        );
    }


    @Override
	@NotNull
	@SuppressWarnings("deprecation")
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand) {
		List<BakedQuad> bakedQuads = new ArrayList<>();
        for (int layer = 0; layer < childrenModels.size(); layer++) {
            BakedModel bakedModel = childrenModels.get(layer);
            float offset = layer * LAYER_OFFSET;

            for (BakedQuad quad : bakedModel.getQuads(state, side, rand)) {
                bakedQuads.add(offsetQuad(quad, offset));
            }
        }
		return bakedQuads;
	}

	@Override
	@NotNull
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand,
                                    @NotNull ModelData data, @Nullable RenderType renderType) {
		List<BakedQuad> bakedQuads = new ArrayList<>();
		for (BakedModel bakedModel : childrenModels) {
			bakedQuads.addAll(bakedModel.getQuads(state, side, rand, data, renderType));
		}
		return bakedQuads;
	}

	@Override
	@NotNull
	public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand,
			@NotNull ModelData data) {
		Set<ChunkRenderTypeSet> chunkRenderTypeSets = new HashSet<>();
		for (BakedModel bakedModel : childrenModels) {
			chunkRenderTypeSets.add(bakedModel.getRenderTypes(state, rand, data));
		}
		return ChunkRenderTypeSet.union(chunkRenderTypeSets);
	}

	@Override
	public boolean useAmbientOcclusion() {
		return this.childrenModels.stream().anyMatch(BakedModel::useAmbientOcclusion);
	}

	@Override
	public boolean isGui3d() {
		return this.childrenModels.stream().anyMatch(BakedModel::isGui3d);
	}

	@Override
	public boolean usesBlockLight() {
		return this.childrenModels.stream().anyMatch(BakedModel::usesBlockLight);
	}

	@Override
	public boolean isCustomRenderer() {
		return this.childrenModels.stream().anyMatch(BakedModel::isCustomRenderer);
	}

	@Override
	@NotNull
	@SuppressWarnings("deprecation")
	public TextureAtlasSprite getParticleIcon() {
		if (this.childrenModels.isEmpty()) {
			return ModelBakery.FIRE_0.sprite();
		} else {
			return this.childrenModels.get(0).getParticleIcon();
		}
	}

	@Override
	@NotNull
	public TextureAtlasSprite getParticleIcon(@NotNull ModelData modelData) {
		if (this.childrenModels.isEmpty()) {
			return ModelBakery.FIRE_0.sprite();
		} else {
			return this.childrenModels.get(0).getParticleIcon(modelData);
		}
	}

	@Override
	@NotNull
	public ItemOverrides getOverrides() {
		return new ItemOverrides() {
		};
	}

	@Override
	public BakedModel applyTransform(ItemDisplayContext transformType, PoseStack poseStack,
			boolean applyLeftHandTransform) {
		childrenModels.get(0).getTransforms().getTransform(transformType).apply(applyLeftHandTransform, poseStack);
//		this.getTransforms().getTransform(transformType).apply(applyLeftHandTransform, poseStack);
		return this;
	}
}