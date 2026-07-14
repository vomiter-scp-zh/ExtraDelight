package com.vomiter.extradelight.data.tags;

import java.util.concurrent.CompletableFuture;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.Fermentation;
import com.vomiter.extradelight.registry.SummerCitrus;
import com.vomiter.extradelight.util.EDItemGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;

public class EDItemTags2 extends ItemTagsProvider {

	public EDItemTags2(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
			CompletableFuture<TagLookup<Block>> blockTagProvider,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, blockTagProvider, ExtraDelight.MOD_ID + "2", existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {

		tag(ExtraDelightTags.CHILLING_ITEMS).add(Items.BLUE_ICE, Items.PACKED_ICE, Items.ICE, Items.SNOW_BLOCK,
				Items.SNOWBALL
                ,SummerCitrus.ICE_CUBES.get()
        );
		tag(ExtraDelightTags.CAKE_SLICE).add(ModItems.CAKE_SLICE.get(), ExtraDelightItems.COFFEE_CAKE_SLICE.get(),
				ExtraDelightItems.CHOCOLATE_CAKE.get()
                ,SummerCitrus.LEMON_CUCUMBER_CAKE_SLICE.get(), SummerCitrus.MELON_LAYER_CAKE_SLICE.get(), SummerCitrus.KYIV_CAKE_SLICE.get()
        );
		tag(ExtraDelightTags.EGG_OR_YOLK).add(ExtraDelightItems.EGG_YOLK.get()).addTag(Tags.Items.EGGS);

        tag(ExtraDelightTags.ICE_CUBES).add(SummerCitrus.ICE_CUBES.get());

		tag(ExtraDelightTags.IS_JELLY_INGREDIENT).add(ExtraDelightItems.MINT.get());

		tag(CompatTags.SLICED_INGREDIENTS).add(ExtraDelightItems.SLICED_ONION.get(),
				ExtraDelightItems.SLICED_TOMATO.get(), ExtraDelightItems.CHEESE.get(),
				ExtraDelightItems.SLICED_CHILI.get());

		// Serene Seasons
		tag(CompatTags.SERENE_SEASONS_SUMMER_CROPS).add(ExtraDelightItems.CINNAMON_SAPLING.get(),
				ExtraDelightItems.GINGER_CUTTING.get(), ExtraDelightItems.MINT.get(),
				ExtraDelightItems.CORN_SEEDS.get(), ExtraDelightItems.MALLOW_ROOT.get(),
				ExtraDelightItems.CHILI_SEEDS.get(), ExtraDelightItems.PEANUTS.get(),
				ExtraDelightItems.HAZELNUT_SAPLING.get(), ExtraDelightItems.COFFEE_CHERRIES.get(),
				ExtraDelightItems.APPLE_SAPLING.get(), ExtraDelightItems.GARLIC_CLOVE.get()

                ,
				Fermentation.CUCUMBER_SEED.get(), Fermentation.SOYBEANS.get(), SummerCitrus.LIME_SAPLING_ITEM.get(),
				SummerCitrus.ORANGE_SAPLING_ITEM.get(), SummerCitrus.GRAPEFRUIT_SAPLING_ITEM.get()


        );
		tag(CompatTags.SERENE_SEASONS_AUTUMN_CROPS).add(ExtraDelightItems.MINT.get(),
				ExtraDelightItems.MALLOW_ROOT.get(), ExtraDelightItems.CHILI_SEEDS.get(),
				ExtraDelightItems.PEANUTS.get(), ExtraDelightItems.HAZELNUT_SAPLING.get(),
				ExtraDelightItems.COFFEE_CHERRIES.get(), ExtraDelightItems.APPLE_SAPLING.get(),
				Fermentation.SOYBEANS.get(), SummerCitrus.LIME_SAPLING_ITEM.get(),
				SummerCitrus.ORANGE_SAPLING_ITEM.get(), SummerCitrus.GRAPEFRUIT_SAPLING_ITEM.get()
        );
		tag(CompatTags.SERENE_SEASONS_WINTER_CROPS).add(ExtraDelightItems.GARLIC_CLOVE.get()
                , SummerCitrus.LEMON_SAPLING_ITEM.get(), SummerCitrus.ORANGE_SAPLING_ITEM.get(), SummerCitrus.GRAPEFRUIT_SAPLING_ITEM.get()
        );
		tag(CompatTags.SERENE_SEASONS_SPRING_CROPS).add(ExtraDelightItems.CINNAMON_SAPLING.get(),
				ExtraDelightItems.GINGER_CUTTING.get(), ExtraDelightItems.MINT.get(),
				ExtraDelightItems.CORN_SEEDS.get(), ExtraDelightItems.CHILI_SEEDS.get(),
				ExtraDelightItems.HAZELNUT_SAPLING.get(), ExtraDelightItems.COFFEE_CHERRIES.get(),
				ExtraDelightItems.APPLE_SAPLING.get(), ExtraDelightItems.GARLIC_CLOVE.get()
                , Fermentation.CUCUMBER_SEED.get(), Fermentation.SOYBEANS.get(), SummerCitrus.LEMON_SAPLING_ITEM.get(), SummerCitrus.LIME_SAPLING_ITEM.get()
        );

		// Storage Blocks
		tag(Tags.Items.STORAGE_BLOCKS).addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CHEESE)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_BUTTER).addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_FLOUR)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_MEAL)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_SUGAR)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_ON_COB)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_HUSK)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_DRIED_CORN_HUSK)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_COB)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GINGER).addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_EGGS)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_APPLE)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GOLDEN_APPLE)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_BROWN_MUSHROOM)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_RED_MUSHROOM)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_SWEET_BERRIES)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GLOW_BERRIES)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_SILK)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GROUND_CINNAMON)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_RAW_CINNAMON)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CINNAMON_STICK)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_BREAD_CRUMBS)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_MINT)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_MARSHMALLOW)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GOLDEN_CARROT)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_BLOOD_CHOCOLATE_BAR)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_DARK_CHOCOLATE_BAR)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_MILK_CHOCOLATE_BAR)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_WHITE_CHOCOLATE_BAR)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CHILI)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CHILI_POWDER)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_PEANUTS_IN_SHELL)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_PEANUTS)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_ROASTED_PEANUTS)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_HAZELNUTS_IN_SHELL)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_HAZELNUTS)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_ROASTED_HAZELNUTS)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_MALLOW_ROOT)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_MALLOW_POWDER)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_COFFEE_CHERRIES)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GREEN_COFFEE)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_COFFEE_BEANS)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GROUND_COFFEE)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_COCOA_BEANS)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_ROASTED_COCOA_BEANS)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_COCOA_SOLIDS)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_COCOA_POWDER)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_KERNELS)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GARLIC)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CUCUMBER)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_SOYBEANS).addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_SALT)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_LEMON).addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_LIME)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_ORANGE)
				.addTag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GRAPEFRUIT);

		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CHEESE).add(ExtraDelightItems.CHEESE_BLOCK_ITEM.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_BUTTER).add(ExtraDelightItems.BUTTER_BLOCK_ITEM.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_FLOUR).add(ExtraDelightItems.FLOUR_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_MEAL).add(ExtraDelightItems.CORNMEAL_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_SUGAR).add(ExtraDelightItems.SUGAR_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_ON_COB).add(ExtraDelightItems.CORN_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_HUSK).add(ExtraDelightItems.CORN_HUSK_BUNDLE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_DRIED_CORN_HUSK).add(ExtraDelightItems.DRIED_CORN_HUSK_BUNDLE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_COB).add(ExtraDelightItems.CORN_COB_BUNDLE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GINGER).add(ExtraDelightItems.GINGER_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_EGGS).add(ExtraDelightItems.EGG_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_APPLE).add(ExtraDelightItems.APPLE_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GOLDEN_APPLE).add(ExtraDelightItems.GOLDEN_APPLE_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_BROWN_MUSHROOM).add(ExtraDelightItems.BROWN_MUSHROOM_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_RED_MUSHROOM).add(ExtraDelightItems.RED_MUSHROOM_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_SWEET_BERRIES).add(ExtraDelightItems.SWEET_BERRY_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GLOW_BERRIES).add(ExtraDelightItems.GLOW_BERRY_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_SILK).add(ExtraDelightItems.CORN_SILK_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GROUND_CINNAMON)
				.add(ExtraDelightItems.GROUND_CINNAMON_BLOCK_ITEM.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_RAW_CINNAMON).add(ExtraDelightItems.RAW_CINNAMON_BLOCK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CINNAMON_STICK).add(ExtraDelightItems.CINNAMON_STICK_BLOCK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_BREAD_CRUMBS).add(ExtraDelightItems.BREADCRUMB_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_MINT).add(ExtraDelightItems.MINT_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_MARSHMALLOW).add(ExtraDelightItems.MARSHMALLOW_BLOCK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GOLDEN_CARROT).add(ExtraDelightItems.GOLDEN_CARROT_CRATE_BLOCK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_BLOOD_CHOCOLATE_BAR)
				.add(ExtraDelightItems.BLOOD_CHOCOLATE_BLOCK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_DARK_CHOCOLATE_BAR).add(ExtraDelightItems.DARK_CHOCOLATE_BLOCK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_MILK_CHOCOLATE_BAR).add(ExtraDelightItems.MILK_CHOCOLATE_BLOCK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_WHITE_CHOCOLATE_BAR)
				.add(ExtraDelightItems.WHITE_CHOCOLATE_BLOCK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CHILI).add(ExtraDelightItems.CHILI_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CHILI_POWDER).add(ExtraDelightItems.CHILI_POWDER_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_PEANUTS_IN_SHELL).add(ExtraDelightItems.PEANUT_IN_SHELL_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_PEANUTS).add(ExtraDelightItems.PEANUT_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_ROASTED_PEANUTS).add(ExtraDelightItems.ROASTED_PEANUT_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_HAZELNUTS_IN_SHELL)
				.add(ExtraDelightItems.HAZELNUT_IN_SHELL_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_HAZELNUTS).add(ExtraDelightItems.HAZELNUT_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_ROASTED_HAZELNUTS).add(ExtraDelightItems.ROASTED_HAZELNUT_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_MALLOW_ROOT).add(ExtraDelightItems.MALLOW_ROOT_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_MALLOW_POWDER).add(ExtraDelightItems.MALLOW_POWDER_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_COFFEE_CHERRIES).add(ExtraDelightItems.COFFEE_CHERRY_CRATE.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GREEN_COFFEE).add(ExtraDelightItems.GREEN_COFFEE_BEAN_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_COFFEE_BEANS).add(ExtraDelightItems.COFFEE_BEAN_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GROUND_COFFEE).add(ExtraDelightItems.GROUND_COFFEE_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_COCOA_BEANS).add(ExtraDelightItems.COCOA_BEAN_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_ROASTED_COCOA_BEANS)
				.add(ExtraDelightItems.ROASTED_COCOA_BEAN_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_COCOA_SOLIDS).add(ExtraDelightItems.COCOA_SOLIDS_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_COCOA_POWDER).add(ExtraDelightItems.COCOA_POWDER_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CORN_KERNELS).add(ExtraDelightItems.CORN_SACK.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GARLIC).add(ExtraDelightItems.GARLIC_CRATE.get());

		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_CUCUMBER).add(Fermentation.CUCUMBER_CRATE_ITEM.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_SOYBEANS).add(Fermentation.SOYBEAN_SACK_ITEM.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_SALT).add(ExtraDelightItems.SALT_BLOCK_ITEM.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_LEMON).add(SummerCitrus.LEMON_CRATE_ITEM.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_LIME).add(SummerCitrus.LIME_CRATE_ITEM.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_ORANGE).add(SummerCitrus.ORANGE_CRATE_ITEM.get());
		tag(ExtraDelightTags.STORAGE_BLOCKS_ITEM_GRAPEFRUIT).add(SummerCitrus.GRAPEFRUIT_CRATE_ITEM.get());

		tag(CompatTags.UPRIGHT_ON_BELT).add(
                ExtraDelightItems.COOKING_OIL.get(), ExtraDelightItems.VINEGAR.get(),
				ExtraDelightItems.BLOOD_CHOCOLATE_SYRUP_BOTTLE.get(), ExtraDelightItems.GRAVY.get(),
				ExtraDelightItems.DARK_CHOCOLATE_SYRUP_BOTTLE.get(), ExtraDelightItems.YEAST.get(),
				ExtraDelightItems.MILK_CHOCOLATE_SYRUP_BOTTLE.get(), ExtraDelightItems.GLOW_BERRY_JUICE.get(),
				ExtraDelightItems.WHITE_CHOCOLATE_SYRUP_BOTTLE.get(), ExtraDelightItems.SWEET_BERRY_JUICE.get(),
				ExtraDelightItems.TOMATO_JUICE.get(), ExtraDelightItems.CACTUS_JUICE.get(),
				ExtraDelightItems.SEAWEED_SALAD.get(), ExtraDelightItems.WHIPPED_CREAM.get(),
				ExtraDelightItems.EGG_MIX.get(), ExtraDelightItems.SCRAMBLED_EGGS.get(),
				ExtraDelightItems.OMELETTE_MIX.get(), ExtraDelightItems.EGG_SALAD.get(),
				ExtraDelightItems.FURIKAKE.get(), ExtraDelightItems.CURRY_POWDER.get(),
				ExtraDelightItems.SWEET_BERRY_PIE_ITEM.get(), ExtraDelightItems.GLOW_BERRY_PIE_ITEM.get(),
				ExtraDelightItems.CHEESECAKE_ITEM.get(), ExtraDelightItems.HONEY_CHEESECAKE_ITEM.get(),
				ExtraDelightItems.PUMPKIN_CHEESECAKE_ITEM.get(), ExtraDelightItems.CHOCOLATE_CHEESECAKE_ITEM.get(),
				ExtraDelightItems.APPLE_CHEESECAKE_ITEM.get(), ExtraDelightItems.GLOW_BERRY_CHEESECAKE_ITEM.get(),
				ExtraDelightItems.QUICHE.get(), ExtraDelightItems.CARROT_SALAD.get(),
				ExtraDelightItems.APPLE_SAUCE.get(), ExtraDelightItems.POTATO_SOUP.get(),
				ExtraDelightItems.TOMATO_SOUP.get(), ExtraDelightItems.FISH_SOUP.get(),
				ExtraDelightItems.CARROT_SOUP.get(), ExtraDelightItems.SALISBURY_STEAK.get(),
				ExtraDelightItems.MASHED_POTATO_GRAVY.get(), ExtraDelightItems.PORK_STEW.get(),
				ExtraDelightItems.LAMB_STEW.get(), ExtraDelightItems.CHICKEN_STEW.get(), ExtraDelightItems.CURRY.get(),
				ExtraDelightItems.BEEF_STEW_RICE.get(), ExtraDelightItems.PORK_STEW_RICE.get(),
				ExtraDelightItems.LAMB_STEW_RICE.get(), ExtraDelightItems.RABBIT_STEW_RICE.get(),
				ExtraDelightItems.CHICKEN_STEW_RICE.get(), ExtraDelightItems.FISH_STEW_RICE.get(),
				ExtraDelightItems.CURRY_RICE.get(), ExtraDelightItems.LIVER_ONIONS.get(),
				ExtraDelightItems.OXTAIL_SOUP.get(), ExtraDelightItems.HASH.get(), ExtraDelightItems.POT_ROAST.get(),
				ExtraDelightItems.MEAT_LOAF.get(), ExtraDelightItems.BBQ_RIBS.get(), ExtraDelightItems.RACK_LAMB.get(),
				ExtraDelightItems.STIRFRY.get(), ExtraDelightItems.BEEF_WELLINGTON.get(),
				ExtraDelightItems.HAGGIS.get(), ExtraDelightItems.JELLY_WHITE.get(),
				ExtraDelightItems.JELLY_ORANGE.get(), ExtraDelightItems.JELLY_MAGENTA.get(),
				ExtraDelightItems.JELLY_LIGHT_BLUE.get(), ExtraDelightItems.JELLY_YELLOW.get(),
				ExtraDelightItems.JELLY_LIME.get(), ExtraDelightItems.JELLY_PINK.get(),
				ExtraDelightItems.JELLY_GREY.get(), ExtraDelightItems.JELLY_LIGHT_GREY.get(),
				ExtraDelightItems.JELLY_CYAN.get(), ExtraDelightItems.JELLY_PURPLE.get(),
				ExtraDelightItems.JELLY_BLUE.get(), ExtraDelightItems.JELLY_BROWN.get(),
				ExtraDelightItems.JELLY_GREEN.get(), ExtraDelightItems.JELLY_RED.get(),
				ExtraDelightItems.JELLY_BLACK.get(), ExtraDelightItems.BREADING_MISANPLAS.get(),
				ExtraDelightItems.MACARONI_CHEESE.get(), ExtraDelightItems.LASAGNA.get(),
				ExtraDelightItems.HOTDISH.get(), ExtraDelightItems.FURIKAKE_RICE.get(),
				ExtraDelightItems.FISH_FLAKES.get(), ExtraDelightItems.MUSHROOM_RISOTTO.get(),
				ExtraDelightItems.SALAD.get(), ExtraDelightItems.ALFREDO_SAUCE.get(),
				ExtraDelightItems.PASTA_TOMATO.get(), ExtraDelightItems.PASTA_ALFREDO.get(),
				ExtraDelightItems.CHICKEN_ALFREDO.get(), ExtraDelightItems.FISH_SALAD.get(),
				ExtraDelightItems.COOKED_PASTA.get(), ExtraDelightItems.BUTTERED_PASTA.get(),
				ExtraDelightItems.BAD_FOOD.get(), ExtraDelightItems.CACTUS_EGGS.get(),
				ExtraDelightItems.CACTUS_SOUP.get(), ExtraDelightItems.CACTUS_SALAD.get(),
				ExtraDelightItems.MEAT_PIE_BLOCK_ITEM.get(), ExtraDelightItems.TEA.get(),
				ExtraDelightItems.STEWED_APPLES.get(), ExtraDelightItems.APPLE_FRITTERS.get(),
				ExtraDelightItems.CARAMEL_POPCORN.get(), ExtraDelightItems.CORNBREAD.get(),
				ExtraDelightItems.CARAMEL_SAUCE.get(), ExtraDelightItems.PUMPKIN_PIE_ITEM.get(),
				ExtraDelightItems.CORN_PUDDING.get(), ExtraDelightItems.APPLE_CRISP.get(),
				ExtraDelightItems.STUFFING.get(), ExtraDelightItems.POTATO_AU_GRATIN.get(),
				ExtraDelightItems.ICE_CREAM.get(), ExtraDelightItems.CHOCOLATE_ICE_CREAM.get(),
				ExtraDelightItems.GLOW_BERRY_ICE_CREAM.get(), ExtraDelightItems.SWEET_BERRY_ICE_CREAM.get(),
				ExtraDelightItems.PUMPKIN_ICE_CREAM.get(), ExtraDelightItems.HONEY_ICE_CREAM.get(),
				ExtraDelightItems.APPLE_ICE_CREAM.get(), ExtraDelightItems.COOKIE_DOUGH_ICE_CREAM.get(),
				ExtraDelightItems.MINT_CHIP_ICE_CREAM.get(), ExtraDelightItems.FRENCH_TOAST.get(),
				ExtraDelightItems.MILKSHAKE.get(), ExtraDelightItems.CHOCOLATE_MILKSHAKE.get(),
				ExtraDelightItems.GLOW_BERRY_MILKSHAKE.get(), ExtraDelightItems.SWEET_BERRY_MILKSHAKE.get(),
				ExtraDelightItems.PUMPKIN_MILKSHAKE.get(), ExtraDelightItems.HONEY_MILKSHAKE.get(),
				ExtraDelightItems.APPLE_MILKSHAKE.get(), ExtraDelightItems.COOKIE_DOUGH_MILKSHAKE.get(),
				ExtraDelightItems.MINT_CHIP_MILKSHAKE.get(), ExtraDelightItems.CHOCOLATE_MILK.get(),
				ExtraDelightItems.EGGNOG.get(), ExtraDelightItems.GINGER_BEER.get(), ExtraDelightItems.CONGEE.get(),
				ExtraDelightItems.LUGAW.get(), ExtraDelightItems.BEET_MINT_SALAD.get(),
				ExtraDelightItems.MONKEY_BREAD.get(), ExtraDelightItems.MINT_LAMB.get(),
				ExtraDelightItems.CHARCUTERIE_BOARD.get(), ExtraDelightItems.CHRISTMAS_PUDDING.get(),
				ExtraDelightItems.STICKY_TOFFEE_PUDDING_SLICE.get(), ExtraDelightItems.HORCHATA.get(),
				//ExtraDelightItems.MINT_JELLY.get(),
                ExtraDelightItems.PUNCH.get(), ExtraDelightItems.XOCOLATL.get(),
				ExtraDelightItems.GOURMET_HOT_CHOCOLATE.get(), ExtraDelightItems.COFFEE.get(),
				ExtraDelightItems.CRISP_RICE_CEREAL.get(), ExtraDelightItems.BLACK_FOREST_TRIFLE.get(),
				ExtraDelightItems.CORN_FLAKES_CEREAL.get(), ExtraDelightItems.COFFEE_CAKE_FEAST.get(),
				ExtraDelightItems.CHOCOLATE_CAKE_BLOCK.get(), ExtraDelightItems.COCOA_BUTTER_BOTTLE.get(),
				ExtraDelightItems.PEANUT_BUTTER_BOTTLE.get(), ExtraDelightItems.MARSHMALLOW_FLUFF_BOTTLE.get(),
				ExtraDelightItems.NUT_BUTTER_MILKSHAKE.get(), ExtraDelightItems.HAZELNUT_SPREAD_BOTTLE.get(),
				ExtraDelightItems.MISSISSIPPI_MUD_PIE.get(), ExtraDelightItems.GRASSHOPPER_PIE.get(),
				ExtraDelightItems.AFFOGATO.get(), ExtraDelightItems.MILK_TART_FEAST.get(),
				ExtraDelightItems.BACON_EGG_PIE.get(), ExtraDelightItems.APPLE_SLAW.get(),
				ExtraDelightItems.PORK_AND_APPLES.get(), ExtraDelightItems.STUFFED_APPLE.get(),
				ExtraDelightItems.STUFFED_APPLE_ICE_CREAM.get(), ExtraDelightItems.MULLIGATAWNY_SOUP.get(),
				ExtraDelightItems.AEBLEFLAESK.get(), ExtraDelightItems.CANDY_BAR_SALAD.get(),
				ExtraDelightItems.CHILI_CON_CARNE.get(), ExtraDelightItems.WHITE_CHILI.get(),
				ExtraDelightItems.NUT_BUTTER_ICE_CREAM.get(), ExtraDelightItems.NUT_BUTTER_MILKSHAKE.get(),
				ExtraDelightItems.HAZELNUT_SOUP.get(), ExtraDelightItems.POTATO_SALAD.get(),
				ExtraDelightItems.ONION_SOUP.get(), ExtraDelightItems.FAT_POTATOES.get(),
				ExtraDelightItems.PANFORTE.get(), ExtraDelightItems.BORSCHT.get(),
				ExtraDelightItems.DEVILLED_SAUSAGES.get(), ExtraDelightItems.AGLIO_E_OLIO.get(),
				ExtraDelightItems.PENNE_ALL_ARRABIATA.get(),
                Fermentation.SAUERKRAUT_ITEM.get(), Fermentation.KIMCHI_ITEM.get(),
				Fermentation.MISO_PASTE_ITEM.get(), Fermentation.FISH_SAUCE_ITEM.get(), Fermentation.NATTO_ITEM.get(),
				Fermentation.SOAKED_SOYBEANS_ITEM.get(), Fermentation.MASHED_SOYBEANS_ITEM.get(),
				Fermentation.SOY_MILK.get(), Fermentation.GAZPACHO.get(), Fermentation.EDAMAME.get(),
				Fermentation.BEEF_BULGOGI.get(), Fermentation.HONEY_CHILI_CHICKEN.get(),
				Fermentation.CARAMEL_CHICKEN.get(), Fermentation.SOY_GLAZED_SALMON_ITEM.get(),
				Fermentation.STEAK_PICKLED_ONION_PIE_ITEM.get(), Fermentation.SHIRAZI_SALAD.get(),
				Fermentation.CUCUMBER_SALAD.get(), Fermentation.MISO_SOUP.get(), Fermentation.NATTO_AND_RICE.get(),
				Fermentation.SAUERKRAUT_SOUP.get(), Fermentation.YEAST_SPREAD.get(), Fermentation.PICKLE_JUICE.get(),
				Fermentation.SAUERKRAUT_AND_SAUSAGE.get(), Fermentation.MORKOVCHA.get(),
				Fermentation.ZUPA_OGORKOWA.get(), Fermentation.KIMCHI_FRIED_RICE.get(), Fermentation.KONGJANG.get(),
				SummerCitrus.LEMON_JUICE.get(), SummerCitrus.LIME_JUICE.get(), SummerCitrus.ORANGE_JUICE.get(),
				SummerCitrus.GRAPEFRUIT_JUICE.get(), ExtraDelightItems.STIFF_PEAKS.get(), ExtraDelightItems.EGG_WHITE.get(),
				SummerCitrus.LEMON_CURD.get(), SummerCitrus.LEMON_MERINGUE_PIE_ITEM.get(),
				SummerCitrus.KEY_LIME_PIE_ITEM.get(), SummerCitrus.LEMONADE.get(), SummerCitrus.LIMEADE.get(),
				SummerCitrus.ORANGEADE.get(), SummerCitrus.MELON_GAZPACHO.get(), SummerCitrus.THAI_MELON_SALAD.get(),
				SummerCitrus.DALGONA_COFFEE.get(), SummerCitrus.GRAPEFRUIT_BEETROOT_SALAD.get(),
				SummerCitrus.CITRUS_ONION_SALAD.get(), SummerCitrus.MELON_FRUIT_SALAD_SERVING.get(),
				SummerCitrus.LEMON_CUCUMBER_CAKE_ITEM.get(), SummerCitrus.BAKED_COD_SERVING.get(),
				SummerCitrus.MELON_LAYER_CAKE_ITEM.get(), SummerCitrus.GRAPEFRUIT_SORBET.get(),
				SummerCitrus.LEMON_DELICIOUS.get(), SummerCitrus.ORANGE_CHICKEN.get(),
				SummerCitrus.MELON_RIND_STIRFRY.get(), SummerCitrus.LIME_SOUFFLE.get(),
				SummerCitrus.CHEESE_SOUFFLE.get(), SummerCitrus.PRESERVED_LEMON_PASTA.get(),
				SummerCitrus.MELON_LIME_GLAZED_CHICKEN.get(), SummerCitrus.KYIV_CAKE_ITEM.get(),
				SummerCitrus.RAW_BAKED_ALASKA_ITEM.get(), SummerCitrus.BAKED_ALASKA_ITEM.get()
        ).addTag(ExtraDelightTags.CONDIMENTS);



		for (EDItemGenerator.Drink d : EDItemGenerator.drinks) {
			parseTaNTag(d);
		}

		for (RegistryObject<Item> i : EDItemGenerator.hotFood) {
			tag(ExtraDelightTags.HEATING_CONSUMED_ITEMS).add(i.get());
		}

		for (RegistryObject<Item> i : EDItemGenerator.coldFood) {
			tag(ExtraDelightTags.COOLING_CONSUMED_ITEMS).add(i.get());
		}
	}

	// Tough as Nails

	public void parseTaNTag(EDItemGenerator.Drink d) {
		tag(ExtraDelightTags.DRINKS).add(d.item.get());

		if (d.isHot)
			tag(ExtraDelightTags.HEATING_CONSUMED_ITEMS).add(d.item.get());

		if (d.isCold)
			tag(ExtraDelightTags.COOLING_CONSUMED_ITEMS).add(d.item.get());

		switch (d.thirst) {
		case 1:
			tag(ExtraDelightTags.ONE_THIRST_DRINKS).add(d.item.get());
			break;
		case 2:
			tag(ExtraDelightTags.TWO_THIRST_DRINKS).add(d.item.get());
			break;
		case 3:
			tag(ExtraDelightTags.THREE_THIRST_DRINKS).add(d.item.get());
			break;
		case 4:
			tag(ExtraDelightTags.FOUR_THIRST_DRINKS).add(d.item.get());
			break;
		case 5:
			tag(ExtraDelightTags.FIVE_THIRST_DRINKS).add(d.item.get());
			break;
		case 6:
			tag(ExtraDelightTags.SIX_THIRST_DRINKS).add(d.item.get());
			break;
		case 7:
			tag(ExtraDelightTags.SEVEN_THIRST_DRINKS).add(d.item.get());
			break;
		case 8:
			tag(ExtraDelightTags.EIGHT_THIRST_DRINKS).add(d.item.get());
			break;
		case 9:
			tag(ExtraDelightTags.NINE_THIRST_DRINKS).add(d.item.get());
			break;
		case 10:
			tag(ExtraDelightTags.TEN_THIRST_DRINKS).add(d.item.get());
			break;
		case 11:
			tag(ExtraDelightTags.ELEVEN_THIRST_DRINKS).add(d.item.get());
			break;
		case 12:
			tag(ExtraDelightTags.TWELVE_THIRST_DRINKS).add(d.item.get());
			break;
		case 13:
			tag(ExtraDelightTags.THIRTEEN_THIRST_DRINKS).add(d.item.get());
			break;
		case 14:
			tag(ExtraDelightTags.FOURTEEN_THIRST_DRINKS).add(d.item.get());
			break;
		case 15:
			tag(ExtraDelightTags.FIFTEEN_THIRST_DRINKS).add(d.item.get());
			break;
		case 16:
			tag(ExtraDelightTags.SIXTEEN_THIRST_DRINKS).add(d.item.get());
			break;
		case 17:
			tag(ExtraDelightTags.SEVENTEEN_THIRST_DRINKS).add(d.item.get());
			break;
		case 18:
			tag(ExtraDelightTags.EIGHTEEN_THIRST_DRINKS).add(d.item.get());
			break;
		case 19:
			tag(ExtraDelightTags.NINETEEN_THIRST_DRINKS).add(d.item.get());
			break;
		case 20:
			tag(ExtraDelightTags.TWENTY_THIRST_DRINKS).add(d.item.get());
			break;
		}

		switch (d.hydration) {
		case 1:
			tag(ExtraDelightTags.TEN_HYDRATION_DRINKS).add(d.item.get());
			break;
		case 2:
			tag(ExtraDelightTags.TWENTY_HYDRATION_DRINKS).add(d.item.get());
			break;
		case 3:
			tag(ExtraDelightTags.THIRTY_HYDRATION_DRINKS).add(d.item.get());
			break;
		case 4:
			tag(ExtraDelightTags.FORTY_HYDRATION_DRINKS).add(d.item.get());
			break;
		case 5:
			tag(ExtraDelightTags.FIFTY_HYDRATION_DRINKS).add(d.item.get());
			break;
		case 6:
			tag(ExtraDelightTags.SIXTY_HYDRATION_DRINKS).add(d.item.get());
			break;
		case 7:
			tag(ExtraDelightTags.SEVENTY_HYDRATION_DRINKS).add(d.item.get());
			break;
		case 8:
			tag(ExtraDelightTags.EIGHTY_HYDRATION_DRINKS).add(d.item.get());
			break;
		case 9:
			tag(ExtraDelightTags.NINETY_HYDRATION_DRINKS).add(d.item.get());
			break;
		case 10:
			tag(ExtraDelightTags.ONE_HUNDRED_HYDRATION_DRINKS).add(d.item.get());
			break;
		}

		switch (d.poison) {
		case 25:
			tag(ExtraDelightTags.TWENTY_FIVE_POISON_CHANCE_DRINKS).add(d.item.get());
			break;
		case 50:
			tag(ExtraDelightTags.FIFTY_POISON_CHANCE_DRINKS).add(d.item.get());
			break;
		case 75:
			tag(ExtraDelightTags.SEVENTY_FIVE_POISON_CHANCE_DRINKS).add(d.item.get());
			break;
		case 100:
			tag(ExtraDelightTags.ONE_HUNDRED_POISON_CHANCE_DRINKS).add(d.item.get());
			break;
		}

	}

}
