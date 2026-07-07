package com.vomiter.extradelight.data;

import com.vomiter.extradelight.data.loot.BlockLootTables;
import com.vomiter.extradelight.data.loot.MiscLootTables;
import com.vomiter.extradelight.data.loot.StructureLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class AllLootTables extends LootTableProvider {

    public AllLootTables(PackOutput output) {
        super(output, Set.of(),
                List.of(
                        new SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
                        new SubProviderEntry(MiscLootTables::new, LootContextParamSets.ARCHAEOLOGY),
                        new SubProviderEntry(() -> new StructureLootTables(), LootContextParamSets.EMPTY)
                ));
    }
}