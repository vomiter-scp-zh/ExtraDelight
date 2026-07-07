package com.vomiter.extradelight.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.ParametersAreNonnullByDefault;


import com.vomiter.extradelight.data.advancement.EDAdvancementGenerator;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class Advancements extends ForgeAdvancementProvider {
	public Advancements(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
			ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, existingFileHelper, List.of(new EDAdvancementGenerator()));
	}
}