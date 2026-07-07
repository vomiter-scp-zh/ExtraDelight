package com.vomiter.extradelight.worldgen.features.trees;

import java.util.Optional;

import com.vomiter.extradelight.ExtraDelight;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;

import javax.annotation.Nullable;


public class ExtraDelightTreeGrowers {
	public static final TreeGrower CINNAMON = new TreeGrower(ExtraDelight.MOD_ID + "cinnamon", 0.1F, Optional.empty(),
			Optional.empty(), Optional.of(ExtraDelightTreeFeatures.CINNAMON), Optional.empty(), Optional.empty(),
			Optional.empty());
	public static final TreeGrower HAZELNUT = new TreeGrower(ExtraDelight.MOD_ID + "hazelnut", 0.1F, Optional.empty(),
			Optional.empty(), Optional.of(ExtraDelightTreeFeatures.HAZELNUT), Optional.empty(), Optional.empty(),
			Optional.empty());
	public static final TreeGrower APPLE = new TreeGrower(ExtraDelight.MOD_ID + "apple", 0.1F, Optional.empty(),
			Optional.empty(), Optional.of(ExtraDelightTreeFeatures.APPLE), Optional.empty(), Optional.empty(),
			Optional.empty());
	public static final TreeGrower LEMON = new TreeGrower(ExtraDelight.MOD_ID + "lemon", 0.1F, Optional.empty(),
			Optional.empty(), Optional.of(ExtraDelightTreeFeatures.LEMON), Optional.empty(), Optional.empty(),
			Optional.empty());
	public static final TreeGrower LIME = new TreeGrower(ExtraDelight.MOD_ID + "lime", 0.1F, Optional.empty(),
			Optional.empty(), Optional.of(ExtraDelightTreeFeatures.LIME), Optional.empty(), Optional.empty(),
			Optional.empty());
	public static final TreeGrower ORANGE = new TreeGrower(ExtraDelight.MOD_ID + "orange", 0.1F, Optional.empty(),
			Optional.empty(), Optional.of(ExtraDelightTreeFeatures.ORANGE), Optional.empty(), Optional.empty(),
			Optional.empty());
	public static final TreeGrower GRAPEFRUIT = new TreeGrower(ExtraDelight.MOD_ID + "grapefruit", 0.1F, Optional.empty(),
			Optional.empty(), Optional.of(ExtraDelightTreeFeatures.GRAPEFRUIT), Optional.empty(), Optional.empty(),
			Optional.empty());

    public static class TreeGrower extends AbstractTreeGrower {
        private final String name;
        private final float secondaryChance;
        private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree;
        private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree;
        private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree;
        private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree;
        private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers;
        private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers;

        public TreeGrower(String name,
                          float secondaryChance,
                          Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree,
                          Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree,
                          Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
                          Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree,
                          Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers,
                          Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers
        ) {
            this.name = name;
            this.secondaryChance = secondaryChance;
            this.megaTree = megaTree;
            this.secondaryMegaTree = secondaryMegaTree;
            this.tree = tree;
            this.secondaryTree = secondaryTree;
            this.flowers = flowers;
            this.secondaryFlowers = secondaryFlowers;
        }

        @Nullable
        protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowersNearby) {
            float roll = random.nextFloat();

            ExtraDelight.LOGGER.info(
                    "[TreeGrower:{}] getConfiguredFeature. flowersNearby={}, secondaryChance={}, roll={}, tree={}, secondaryTree={}, flowers={}, secondaryFlowers={}",
                    this.name,
                    flowersNearby,
                    this.secondaryChance,
                    roll,
                    this.tree.map(ResourceKey::location).orElse(null),
                    this.secondaryTree.map(ResourceKey::location).orElse(null),
                    this.flowers.map(ResourceKey::location).orElse(null),
                    this.secondaryFlowers.map(ResourceKey::location).orElse(null)
            );

            if (roll < this.secondaryChance) {
                if (flowersNearby && this.secondaryFlowers.isPresent()) {
                    ResourceKey<ConfiguredFeature<?, ?>> selected = this.secondaryFlowers.get();
                    ExtraDelight.LOGGER.info("[TreeGrower:{}] selected secondaryFlowers feature: {}", this.name, selected.location());
                    return selected;
                }

                if (this.secondaryTree.isPresent()) {
                    ResourceKey<ConfiguredFeature<?, ?>> selected = this.secondaryTree.get();
                    ExtraDelight.LOGGER.info("[TreeGrower:{}] selected secondaryTree feature: {}", this.name, selected.location());
                    return selected;
                }
            }

            ResourceKey<ConfiguredFeature<?, ?>> selected =
                    flowersNearby && this.flowers.isPresent() ? this.flowers.get() : this.tree.orElse(null);

            ExtraDelight.LOGGER.info(
                    "[TreeGrower:{}] selected normal feature: {}",
                    this.name,
                    selected == null ? "null" : selected.location()
            );

            return selected;
        }

