package com.vomiter.extradelight.data.tags;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.registry.AestheticBlocks;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.Fermentation;
import com.vomiter.extradelight.registry.SummerCitrus;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings({"unused", "deprecation", "SpellCheckingInspection"})
public class EDItemTagsDifficult extends ItemTagsProvider {

	public EDItemTagsDifficult(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
                               CompletableFuture<TagLookup<Block>> blockTagProvider,
                               @Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, blockTagProvider, ExtraDelight.MOD_ID + "d", existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(ExtraDelightTags.JAM_GLOW_BERRY);//.add(ExtraDelightItems.GLOW_BERRY_JAM.get());
		tag(ExtraDelightTags.JAM_FRUIT);//.add(ExtraDelightItems.JAM.get());
		tag(ExtraDelightTags.JAM_GOLDEN_APPLE);//.add(ExtraDelightItems.GOLDEN_APPLE_JAM.get());
		tag(ExtraDelightTags.JAM_MINT).add(ExtraDelightItems.MINT_JELLY.get());

		tag(ExtraDelightTags.JAM_MUNDANE).addTag(ExtraDelightTags.JAM_FRUIT).addTag(ExtraDelightTags.JAM_MINT);
		/*
        tag(ExtraDelightTags.JAM).add(ExtraDelightItems.DYNAMIC_JAM.get()).addTag(ExtraDelightTags.JAM_MUNDANE)
				.addTag(ExtraDelightTags.JAM_GOLDEN_APPLE).addTag(ExtraDelightTags.JAM_GLOW_BERRY)
				.addOptionalTag(CompatTags.JELLIES);
		tag(CompatTags.JELLIES).add(ExtraDelightItems.DYNAMIC_JAM.get());
		
		 */
		tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "food/cooked_eggs")))
                .add(ExtraDelightItems.BOILED_EGG.get(),
				ExtraDelightItems.SCRAMBLED_EGGS.get());
        tag(ExtraDelightTags.TOAST_TOPPING).add(ExtraDelightItems.BUTTER.get(),
                ExtraDelightItems.PEANUT_BUTTER_BOTTLE.get(), ExtraDelightItems.HAZELNUT_SPREAD_BOTTLE.get(),
                ExtraDelightItems.MARSHMALLOW_FLUFF_BOTTLE.get()
                /*
                ExtraDelightItems.JAM.get(),
                ExtraDelightItems.GLOW_BERRY_JAM.get(),
				ExtraDelightItems.GOLDEN_APPLE_JAM.get(),
                Fermentation.YEAST_SPREAD.get(),
                ExtraDelightItems.DYNAMIC_JAM.get()

                 */
        );
        //tag(CommonTags.Items.FOODS_MILK).add(Fermentation.SOY_MILK.get());
        tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "foods/vegetables")))
                .add(ExtraDelightItems.CORN_SEEDS.get(), ExtraDelightItems.CHILI.get(), Fermentation.CUCUMBER.get());
        tag(ExtraDelightTags.MISO_SOUP_INGREDIENTS).addTag(ExtraDelightTags.PROCESSED_CABBAGE)
                .addTag(Tags.Items.MUSHROOMS).addTag(ExtraDelightTags.PROCESSED_CARROT)
                .addTag(ExtraDelightTags.PROCESSED_POTATO).addTag(ExtraDelightTags.PROCESSED_ONION)
        //.addOptionalTag(CommonTags.FOODS_SAFE_RAW_FISH.location())
        ;
        /* TODO: CHECK HOW FOOD WORKS
		tag(ItemTags.CAMEL_FOOD).add(ExtraDelightItems.CACTUS.get());
		tag(ItemTags.CHICKEN_FOOD).add(ExtraDelightItems.CORN_SEEDS.get(), ExtraDelightItems.CHILI_SEEDS.get(),
				ExtraDelightItems.HAZELNUTS.get(), ExtraDelightItems.PEANUTS.get(),
				ExtraDelightItems.SUNFLOWER_SEEDS.get(), Fermentation.CUCUMBER_SEED.get(), Fermentation.SOYBEANS.get());
		tag(ItemTags.COW_FOOD).add(ExtraDelightItems.CORN_HUSK.get(), ExtraDelightItems.DRIED_CORN_HUSK.get(),
				Fermentation.SOYBEAN_POD.get(), Fermentation.SOYBEANS.get());
		tag(ItemTags.FROG_FOOD).add(ExtraDelightItems.AGAR_AGAR.get());
		tag(ItemTags.GOAT_FOOD).add(Fermentation.SOYBEAN_POD.get(), Fermentation.SOYBEANS.get());
		tag(ItemTags.LLAMA_FOOD).add(ExtraDelightItems.CORN_HUSK_BUNDLE.get(),
				ExtraDelightItems.DRIED_CORN_HUSK_BUNDLE.get());
		tag(ItemTags.PARROT_FOOD).add(ExtraDelightItems.CORN_SEEDS.get(), ExtraDelightItems.CHILI_SEEDS.get(),
				ExtraDelightItems.HAZELNUTS.get(), ExtraDelightItems.PEANUTS.get(),
				ExtraDelightItems.SUNFLOWER_SEEDS.get(), Fermentation.CUCUMBER_SEED.get(), Fermentation.SOYBEANS.get());
		tag(ItemTags.PIG_FOOD).add(ExtraDelightItems.CORN_COB.get(), ExtraDelightItems.GINGER.get(),
				ExtraDelightItems.MALLOW_ROOT.get(), Fermentation.CUCUMBER.get());
		tag(ItemTags.SHEEP_FOOD).add(ExtraDelightItems.CORN_HUSK.get(), ExtraDelightItems.DRIED_CORN_HUSK.get(),
				Fermentation.SOYBEAN_POD.get(), Fermentation.SOYBEANS.get());

         */
        tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "foods/fruits")));
        tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "foods/berries")));

        tag(ExtraDelightTags.JAMMABLE)
                .addTag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "foods/fruits")))
                .addTag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "foods/berries")))
                .addTag(Tags.Items.CROPS_CARROT);
        tag(ExtraDelightTags.MILK_CHOCOLATE_BAR).add(ExtraDelightItems.MILK_CHOCOLATE_BAR.get());
        //.addOptional(AllItems.BAR_OF_CHOCOLATE.getId()

        // Dyed Tags
        for (DyeColor dyeColor : DyeColor.values()) {
            tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "dyed/" + dyeColor.getName().toLowerCase(Locale.ROOT))))
                    .add(ExtraDelightItems.BLACK_CHOCOLATE_BOX.get(),
                            AestheticBlocks.GINGHAM_CARPET_ITEMS.get(dyeColor.getId()).get(),
                            AestheticBlocks.GINGHAM_ITEMS.get(dyeColor.getId()).get(),
                            AestheticBlocks.MOLDED_WALLPAPER_ITEMS.get(dyeColor.getId()).get(),
                            AestheticBlocks.WALLPAPER_ITEMS.get(dyeColor.getId()).get(),
                            AestheticBlocks.BOW_ITEMS.get(dyeColor.getId()).get());
            tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "dye")))
                    .addTag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "dyed/" + dyeColor.getName().toLowerCase(Locale.ROOT))));
        }
        tag(CompatTags.UPRIGHT_ON_BELT)
                //.add(ExtraDelightItems.JAM.get(),
                        //ExtraDelightItems.GLOW_BERRY_JAM.get(), ExtraDelightItems.GOLDEN_APPLE_JAM.get(),);
        ;

    }
}
