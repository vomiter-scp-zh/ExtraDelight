package com.vomiter.extradelight.data;

import java.util.concurrent.CompletableFuture;

import com.vomiter.extradelight.data.loot.LootModifiers;
import com.vomiter.extradelight.data.tags.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

public class DataGen {
//	private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.PLACED_FEATURE,
//			ExtraDelightTreePlacement::bootstrap);

	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		//generator.addProvider(event.includeClient(), new ItemModels(output, helper));
		//generator.addProvider(true, new BlockModels(output, helper));

		generator.addProvider(true, new AllLootTables(output));

		EDBlockTags blockTags = new EDBlockTags(output, lookupProvider, event.getExistingFileHelper());
		generator.addProvider(true, blockTags);
        generator.addProvider(true, new EDBiomeTags(output, lookupProvider, helper));
        generator.addProvider(event.includeServer(),
				new EDItemTags(output, lookupProvider, blockTags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(),
                new EDItemTags2(output, lookupProvider, blockTags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(),
                new EDItemTags3(output, lookupProvider, blockTags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(),
                new EDItemTagsDifficult(output, lookupProvider, blockTags.contentsGetter(), helper));

		generator.addProvider(event.includeServer(), new EDFluidTags(output, lookupProvider, helper));

		//generator.addProvider(event.includeServer(), new Recipes(output, lookupProvider));
		generator.addProvider(event.includeServer(), new LootModifiers(lookupProvider, output));
		generator.addProvider(event.includeServer(), new Advancements(output, lookupProvider, helper));
		//generator.addProvider(event.includeClient(), new EnglishLoc(output));

		generator.addProvider(event.includeServer(), new EDBiomeModifiers(output, lookupProvider));
		generator.addProvider(event.includeServer(), EDRegistries.provider(output, lookupProvider));

		//generator.addProvider(event.includeServer(), new DataMapGen(output, lookupProvider));

        /*
		generator.addProvider(event.includeClient(),
				new PatchouliGen(output, ExtraDelight.MOD_ID, "en_us", lookupProvider));

         */

        /*
		generator.addProvider(event.includeClient(), new CreateMixingRecipes(output, lookupProvider, "create"));

         */

	}
}