        @Nullable
        private ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
            float roll = random.nextFloat();

            ExtraDelight.LOGGER.info(
                    "[TreeGrower:{}] getConfiguredMegaFeature. secondaryChance={}, roll={}, megaTree={}, secondaryMegaTree={}",
                    this.name,
                    this.secondaryChance,
                    roll,
                    this.megaTree.map(ResourceKey::location).orElse(null),
                    this.secondaryMegaTree.map(ResourceKey::location).orElse(null)
            );

            ResourceKey<ConfiguredFeature<?, ?>> selected =
                    this.secondaryMegaTree.isPresent() && roll < this.secondaryChance
                            ? this.secondaryMegaTree.get()
                            : this.megaTree.orElse(null);

            ExtraDelight.LOGGER.info(
                    "[TreeGrower:{}] selected mega feature: {}",
                    this.name,
                    selected == null ? "null" : selected.location()
            );

            return selected;
        }

        @Override
        public boolean growTree(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random) {
            ExtraDelight.LOGGER.info(
                    "[TreeGrower:{}] growTree start. pos={}, state={}, dimension={}, blockAtPos={}, fluidAtPos={}",
                    this.name,
                    pos,
                    state,
                    level.dimension().location(),
                    level.getBlockState(pos),
                    level.getFluidState(pos)
            );

            ResourceKey<ConfiguredFeature<?, ?>> megaKey = this.getConfiguredMegaFeature(random);

            if (megaKey != null) {
                ExtraDelight.LOGGER.info("[TreeGrower:{}] trying mega tree feature: {}", this.name, megaKey.location());

                Optional<Holder.Reference<ConfiguredFeature<?, ?>>> megaHolderOpt =
                        level.registryAccess()
                                .registryOrThrow(Registries.CONFIGURED_FEATURE)
                                .getHolder(megaKey);

                ExtraDelight.LOGGER.info(
                        "[TreeGrower:{}] mega holder present: {}",
                        this.name,
                        megaHolderOpt.isPresent()
                );

                Holder<ConfiguredFeature<?, ?>> megaHolder = megaHolderOpt.orElse(null);

                SaplingGrowTreeEvent event = ForgeEventFactory.blockGrowFeature(level, random, pos, megaHolder);
                ExtraDelight.LOGGER.info(
                        "[TreeGrower:{}] mega SaplingGrowTreeEvent. canceled={}, originalHolderPresent={}, eventHolderPresent={}",
                        this.name,
                        event.isCanceled(),
                        megaHolder != null,
                        event.getFeature() != null
                );

                megaHolder = event.getFeature();

                if (event.isCanceled()) {
                    ExtraDelight.LOGGER.warn("[TreeGrower:{}] mega grow canceled by SaplingGrowTreeEvent. pos={}", this.name, pos);
                    return false;
                }

                if (megaHolder != null) {
                    for (int i = 0; i >= -1; --i) {
                        for (int j = 0; j >= -1; --j) {
                            boolean twoByTwo = isTwoByTwoSapling(state, level, pos, i, j);

                            ExtraDelight.LOGGER.info(
                                    "[TreeGrower:{}] checking 2x2 sapling. basePos={}, xOffset={}, zOffset={}, result={}",
                                    this.name,
                                    pos,
                                    i,
                                    j,
                                    twoByTwo
                            );

                            if (twoByTwo) {
                                ConfiguredFeature<?, ?> configuredFeature = megaHolder.value();
                                BlockState air = Blocks.AIR.defaultBlockState();
                                BlockPos placePos = pos.offset(i, 0, j);

                                ExtraDelight.LOGGER.info(
                                        "[TreeGrower:{}] placing mega tree. placePos={}, featureHolder={}",
                                        this.name,
                                        placePos,
                                        megaHolder.unwrapKey().map(ResourceKey::location).orElse(null)
                                );

                                level.setBlock(pos.offset(i, 0, j), air, 4);
                                level.setBlock(pos.offset(i + 1, 0, j), air, 4);
                                level.setBlock(pos.offset(i, 0, j + 1), air, 4);
                                level.setBlock(pos.offset(i + 1, 0, j + 1), air, 4);

                                boolean placed = configuredFeature.place(level, chunkGenerator, random, placePos);

                                ExtraDelight.LOGGER.info(
                                        "[TreeGrower:{}] mega configuredFeature.place result: {}. placePos={}",
                                        this.name,
                                        placed,
                                        placePos
                                );

                                if (placed) {
                                    ExtraDelight.LOGGER.info("[TreeGrower:{}] mega tree grow success. pos={}", this.name, pos);
                                    return true;
                                }

                                ExtraDelight.LOGGER.warn(
                                        "[TreeGrower:{}] mega tree place failed. restoring saplings. pos={}, placePos={}",
                                        this.name,
                                        pos,
                                        placePos
                                );

                                level.setBlock(pos.offset(i, 0, j), state, 4);
                                level.setBlock(pos.offset(i + 1, 0, j), state, 4);
                                level.setBlock(pos.offset(i, 0, j + 1), state, 4);
                                level.setBlock(pos.offset(i + 1, 0, j + 1), state, 4);
                                return false;
                            }
                        }
                    }

                    ExtraDelight.LOGGER.info(
                            "[TreeGrower:{}] mega feature exists but no 2x2 sapling pattern found. fallback to normal tree. pos={}",
                            this.name,
                            pos
                    );
                } else {
                    ExtraDelight.LOGGER.warn(
                            "[TreeGrower:{}] mega feature holder is null after event. key={}, pos={}",
                            this.name,
                            megaKey.location(),
                            pos
                    );
                }
            }

            boolean flowersNearby = this.hasFlowers(level, pos);
            ResourceKey<ConfiguredFeature<?, ?>> normalKey = this.getConfiguredFeature(random, flowersNearby);

            ExtraDelight.LOGGER.info(
                    "[TreeGrower:{}] trying normal tree. pos={}, flowersNearby={}, selectedKey={}",
                    this.name,
                    pos,
                    flowersNearby,
                    normalKey == null ? "null" : normalKey.location()
            );

            if (normalKey == null) {
                ExtraDelight.LOGGER.warn("[TreeGrower:{}] normal tree key is null. pos={}", this.name, pos);
                return false;
            }

            Optional<Holder.Reference<ConfiguredFeature<?, ?>>> holderOpt =
                    level.registryAccess()
                            .registryOrThrow(Registries.CONFIGURED_FEATURE)
                            .getHolder(normalKey);

            ExtraDelight.LOGGER.info(
                    "[TreeGrower:{}] normal holder present: {}. key={}",
                    this.name,
                    holderOpt.isPresent(),
                    normalKey.location()
            );

            Holder<ConfiguredFeature<?, ?>> holder = holderOpt.orElse(null);

            SaplingGrowTreeEvent event = ForgeEventFactory.blockGrowFeature(level, random, pos, holder);

            ExtraDelight.LOGGER.info(
                    "[TreeGrower:{}] normal SaplingGrowTreeEvent. canceled={}, originalHolderPresent={}, eventHolderPresent={}",
                    this.name,
                    event.isCanceled(),
                    holder != null,
                    event.getFeature() != null
            );

            holder = event.getFeature();

            if (event.isCanceled()) {
                ExtraDelight.LOGGER.warn("[TreeGrower:{}] normal grow canceled by SaplingGrowTreeEvent. pos={}", this.name, pos);
                return false;
            }

            if (holder == null) {
                ExtraDelight.LOGGER.warn(
                        "[TreeGrower:{}] normal feature holder is null after event. key={}, pos={}",
                        this.name,
                        normalKey.location(),
                        pos
                );
                return false;
            }

            ConfiguredFeature<?, ?> configuredFeature = holder.value();
            BlockState fluidBlockState = level.getFluidState(pos).createLegacyBlock();

            ExtraDelight.LOGGER.info(
                    "[TreeGrower:{}] placing normal tree. pos={}, key={}, holderKey={}, replacingSaplingWith={}",
                    this.name,
                    pos,
                    normalKey.location(),
                    holder.unwrapKey().map(ResourceKey::location).orElse(null),
                    fluidBlockState
            );

            level.setBlock(pos, fluidBlockState, 4);

            boolean placed = configuredFeature.place(level, chunkGenerator, random, pos);

            ExtraDelight.LOGGER.info(
                    "[TreeGrower:{}] normal configuredFeature.place result: {}. pos={}, stateAfterPlace={}",
                    this.name,
                    placed,
                    pos,
                    level.getBlockState(pos)
            );

            if (placed) {
                if (level.getBlockState(pos) == fluidBlockState) {
                    ExtraDelight.LOGGER.info(
                            "[TreeGrower:{}] place returned true but block at pos still equals fluidBlockState. sending block update. pos={}",
                            this.name,
                            pos
                    );
                    level.sendBlockUpdated(pos, state, fluidBlockState, 2);
                }

                ExtraDelight.LOGGER.info("[TreeGrower:{}] normal tree grow success. pos={}", this.name, pos);
                return true;
            }

            ExtraDelight.LOGGER.warn(
                    "[TreeGrower:{}] normal tree place failed. restoring sapling. pos={}, key={}, stateBeforeRestore={}, stateAfterFailedPlace={}",
                    this.name,
                    pos,
                    normalKey.location(),
                    state,
                    level.getBlockState(pos)
            );

            level.setBlock(pos, state, 4);
            return false;
        }

        private static boolean isTwoByTwoSapling(BlockState state, BlockGetter level, BlockPos pos, int xOffset, int zOffset) {
            Block block = state.getBlock();

            BlockPos p1 = pos.offset(xOffset, 0, zOffset);
            BlockPos p2 = pos.offset(xOffset + 1, 0, zOffset);
            BlockPos p3 = pos.offset(xOffset, 0, zOffset + 1);
            BlockPos p4 = pos.offset(xOffset + 1, 0, zOffset + 1);

            boolean result =
                    level.getBlockState(p1).is(block)
                            && level.getBlockState(p2).is(block)
                            && level.getBlockState(p3).is(block)
                            && level.getBlockState(p4).is(block);

            if (result) {
                ExtraDelight.LOGGER.info(
                        "[TreeGrower] found 2x2 sapling. block={}, p1={}, p2={}, p3={}, p4={}",
                        block,
                        p1,
                        p2,
                        p3,
                        p4
                );
            }

            return result;
        }

        private boolean hasFlowers(LevelAccessor level, BlockPos pos) {
            int flowerCount = 0;

            for (BlockPos blockPos : BlockPos.MutableBlockPos.betweenClosed(
                    pos.below().north(2).west(2),
                    pos.above().south(2).east(2)
            )) {
                if (level.getBlockState(blockPos).is(BlockTags.FLOWERS)) {
                    flowerCount++;

                    ExtraDelight.LOGGER.info(
                            "[TreeGrower:{}] flower nearby. flowerPos={}, state={}",
                            this.name,
                            blockPos.immutable(),
                            level.getBlockState(blockPos)
                    );
                }
            }

            ExtraDelight.LOGGER.info(
                    "[TreeGrower:{}] hasFlowers result. pos={}, flowerCount={}, result={}",
                    this.name,
                    pos,
                    flowerCount,
                    flowerCount > 0
            );

            return flowerCount > 0;
        }
    }
}