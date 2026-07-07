package com.vomiter.extradelight.data.tags;

import java.util.concurrent.CompletableFuture;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.registry.ExtraDelightFluids;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;

public class EDFluidTags extends FluidTagsProvider {

	public EDFluidTags(PackOutput output, CompletableFuture<Provider> provider,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, ExtraDelight.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {
		tag(ExtraDelightTags.CHOCOLATE_FLUID).add(ExtraDelightFluids.BLOOD_CHOCOLATE_SYRUP.FLUID.get(),
				ExtraDelightFluids.WHITE_CHOCOLATE_SYRUP.FLUID.get(),
				ExtraDelightFluids.MILK_CHOCOLATE_SYRUP.FLUID.get(),
				ExtraDelightFluids.DARK_CHOCOLATE_SYRUP.FLUID.get());


		tag(ExtraDelightTags.LEMON_LIME).add(ExtraDelightFluids.LEMON_JUICE.FLUID.get(),
				ExtraDelightFluids.LIME_JUICE.FLUID.get());


	}
}
