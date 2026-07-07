package com.vomiter.extradelight.worldgen.placers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.vomiter.extradelight.ExtraDelight;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.material.Fluids;

public class FruitLeafPlacer extends FancyFoliagePlacer {
    public static final MapCodec<FruitLeafPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> blobParts(instance).apply(instance, FruitLeafPlacer::new)
    );

    public FruitLeafPlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset, height);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return FoliagePlacerRegistry.FRUIT_LEAF.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level,
                                 FoliageSetter foliageSetter,
                                 RandomSource random,
                                 TreeConfiguration config,
                                 int maxFreeTreeHeight,
                                 FoliageAttachment attachment,
                                 int foliageHeight,
                                 int foliageRadius,
                                 int offset) {
        ExtraDelight.LOGGER.info(
                "[FruitLeafPlacer] createFoliage start. attachmentPos={}, maxFreeTreeHeight={}, foliageHeight={}, foliageRadius={}, offset={}, doubleTrunk={}",
                attachment.pos(),
                maxFreeTreeHeight,
                foliageHeight,
                foliageRadius,
                offset,
                attachment.doubleTrunk()
        );

        int placedCount = 0;

        for (int localY = offset; localY >= offset - foliageHeight; localY--) {
            int range = foliageRadius + (localY != offset && localY != offset - foliageHeight ? 1 : 0);

            ExtraDelight.LOGGER.info(
                    "[FruitLeafPlacer] placing row. attachmentPos={}, localY={}, range={}",
                    attachment.pos(),
                    localY,
                    range
            );

            placedCount += this.placeLeavesRowFruitDebug(
                    level,
                    foliageSetter,
                    random,
                    config,
                    attachment.pos(),
                    range,
                    localY,
                    attachment.doubleTrunk()
            );
        }

        ExtraDelight.LOGGER.info(
                "[FruitLeafPlacer] createFoliage end. attachmentPos={}, placedCount={}",
                attachment.pos(),
                placedCount
        );
    }

    private int placeLeavesRowFruitDebug(LevelSimulatedReader level,
                                         FoliageSetter foliageSetter,
                                         RandomSource random,
                                         TreeConfiguration config,
                                         BlockPos pos,
                                         int range,
                                         int localY,
                                         boolean large) {
        int largeOffset = large ? 1 : 0;
        int placed = 0;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int dx = -range; dx <= range + largeOffset; dx++) {
            for (int dz = -range; dz <= range + largeOffset; dz++) {
                if (!this.shouldSkipLocationSigned(random, dx, localY, dz, range, large)) {
                    mutablePos.setWithOffset(pos, dx, localY, dz);

                    boolean result = tryPlaceFruitLeaf(
                            level,
                            foliageSetter,
                            random,
                            config,
                            mutablePos.immutable()
                    );

                    if (result) {
                        placed++;
                    }
                }
            }
        }

        ExtraDelight.LOGGER.info(
                "[FruitLeafPlacer] row result. basePos={}, localY={}, range={}, placed={}",
                pos,
                localY,
                range,
                placed
        );

        return placed;
    }

    protected static boolean tryPlaceFruitLeaf(LevelSimulatedReader level,
                                               FoliageSetter foliageSetter,
                                               RandomSource random,
                                               TreeConfiguration config,
                                               BlockPos pos) {
        boolean valid = TreeFeature.validTreePos(level, pos);

        if (!valid) {
            ExtraDelight.LOGGER.info(
                    "[FruitLeafPlacer] skip leaf. pos={}, reason=invalidTreePos",
                    pos
            );
            return false;
        }

        BlockState blockState = config.foliageProvider.getState(random, pos);

        if (blockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
            blockState = blockState.setValue(
                    BlockStateProperties.WATERLOGGED,
                    level.isFluidAtPosition(pos, fluidState -> fluidState.isSourceOfType(Fluids.WATER))
            );
        }

        ExtraDelight.LOGGER.info(
                "[FruitLeafPlacer] set leaf. pos={}, state={}",
                pos,
                blockState
        );

        foliageSetter.set(pos, blockState);
        return true;
    }
}