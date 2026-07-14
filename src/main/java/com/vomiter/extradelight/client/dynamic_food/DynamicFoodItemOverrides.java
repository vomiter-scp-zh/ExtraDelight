package com.vomiter.extradelight.client.dynamic_food;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.items.dynamic.DynamicJam;
import com.vomiter.extradelight.common.items.dynamic.api.IDynamic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class DynamicFoodItemOverrides extends ItemOverrides {
	private final Cache<Integer, DynamicFoodChildBakedGeometry> cache;

	public DynamicFoodItemOverrides() {
		this.cache
                = CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.of(5, ChronoUnit.MINUTES)).build();
	}

	@Override
	@Nullable
	@ParametersAreNonnullByDefault
	public BakedModel resolve(BakedModel pModel, ItemStack pStack, @Nullable ClientLevel pLevel,
			@Nullable LivingEntity pEntity, int pSeed) {
		if (pStack.getItem() instanceof IDynamic customizable) {
			Collection<ResourceLocation> resources = customizable.getPieces(pStack);
			List<BakedModel> pieces = new ArrayList<BakedModel>();

			for (ResourceLocation rc : new HashSet<>(resources)) {
				BakedModel bm = Minecraft.getInstance().getModelManager()
						.getModel(rc);
				if (bm != Minecraft.getInstance().getModelManager().getMissingModel()){
                    pieces.add(bm);
                }
			}
            if (pieces.isEmpty()){
                pieces.add(
                        Minecraft.getInstance().getModelManager()
                                .getModel(customizable.getMissingModel())
                );
            }
            var finalPieces = new ArrayList<BakedModel>();

            finalPieces.add(
                    Minecraft.getInstance().getModelManager().getModel(customizable.getBaseModel()
                    )
            );



            finalPieces.addAll(pieces);

			try {
				return cache.get(finalPieces.size(), () -> {
					List<BakedModel> pieceBakedModels = new ArrayList<>(finalPieces.size());
					for (BakedModel iPiece : finalPieces) {
						pieceBakedModels.add(iPiece);
					}
					return new DynamicFoodChildBakedGeometry(pieceBakedModels);
				});
			} catch (ExecutionException e) {
				ExtraDelight.LOGGER.error("FAILED TO CREATE GEOMETRY FOR MODEL", e);
			}
		}
		return pModel;
	}
}