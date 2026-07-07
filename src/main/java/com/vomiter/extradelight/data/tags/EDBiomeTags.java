package com.vomiter.extradelight.data.tags;

import com.vomiter.extradelight.ExtraDelight;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EDBiomeTags extends BiomeTagsProvider {
    public EDBiomeTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, ExtraDelight.MOD_ID, existingFileHelper);
    }
    private static TagKey<Biome> create(String ns, String path){
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(ns, path));
    }


    protected void addTags(HolderLookup.@NotNull Provider p_256485_) {
        tag(create(ExtraDelight.MOD_ID, "is_mountain_slope"))
                .add(Biomes.SNOWY_SLOPES)
                .add(Biomes.MEADOW)
                .add(Biomes.GROVE)
                .add(Biomes.CHERRY_GROVE);
        tag(create(ExtraDelight.MOD_ID, "is_temperate"))
                .add(Biomes.PLAINS)
                .add(Biomes.SUNFLOWER_PLAINS)
                .add(Biomes.FOREST)
                .add(Biomes.FLOWER_FOREST)
                .add(Biomes.BIRCH_FOREST)
                .add(Biomes.DARK_FOREST)
                .add(Biomes.OLD_GROWTH_BIRCH_FOREST)
                .add(Biomes.MEADOW)
                .add(Biomes.SWAMP)
                .add(Biomes.MANGROVE_SWAMP)
                .add(Biomes.BEACH);
    }
}