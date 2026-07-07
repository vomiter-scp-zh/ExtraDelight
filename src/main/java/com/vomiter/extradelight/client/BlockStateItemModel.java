package com.vomiter.extradelight.client;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.blaze3d.vertex.PoseStack;
import com.vomiter.extradelight.DataComponents;
import com.vomiter.extradelight.ExtraDelight;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public final class BlockStateItemModel {
    public static final ResourceLocation ID = ExtraDelight.modLoc("blockstateitem");
    private BlockStateItemModel() {
    }

    public static final class Loader implements IGeometryLoader<UnbakedGeometry> {
        @Override
        public UnbakedGeometry read(JsonObject jsonObject, JsonDeserializationContext context)
                throws JsonParseException {
            return new UnbakedGeometry();
        }
    }

    public static final class UnbakedGeometry implements IUnbakedGeometry<UnbakedGeometry> {
        @Override
        public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation) {
            return new BakedGeometry();
        }
    }

    public static final class BakedGeometry implements BakedModel {
        private final ItemTransforms transforms = new ItemTransforms(
                new ItemTransform(new Vector3f(75, 45, 0), new Vector3f(0, 2.5f, 0), new Vector3f(0.375f, 0.375f, 0.375f)),
                new ItemTransform(new Vector3f(75, 45, 0), new Vector3f(0, 2.5f, 0), new Vector3f(0.375f, 0.375f, 0.375f)),
                new ItemTransform(new Vector3f(0, 225, 0), new Vector3f(0, 0, 0), new Vector3f(0.40f, 0.40f, 0.40f)),
                new ItemTransform(new Vector3f(0, 45, 0), new Vector3f(0, 0, 0), new Vector3f(0.40f, 0.40f, 0.40f)),
                new ItemTransform(new Vector3f(30, 225, 0), new Vector3f(0, 0, 0), new Vector3f(0.625f, 0.625f, 0.625f)),
                new ItemTransform(new Vector3f(30, 225, 0), new Vector3f(0, 0, 0), new Vector3f(0.625f, 0.625f, 0.625f)),
                new ItemTransform(new Vector3f(0, 0, 0), new Vector3f(0, 3, 0), new Vector3f(0.25f, 0.25f, 0.25f)),
                new ItemTransform(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(0.5f, 0.5f, 0.5f)),
                ImmutableMap.of()
        );

        private final ItemOverrides overrides = new Overrides();

        @Override
        @NotNull
        public List<BakedQuad> getQuads(
                @Nullable BlockState state,
                @Nullable Direction side,
                @NotNull RandomSource random
        ) {
            return Collections.emptyList();
        }

        @Override
        public boolean useAmbientOcclusion() {
            return true;
        }

        @Override
        public boolean isGui3d() {
            return true;
        }

        @Override
        public boolean usesBlockLight() {
            return true;
        }

        @Override
        public boolean isCustomRenderer() {
            return false;
        }

        @Override
        @NotNull
        public TextureAtlasSprite getParticleIcon() {
            return ModelBakery.FIRE_0.sprite();
        }

        @Override
        @NotNull
        public TextureAtlasSprite getParticleIcon(@NotNull ModelData modelData) {
            return ModelBakery.FIRE_0.sprite();
        }

        @Override
        @NotNull
        public ItemOverrides getOverrides() {
            return overrides;
        }

        @Override
        public @NotNull BakedModel applyTransform(
                @NotNull ItemDisplayContext transformType,
                @NotNull PoseStack poseStack,
                boolean leftHand
        ) {
            transforms.getTransform(transformType).apply(leftHand, poseStack);
            return this;
        }
    }

    public static final class Overrides extends ItemOverrides {
        @Override
        @ParametersAreNonnullByDefault
        public @NotNull BakedModel resolve(
                BakedModel model,
                ItemStack stack,
                @Nullable ClientLevel level,
                @Nullable LivingEntity entity,
                int seed
        ) {
            if (stack.getItem() instanceof BlockItem blockItem) {
                BlockState state = DataComponents.applyBlockStateTag(stack, blockItem.getBlock().defaultBlockState());
                return Minecraft.getInstance().getBlockRenderer().getBlockModel(state);
            }

            return model;
        }
    }
}