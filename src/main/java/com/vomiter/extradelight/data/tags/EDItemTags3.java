package com.vomiter.extradelight.data.tags;

import java.util.concurrent.CompletableFuture;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.Fermentation;
import com.vomiter.extradelight.registry.SummerCitrus;
import com.vomiter.extradelight.util.EDItemGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import com.vomiter.extradelight.registry.AestheticBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;

@SuppressWarnings({"unused", "deprecation", "SpellCheckingInspection"})
public class EDItemTags3 extends ItemTagsProvider {

	public EDItemTags3(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
			CompletableFuture<TagLookup<Block>> blockTagProvider,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, blockTagProvider, ExtraDelight.MOD_ID + "3", existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(ExtraDelightTags.CAN_BE_SALTED).add(ExtraDelightItems.BACON_CHEESEBURGER.get(),
				ExtraDelightItems.BACON_EGG_CHEESE_SANDWICH.get(), ExtraDelightItems.BACON_EGG_SANDWICH.get(),
				ExtraDelightItems.BAD_FOOD.get(), ExtraDelightItems.BBQ_RIBS.get(),
				ExtraDelightItems.BEEF_STEW_RICE.get(), ExtraDelightItems.BEEF_WELLINGTON.get(),
				ExtraDelightItems.BEET_MINT_SALAD.get(), ExtraDelightItems.BOILED_EGG.get(),
				ExtraDelightItems.BUTTERED_PASTA.get(), ExtraDelightItems.CACTUS_EGGS.get(),
				ExtraDelightItems.CACTUS_SALAD.get(), ExtraDelightItems.CACTUS_SOUP.get(),
				ExtraDelightItems.CARAMEL_CHEESECAKE_SLICE.get(), ExtraDelightItems.CARAMEL_CUSTARD.get(),
				ExtraDelightItems.CARAMEL_POPCORN.get(), ExtraDelightItems.CARAMEL_POPSICLE.get(),
				ExtraDelightItems.CARROT_SALAD.get(), ExtraDelightItems.CHEESE_SANDWICH.get(),
				ExtraDelightItems.CHEESEBURGER.get(), ExtraDelightItems.CHICKEN_ALFREDO.get(),
				ExtraDelightItems.CHICKEN_FRIED_STEAK.get(), ExtraDelightItems.CHICKEN_PARM.get(),
				ExtraDelightItems.CHICKEN_STEW.get(), ExtraDelightItems.CHICKEN_STEW_RICE.get(),
				ExtraDelightItems.CONGEE.get(), ExtraDelightItems.COOKED_CACTUS.get(),
				ExtraDelightItems.COOKED_CORN.get(), ExtraDelightItems.COOKED_PASTA.get(),
				ExtraDelightItems.CORN_CHOWDER.get(), ExtraDelightItems.CORN_FRITTERS.get(),
				ExtraDelightItems.CORN_PUDDING.get(), ExtraDelightItems.CORNBREAD.get(),
				ExtraDelightItems.CROQUE_MADAME.get(), ExtraDelightItems.CROQUE_MONSIEUR.get(),
				ExtraDelightItems.CURRY.get(), ExtraDelightItems.CURRY_RICE.get(), ExtraDelightItems.DRIED_FRUIT.get(),
				ExtraDelightItems.EGG_BASKET.get(), ExtraDelightItems.EGG_SALAD.get(),
				ExtraDelightItems.EGG_SALAD_SANDWICH.get(), ExtraDelightItems.FISH_AND_CHIPS.get(),
				ExtraDelightItems.FISH_CAKES.get(), ExtraDelightItems.FISH_SALAD.get(),
				ExtraDelightItems.FISH_SALAD_SANDWICH.get(), ExtraDelightItems.FISH_SOUP.get(),
				ExtraDelightItems.FISH_STEW_RICE.get(), ExtraDelightItems.FRENCH_FRIES.get(),
				ExtraDelightItems.FRENCH_TOAST.get(), ExtraDelightItems.FRIED_BRAINS.get(),
				ExtraDelightItems.FRIED_CHICKEN.get(), ExtraDelightItems.FRIED_FISH.get(),
				ExtraDelightItems.FRIED_MUSHROOMS.get(), ExtraDelightItems.FURIKAKE_RICE.get(),
				ExtraDelightItems.GRILLED_CHEESE.get(), ExtraDelightItems.GRILLED_CORN_ON_COB.get(),
				ExtraDelightItems.HAGGIS.get(), ExtraDelightItems.HASH.get(), ExtraDelightItems.HASHBROWNS.get(),
				ExtraDelightItems.HOTDISH.get(), ExtraDelightItems.JERKY.get(), ExtraDelightItems.LAMB_STEW.get(),
				ExtraDelightItems.LAMB_STEW_RICE.get(), ExtraDelightItems.LASAGNA.get(),
				ExtraDelightItems.LIVER_ONIONS.get(), ExtraDelightItems.LUGAW.get(),
				ExtraDelightItems.MACARONI_CHEESE.get(), ExtraDelightItems.MASHED_POTATO_GRAVY.get(),
				ExtraDelightItems.MEAT_LOAF.get(), ExtraDelightItems.MEAT_LOAF_SANDWICH.get(),
				ExtraDelightItems.MEAT_PIE_SLICE.get(), ExtraDelightItems.MUSHROOM_BURGER.get(),
				ExtraDelightItems.MUSHROOM_RISOTTO.get(), ExtraDelightItems.OMELETTE.get(),
				ExtraDelightItems.OXTAIL_SOUP.get(), ExtraDelightItems.PASTA_ALFREDO.get(),
				ExtraDelightItems.PASTA_TOMATO.get(), ExtraDelightItems.POPCORN.get(),
				ExtraDelightItems.PORK_STEW.get(), ExtraDelightItems.PORK_STEW_RICE.get(),
				ExtraDelightItems.PORK_TENDERLOIN.get(), ExtraDelightItems.PORK_TENDERLOIN_SANDWICH.get(),
				ExtraDelightItems.POT_ROAST.get(), ExtraDelightItems.POTATO_AU_GRATIN.get(),
				ExtraDelightItems.POTATO_CHIPS.get(), ExtraDelightItems.POTATO_SOUP.get(),
				ExtraDelightItems.PULLED_PORK_SANDWICH.get(), ExtraDelightItems.QUICHE_SLICE.get(),
				ExtraDelightItems.RABBIT_STEW_RICE.get(), ExtraDelightItems.RACK_LAMB.get(),
				ExtraDelightItems.RICEBALL.get(), ExtraDelightItems.RICEBALL_FILLED.get(),
				ExtraDelightItems.ROASTED_CARROT.get(), ExtraDelightItems.ROASTED_PUMPKIN_SEEDS.get(),
				ExtraDelightItems.ROLL.get(), ExtraDelightItems.SALAD.get(), ExtraDelightItems.SALISBURY_STEAK.get(),
				ExtraDelightItems.SAUSAGE_ROLL.get(), ExtraDelightItems.SCRAMBLED_EGGS.get(),
				ExtraDelightItems.SEAWEED_CRISPS.get(), ExtraDelightItems.SEAWEED_SALAD.get(),
				ExtraDelightItems.SLICED_APPLE.get(), ExtraDelightItems.SLICED_ONION.get(),
				ExtraDelightItems.SLICED_TOMATO.get(), ExtraDelightItems.SOS.get(), ExtraDelightItems.STIRFRY.get(),
				ExtraDelightItems.STUFFED_CACTUS.get(), ExtraDelightItems.STUFFED_HEART.get(),
				ExtraDelightItems.STUFFED_MUSHROOMS.get(), ExtraDelightItems.STUFFING.get(),
				ExtraDelightItems.SUNFLOWER_SEEDS.get(), ExtraDelightItems.TOMATO_SOUP.get(),
				ExtraDelightItems.CONGEE.get(), ExtraDelightItems.LUGAW.get(), ExtraDelightItems.BEET_MINT_SALAD.get(),
				ExtraDelightItems.CRACKERS.get(), ExtraDelightItems.CROQUE_MADAME.get(),
				ExtraDelightItems.CROQUE_MONSIEUR.get(), ExtraDelightItems.ROLL.get(),
				ExtraDelightItems.MINT_LAMB.get(), ExtraDelightItems.BLOOD_CHOCOLATE_BAR.get(),
				ExtraDelightItems.BLOOD_CHOCOLATE_FILLED_BAR.get(), ExtraDelightItems.DARK_CHOCOLATE_BAR.get(),
				ExtraDelightItems.DARK_CHOCOLATE_FILLED_BAR.get(), ExtraDelightItems.MILK_CHOCOLATE_BAR.get(),
				ExtraDelightItems.MILK_CHOCOLATE_FILLED_BAR.get(), ExtraDelightItems.WHITE_CHOCOLATE_BAR.get(),
				ExtraDelightItems.WHITE_CHOCOLATE_FILLED_BAR.get(), ExtraDelightItems.BLOOD_CHOCOLATE_TRUFFLE.get(),
				ExtraDelightItems.DARK_CHOCOLATE_TRUFFLE.get(), ExtraDelightItems.MILK_CHOCOLATE_TRUFFLE.get(),
				ExtraDelightItems.WHITE_CHOCOLATE_TRUFFLE.get(), ExtraDelightItems.PORK_AND_APPLES.get(),
				ExtraDelightItems.APPLE_SLAW.get(), ExtraDelightItems.MULLIGATAWNY_SOUP.get(),
				ExtraDelightItems.AEBLEFLAESK.get(), ExtraDelightItems.JALAPENO_POPPER.get(),
				ExtraDelightItems.JALAPENO_STUFFED_POTATO.get(), ExtraDelightItems.CHILI_CHEESE_CORNBREAD_MUFFIN.get(),
				ExtraDelightItems.CHILI_CON_CARNE.get(), ExtraDelightItems.WHITE_CHILI.get(),
				ExtraDelightItems.HAZELNUT_SOUP.get(), ExtraDelightItems.POTATO_SALAD.get(),
				ExtraDelightItems.ONION_SOUP.get(), ExtraDelightItems.BACON_EGG_PIE_SLICE.get(),
				ExtraDelightItems.ONION_BHAJI.get(), ExtraDelightItems.FAT_POTATOES.get(),
				ExtraDelightItems.CURRYWURST.get(), ExtraDelightItems.BORSCHT.get(), ExtraDelightItems.PAMONHA.get());

		tag(ExtraDelightTags.CORN).add(ExtraDelightItems.CORN_ON_COB.get());
		tag(ExtraDelightTags.CORN_ON_COB).add(ExtraDelightItems.CORN_ON_COB.get());
		tag(ExtraDelightTags.CORN_KERNELS).add(ExtraDelightItems.CORN_SEEDS.get());
		tag(ExtraDelightTags.CORN_MEAL).add(ExtraDelightItems.CORN_MEAL.get()).addOptionalTag(CompatTags.CORNMEAL);
		tag(CompatTags.CORNMEAL).add(ExtraDelightItems.CORN_MEAL.get());
		tag(ExtraDelightTags.COOKED_CACTUS).add(ExtraDelightItems.COOKED_CACTUS.get());
		tag(ExtraDelightTags.GROUND_CINNAMON).add(ExtraDelightItems.GROUND_CINNAMON.get())
				.addOptionalTag(CompatTags.DUSTS_CINNAMON).addOptionalTag(CompatTags.GROUNDCINNAMON);
		tag(CompatTags.DUSTS_CINNAMON).add(ExtraDelightItems.GROUND_CINNAMON.get());
		tag(CompatTags.GROUNDCINNAMON).add(ExtraDelightItems.GROUND_CINNAMON.get());
		tag(ExtraDelightTags.MINT).add(ExtraDelightItems.MINT.get());
		tag(ExtraDelightTags.WHIPPED_CREAM).add(ExtraDelightItems.WHIPPED_CREAM.get());
		tag(ExtraDelightTags.FISH_FLAKES).add(ExtraDelightItems.FISH_FLAKES.get());
		tag(ExtraDelightTags.BOILED_EGG).add(ExtraDelightItems.BOILED_EGG.get());
		tag(ExtraDelightTags.ALFREDO_SAUCE).add(ExtraDelightItems.ALFREDO_SAUCE.get());
		tag(ExtraDelightTags.CROUTONS).add(ExtraDelightItems.CROUTONS.get());
		tag(ExtraDelightTags.DRIED_FRUIT).add(ExtraDelightItems.DRIED_FRUIT.get());
		tag(ExtraDelightTags.BREAD_CRUMBS).add(ExtraDelightItems.BREAD_CRUMBS.get());
		tag(ExtraDelightTags.POPCORN).add(ExtraDelightItems.POPCORN.get()).addOptionalTag(CompatTags.FOODS_POPCORN);
		tag(CompatTags.FOODS_POPCORN).add(ExtraDelightItems.POPCORN.get());
		tag(ExtraDelightTags.CINNAMON_STICK).add(ExtraDelightItems.CINNAMON_STICK.get());

		tag(ExtraDelightTags.SUGAR_COOKIE_DOUGH).add(ExtraDelightItems.SUGAR_COOKIE_DOUGH.get());
		tag(ExtraDelightTags.GINGERBREAD_COOKIE_DOUGH).add(ExtraDelightItems.GINGERBREAD_COOKIE_DOUGH.get());
		tag(ExtraDelightTags.GLOW_BERRY_COOKIE_DOUGH).add(ExtraDelightItems.GLOW_BERRY_COOKIE_DOUGH.get());
		tag(ExtraDelightTags.HONEY_COOKIE_DOUGH).add(ExtraDelightItems.HONEY_COOKIE_DOUGH.get());
		tag(ExtraDelightTags.PUMPKIN_COOKIE_DOUGH).add(ExtraDelightItems.PUMPKIN_COOKIE_DOUGH.get());
		tag(ExtraDelightTags.SWEET_BERRY_COOKIE_DOUGH).add(ExtraDelightItems.SWEET_BERRY_COOKIE_DOUGH.get());
		tag(ExtraDelightTags.CHOCOLATE_CHIP_COOKIE_DOUGH).add(ExtraDelightItems.CHOCOLATE_CHIP_COOKIE_DOUGH.get());
		tag(ExtraDelightTags.NUT_BUTTER_COOKIE_DOUGH).add(ExtraDelightItems.NUT_BUTTER_COOKIE_DOUGH.get());
		tag(ExtraDelightTags.CHOCOLATE_COOKIE_DOUGH).add(ExtraDelightItems.CHOCOLATE_COOKIE_DOUGH.get());

		tag(ExtraDelightTags.COOKIE_DOUGH).add(ExtraDelightItems.APPLE_COOKIE_DOUGH.get(),
				ExtraDelightItems.CHOCOLATE_CHIP_COOKIE_DOUGH.get(), ExtraDelightItems.GINGERBREAD_COOKIE_DOUGH.get(),
				ExtraDelightItems.GLOW_BERRY_COOKIE_DOUGH.get(), ExtraDelightItems.HONEY_COOKIE_DOUGH.get(),
				ExtraDelightItems.PUMPKIN_COOKIE_DOUGH.get(), ExtraDelightItems.SUGAR_COOKIE_DOUGH.get(),
				ExtraDelightItems.SWEET_BERRY_COOKIE_DOUGH.get(), ExtraDelightItems.NUT_BUTTER_COOKIE_DOUGH.get(),
				ExtraDelightItems.CHOCOLATE_COOKIE_DOUGH.get());

		tag(ExtraDelightTags.FROSTING_BLACK).add(ExtraDelightItems.FROSTING_BLACK.get());
		tag(ExtraDelightTags.FROSTING_BLUE).add(ExtraDelightItems.FROSTING_BLUE.get());
		tag(ExtraDelightTags.FROSTING_BROWN).add(ExtraDelightItems.FROSTING_BROWN.get());
		tag(ExtraDelightTags.FROSTING_CYAN).add(ExtraDelightItems.FROSTING_CYAN.get());
		tag(ExtraDelightTags.FROSTING_GRAY).add(ExtraDelightItems.FROSTING_GRAY.get());
		tag(ExtraDelightTags.FROSTING_GREEN).add(ExtraDelightItems.FROSTING_GREEN.get());
		tag(ExtraDelightTags.FROSTING_LIGHT_BLUE).add(ExtraDelightItems.FROSTING_LIGHT_BLUE.get());
		tag(ExtraDelightTags.FROSTING_LIGHT_GRAY).add(ExtraDelightItems.FROSTING_LIGHT_GRAY.get());
		tag(ExtraDelightTags.FROSTING_LIME).add(ExtraDelightItems.FROSTING_LIME.get());
		tag(ExtraDelightTags.FROSTING_MAGENTA).add(ExtraDelightItems.FROSTING_MAGENTA.get());
		tag(ExtraDelightTags.FROSTING_ORANGE).add(ExtraDelightItems.FROSTING_ORANGE.get());
		tag(ExtraDelightTags.FROSTING_PINK).add(ExtraDelightItems.FROSTING_PINK.get());
		tag(ExtraDelightTags.FROSTING_PURPLE).add(ExtraDelightItems.FROSTING_PURPLE.get());
		tag(ExtraDelightTags.FROSTING_RED).add(ExtraDelightItems.FROSTING_RED.get());
		tag(ExtraDelightTags.FROSTING_WHITE).add(ExtraDelightItems.FROSTING_WHITE.get());
		tag(ExtraDelightTags.FROSTING_YELLOW).add(ExtraDelightItems.FROSTING_YELLOW.get());

		tag(ExtraDelightTags.FROSTING).add(ExtraDelightItems.FROSTING_BLACK.get())
				.add(ExtraDelightItems.FROSTING_BLUE.get()).add(ExtraDelightItems.FROSTING_BROWN.get())
				.add(ExtraDelightItems.FROSTING_CYAN.get()).add(ExtraDelightItems.FROSTING_GRAY.get())
				.add(ExtraDelightItems.FROSTING_GREEN.get()).add(ExtraDelightItems.FROSTING_LIGHT_BLUE.get())
				.add(ExtraDelightItems.FROSTING_LIGHT_GRAY.get()).add(ExtraDelightItems.FROSTING_LIME.get())
				.add(ExtraDelightItems.FROSTING_MAGENTA.get()).add(ExtraDelightItems.FROSTING_ORANGE.get())
				.add(ExtraDelightItems.FROSTING_PINK.get()).add(ExtraDelightItems.FROSTING_PURPLE.get())
				.add(ExtraDelightItems.FROSTING_RED.get()).add(ExtraDelightItems.FROSTING_WHITE.get())
				.add(ExtraDelightItems.FROSTING_YELLOW.get());


		for (int i = 0; i < AestheticBlocks.BOW_ITEMS.size(); i++)
			tag(ExtraDelightTags.RIBBON).add(AestheticBlocks.BOW_ITEMS.get(i).get());
			


		tag(ExtraDelightTags.CINNAMON_LOGS).add(ExtraDelightItems.CINNAMON_LOG.get(),
				ExtraDelightItems.CINNAMON_WOOD.get(), ExtraDelightItems.STRIPPED_CINNAMON_LOG.get(),
				ExtraDelightItems.STRIPPED_CINNAMON_WOOD.get());
		tag(ExtraDelightTags.FRUIT_LOGS).add(ExtraDelightItems.FRUIT_LOG.get(), ExtraDelightItems.FRUIT_WOOD.get(),
				ExtraDelightItems.STRIPPED_FRUIT_LOG.get(), ExtraDelightItems.STRIPPED_FRUIT_WOOD.get());

		tag(ItemTags.LOGS).add(ExtraDelightItems.CINNAMON_LOG.get(), ExtraDelightItems.STRIPPED_CINNAMON_LOG.get(),
				ExtraDelightItems.FRUIT_LOG.get(), ExtraDelightItems.STRIPPED_FRUIT_LOG.get(),
				ExtraDelightItems.CINNAMON_WOOD.get(), ExtraDelightItems.FRUIT_WOOD.get(),
				ExtraDelightItems.STRIPPED_CINNAMON_WOOD.get(), ExtraDelightItems.STRIPPED_FRUIT_WOOD.get());
		tag(ItemTags.LOGS_THAT_BURN).add(ExtraDelightItems.CINNAMON_LOG.get(),
				ExtraDelightItems.STRIPPED_CINNAMON_LOG.get(), ExtraDelightItems.FRUIT_LOG.get(),
				ExtraDelightItems.STRIPPED_FRUIT_LOG.get(), ExtraDelightItems.CINNAMON_WOOD.get(),
				ExtraDelightItems.FRUIT_WOOD.get(), ExtraDelightItems.STRIPPED_CINNAMON_WOOD.get(),
				ExtraDelightItems.STRIPPED_FRUIT_WOOD.get());
		tag(ItemTags.PLANKS).add(ExtraDelightItems.CINNAMON_PLANKS.get(), ExtraDelightItems.FRUIT_PLANKS.get());
		tag(ItemTags.FENCES).add(ExtraDelightItems.BLOOD_CHOCOLATE_FENCE.get(),
				ExtraDelightItems.DARK_CHOCOLATE_FENCE.get(), ExtraDelightItems.MILK_CHOCOLATE_FENCE.get(),
				ExtraDelightItems.WHITE_CHOCOLATE_FENCE.get());
		tag(ItemTags.WOODEN_FENCES).add(ExtraDelightItems.CINNAMON_FENCE.get(), ExtraDelightItems.FRUIT_FENCE.get());
		tag(ItemTags.FENCE_GATES).add(ExtraDelightItems.CINNAMON_FENCE_GATE.get(),
				ExtraDelightItems.BLOOD_CHOCOLATE_FENCE_GATE.get(), ExtraDelightItems.DARK_CHOCOLATE_FENCE_GATE.get(),
				ExtraDelightItems.FRUIT_FENCE_GATE.get(), ExtraDelightItems.MILK_CHOCOLATE_FENCE_GATE.get(),
				ExtraDelightItems.WHITE_CHOCOLATE_FENCE_GATE.get());
		tag(ItemTags.STAIRS).add(ExtraDelightItems.BLOOD_CHOCOLATE_STAIRS.get(),
				ExtraDelightItems.DARK_CHOCOLATE_STAIRS.get(), ExtraDelightItems.MILK_CHOCOLATE_STAIRS.get(),
				ExtraDelightItems.WHITE_CHOCOLATE_STAIRS.get());
		tag(ItemTags.WOODEN_STAIRS).add(ExtraDelightItems.CINNAMON_STAIRS.get(), ExtraDelightItems.FRUIT_STAIRS.get());
		tag(ItemTags.SLABS).add(ExtraDelightItems.BLOOD_CHOCOLATE_SLAB.get(),
				ExtraDelightItems.DARK_CHOCOLATE_SLAB.get(), ExtraDelightItems.MILK_CHOCOLATE_SLAB.get(),
				ExtraDelightItems.WHITE_CHOCOLATE_SLAB.get());
		tag(ItemTags.WOODEN_SLABS).add(ExtraDelightItems.CINNAMON_SLAB.get(), ExtraDelightItems.FRUIT_SLAB.get());
		tag(ItemTags.TRAPDOORS).add(ExtraDelightItems.BLOOD_CHOCOLATE_TRAPDOOR.get(),
				ExtraDelightItems.DARK_CHOCOLATE_TRAPDOOR.get(), ExtraDelightItems.MILK_CHOCOLATE_TRAPDOOR.get(),
				ExtraDelightItems.WHITE_CHOCOLATE_TRAPDOOR.get());
		tag(ItemTags.WOODEN_TRAPDOORS).add(ExtraDelightItems.CINNAMON_TRAPDOOR.get(),
				ExtraDelightItems.FRUIT_TRAPDOOR.get());
		tag(ItemTags.WOODEN_PRESSURE_PLATES).add(ExtraDelightItems.CINNAMON_PRESSURE_PLATE.get(),
				ExtraDelightItems.FRUIT_PRESSURE_PLATE.get());
		tag(ItemTags.WOODEN_BUTTONS).add(ExtraDelightItems.CINNAMON_BUTTON.get(), ExtraDelightItems.FRUIT_BUTTON.get());
		tag(ItemTags.DOORS).add(ExtraDelightItems.BLOOD_CHOCOLATE_DOOR.get(),
				ExtraDelightItems.DARK_CHOCOLATE_DOOR.get(), ExtraDelightItems.MILK_CHOCOLATE_DOOR.get(),
				ExtraDelightItems.WHITE_CHOCOLATE_DOOR.get());
		tag(ItemTags.WOODEN_DOORS).add(ExtraDelightItems.CINNAMON_DOOR.get(), ExtraDelightItems.FRUIT_DOOR.get());
		tag(ItemTags.SAPLINGS).add(ExtraDelightItems.CINNAMON_SAPLING.get(), ExtraDelightItems.HAZELNUT_SAPLING.get(),
				ExtraDelightItems.APPLE_SAPLING.get(),

                SummerCitrus.LEMON_SAPLING_ITEM.get(),
				SummerCitrus.LIME_SAPLING_ITEM.get(),
                SummerCitrus.ORANGE_SAPLING_ITEM.get(),
				SummerCitrus.GRAPEFRUIT_SAPLING_ITEM.get()
				

				);
                 

		tag(ExtraDelightTags.MILK_CHOCOLATE_BAR_FILLED).add(ExtraDelightItems.MILK_CHOCOLATE_FILLED_BAR.get());
		tag(ExtraDelightTags.MILK_CHOCOLATE_BLOCK).add(ExtraDelightItems.MILK_CHOCOLATE_BLOCK.get());
		tag(ExtraDelightTags.MILK_CHOCOLATE_CHIPS).add(ExtraDelightItems.MILK_CHOCOLATE_CHIPS.get());
		tag(ExtraDelightTags.MILK_CHOCOLATE_TRUFFLE).add(ExtraDelightItems.MILK_CHOCOLATE_TRUFFLE.get());

		tag(ExtraDelightTags.DARK_CHOCOLATE_BAR).add(ExtraDelightItems.DARK_CHOCOLATE_BAR.get());
		tag(ExtraDelightTags.DARK_CHOCOLATE_BAR_FILLED).add(ExtraDelightItems.DARK_CHOCOLATE_FILLED_BAR.get());
		tag(ExtraDelightTags.DARK_CHOCOLATE_BLOCK).add(ExtraDelightItems.DARK_CHOCOLATE_BLOCK.get());
		tag(ExtraDelightTags.DARK_CHOCOLATE_CHIPS).add(ExtraDelightItems.DARK_CHOCOLATE_CHIPS.get());
		tag(ExtraDelightTags.DARK_CHOCOLATE_TRUFFLE).add(ExtraDelightItems.DARK_CHOCOLATE_TRUFFLE.get());

		tag(ExtraDelightTags.BLOOD_CHOCOLATE_BAR).add(ExtraDelightItems.BLOOD_CHOCOLATE_BAR.get());
		tag(ExtraDelightTags.BLOOD_CHOCOLATE_BAR_FILLED).add(ExtraDelightItems.BLOOD_CHOCOLATE_FILLED_BAR.get());
		tag(ExtraDelightTags.BLOOD_CHOCOLATE_BLOCK).add(ExtraDelightItems.BLOOD_CHOCOLATE_BLOCK.get());
		tag(ExtraDelightTags.BLOOD_CHOCOLATE_CHIPS).add(ExtraDelightItems.BLOOD_CHOCOLATE_CHIPS.get());
		tag(ExtraDelightTags.BLOOD_CHOCOLATE_TRUFFLE).add(ExtraDelightItems.BLOOD_CHOCOLATE_TRUFFLE.get());

		tag(ExtraDelightTags.WHITE_CHOCOLATE_BAR).add(ExtraDelightItems.WHITE_CHOCOLATE_BAR.get());
		tag(ExtraDelightTags.WHITE_CHOCOLATE_BAR_FILLED).add(ExtraDelightItems.WHITE_CHOCOLATE_FILLED_BAR.get());
		tag(ExtraDelightTags.WHITE_CHOCOLATE_BLOCK).add(ExtraDelightItems.WHITE_CHOCOLATE_BLOCK.get());
		tag(ExtraDelightTags.WHITE_CHOCOLATE_CHIPS).add(ExtraDelightItems.WHITE_CHOCOLATE_CHIPS.get());
		tag(ExtraDelightTags.WHITE_CHOCOLATE_TRUFFLE).add(ExtraDelightItems.WHITE_CHOCOLATE_TRUFFLE.get());

		tag(ExtraDelightTags.CHOCOLATE_BAR).addTag(ExtraDelightTags.WHITE_CHOCOLATE_BAR)
				.addTag(ExtraDelightTags.DARK_CHOCOLATE_BAR).addTag(ExtraDelightTags.MILK_CHOCOLATE_BAR)
				.addTag(ExtraDelightTags.BLOOD_CHOCOLATE_BAR);
		tag(ExtraDelightTags.CHOCOLATE_BAR_FILLED).addTag(ExtraDelightTags.WHITE_CHOCOLATE_BAR_FILLED)
				.addTag(ExtraDelightTags.DARK_CHOCOLATE_BAR_FILLED).addTag(ExtraDelightTags.MILK_CHOCOLATE_BAR_FILLED)
				.addTag(ExtraDelightTags.BLOOD_CHOCOLATE_BAR_FILLED);
		tag(ExtraDelightTags.CHOCOLATE_BLOCK).addTag(ExtraDelightTags.WHITE_CHOCOLATE_BLOCK)
				.addTag(ExtraDelightTags.DARK_CHOCOLATE_BLOCK).addTag(ExtraDelightTags.MILK_CHOCOLATE_BLOCK)
				.addTag(ExtraDelightTags.BLOOD_CHOCOLATE_BLOCK);
		tag(ExtraDelightTags.CHOCOLATE_CHIPS).addTag(ExtraDelightTags.WHITE_CHOCOLATE_CHIPS)
				.addTag(ExtraDelightTags.DARK_CHOCOLATE_CHIPS).addTag(ExtraDelightTags.MILK_CHOCOLATE_CHIPS)
				.addTag(ExtraDelightTags.BLOOD_CHOCOLATE_CHIPS);
		tag(ExtraDelightTags.CHOCOLATE_TRUFFLE).addTag(ExtraDelightTags.WHITE_CHOCOLATE_TRUFFLE)
				.addTag(ExtraDelightTags.DARK_CHOCOLATE_TRUFFLE).addTag(ExtraDelightTags.MILK_CHOCOLATE_TRUFFLE)
				.addTag(ExtraDelightTags.BLOOD_CHOCOLATE_TRUFFLE);

		tag(ExtraDelightTags.COCOA_SOLIDS).add(ExtraDelightItems.COCOA_SOLIDS.get());
		tag(ExtraDelightTags.ROASTED_COCOA_BEANS).add(ExtraDelightItems.ROASTED_COCOA_BEANS.get());

		tag(ExtraDelightTags.ROASTED_PEANUTS).add(ExtraDelightItems.ROASTED_PEANUTS.get());
		tag(ExtraDelightTags.PEANUTS).add(ExtraDelightItems.PEANUTS.get());

		tag(ExtraDelightTags.HAZELNUTS_IN_SHELL).add(ExtraDelightItems.HAZELNUTS_IN_SHELL.get());
		tag(ExtraDelightTags.HAZELNUTS).add(ExtraDelightItems.HAZELNUTS.get());
		tag(ExtraDelightTags.ROASTED_HAZELNUTS).add(ExtraDelightItems.ROASTED_HAZELNUTS.get());

		tag(ExtraDelightTags.NUTS).addTag(ExtraDelightTags.HAZELNUTS).addTag(ExtraDelightTags.PEANUTS);
		tag(ExtraDelightTags.ROASTED_NUTS).addTag(ExtraDelightTags.ROASTED_HAZELNUTS)
				.addTag(ExtraDelightTags.ROASTED_PEANUTS);

		tag(ExtraDelightTags.PUFFED_RICE).add(ExtraDelightItems.CRISP_RICE.get());
		tag(ExtraDelightTags.CORN_FLAKES).add(ExtraDelightItems.CORN_FLAKES.get());
		tag(ExtraDelightTags.CHILI_POWDER).add(ExtraDelightItems.CHILI_POWDER.get());
		tag(ExtraDelightTags.TOFFEE).add(ExtraDelightItems.TOFFEE.get());
		tag(ExtraDelightTags.GUMMIES).add(ExtraDelightItems.GUMMIES.get());
		tag(ExtraDelightTags.PEANUT_BRITTLE).add(ExtraDelightItems.PEANUT_BRITTLE.get());
		tag(ExtraDelightTags.TRAIL_MIX).add(ExtraDelightItems.TRAIL_MIX.get());
		tag(ExtraDelightTags.FUDGE).add(ExtraDelightItems.FUDGE_SLICE.get());
		tag(ExtraDelightTags.NOUGAT).add(ExtraDelightItems.NOUGAT.get());
		tag(ExtraDelightTags.DRIED_CHILI).add(ExtraDelightItems.DRIED_CHILI.get());

		tag(ExtraDelightTags.CHOCOLATE_BAR_FILLING).addTag(ExtraDelightTags.NUTS).addTag(ExtraDelightTags.ROASTED_NUTS)
				.addTag(ExtraDelightTags.PROCESSED_FRUIT).addTag(ExtraDelightTags.CHOCOLATE_CHIPS)
				.addTag(ExtraDelightTags.PUFFED_RICE).addTag(ExtraDelightTags.CORN_FLAKES)
				.addTag(ExtraDelightTags.CHILI_POWDER).addTag(ExtraDelightTags.TOFFEE).addTag(ExtraDelightTags.GUMMIES)
				.addTag(ExtraDelightTags.PEANUT_BRITTLE).addTag(ExtraDelightTags.TRAIL_MIX)
				.addTag(ExtraDelightTags.MINT).addTag(ExtraDelightTags.CANDY).addTag(ExtraDelightTags.PROCESSED_FRUIT)
				.addTag(ExtraDelightTags.DRIED_FRUIT).addTag(ExtraDelightTags.DRIED_CHILI);
		tag(ExtraDelightTags.CHOCOLATE_TRUFFLE_FILLING).addTag(ExtraDelightTags.FROSTING).addTag(ExtraDelightTags.FUDGE)
				.addTag(ExtraDelightTags.NOUGAT).addTag(ExtraDelightTags.WHIPPED_CREAM).addTag(ExtraDelightTags.JAM);

		tag(ExtraDelightTags.MARSHMALLOW_FLUFF).add(ExtraDelightItems.MARSHMALLOW_FLUFF_BOTTLE.get());
		tag(ExtraDelightTags.NUT_BUTTER).add(ExtraDelightItems.PEANUT_BUTTER_BOTTLE.get())
				.addOptionalTag(CompatTags.NUTBUTTERS);
		tag(CompatTags.NUTBUTTERS).add(ExtraDelightItems.PEANUT_BUTTER_BOTTLE.get());
		tag(ExtraDelightTags.CHOCOLATE_SYRUP).add(ExtraDelightItems.BLOOD_CHOCOLATE_SYRUP_BOTTLE.get(),
				ExtraDelightItems.DARK_CHOCOLATE_SYRUP_BOTTLE.get(),
				ExtraDelightItems.MILK_CHOCOLATE_SYRUP_BOTTLE.get(),
				ExtraDelightItems.WHITE_CHOCOLATE_SYRUP_BOTTLE.get());
		tag(ExtraDelightTags.SUGAR_COOKIE).add(ExtraDelightItems.SUGAR_COOKIE.get(),
				ExtraDelightItems.SUGAR_COOKIE_ALEX.get(), ExtraDelightItems.SUGAR_COOKIE_CREEPER.get(),
				ExtraDelightItems.SUGAR_COOKIE_DIAMOND.get(), ExtraDelightItems.SUGAR_COOKIE_EMERALD.get(),
				ExtraDelightItems.SUGAR_COOKIE_PICKAXE.get(), ExtraDelightItems.SUGAR_COOKIE_STEVE.get(),
				ExtraDelightItems.SUGAR_COOKIE_SWORD.get());
		tag(ExtraDelightTags.GINGERBREAD_COOKIE).add(ExtraDelightItems.GINGERBREAD_COOKIE.get(),
				ExtraDelightItems.GINGERBREAD_ALEX.get(), ExtraDelightItems.GINGERBREAD_CREEPER.get(),
				ExtraDelightItems.GINGERBREAD_DIAMOND.get(), ExtraDelightItems.GINGERBREAD_EMERALD.get(),
				ExtraDelightItems.GINGERBREAD_PICKAXE.get(), ExtraDelightItems.GINGERBREAD_STEVE.get(),
				ExtraDelightItems.GINGERBREAD_SWORD.get());
		tag(ExtraDelightTags.COOKIE)
				.add(ExtraDelightItems.APPLE_COOKIE.get(), ExtraDelightItems.GLOW_BERRY_COOKIE.get(),
						ExtraDelightItems.PUMPKIN_COOKIE.get(), ExtraDelightItems.NUT_BUTTER_COOKIE.get(),
						ExtraDelightItems.CHOCOLATE_COOKIE.get())
				.addTag(ExtraDelightTags.SUGAR_COOKIE).addTag(ExtraDelightTags.GINGERBREAD_COOKIE);
		tag(ExtraDelightTags.COFFEE_CHERRIES).add(ExtraDelightItems.COFFEE_CHERRIES.get());
		tag(ExtraDelightTags.GROUND_COFFEE_BEANS).add(ExtraDelightItems.GROUND_COFFEE.get());
		tag(ExtraDelightTags.GREEN_COFFEE).add(ExtraDelightItems.GREEN_COFFEE.get());
		tag(ExtraDelightTags.PEANUTS_IN_SHELL).add(ExtraDelightItems.PEANUTS_IN_SHELL.get());

		tag(ExtraDelightTags.TEA_INGREDIENTS).addTag(ExtraDelightTags.MINT).addTag(ExtraDelightTags.PROCESSED_GINGER)
				.add(ExtraDelightItems.CORN_SILK.get()).addTag(ExtraDelightTags.MALLOWROOT);

		tag(ExtraDelightTags.BUTTERSCOTCH).add(ExtraDelightItems.BUTTERSCOTCH.get());

		tag(ExtraDelightTags.ICE_CREAM).add(ExtraDelightItems.ICE_CREAM.get())
				.add(ExtraDelightItems.APPLE_ICE_CREAM.get()).add(ExtraDelightItems.CHOCOLATE_ICE_CREAM.get())
				.add(ExtraDelightItems.COOKIE_DOUGH_ICE_CREAM.get()).add(ExtraDelightItems.GLOW_BERRY_ICE_CREAM.get())
				.add(ExtraDelightItems.HONEY_ICE_CREAM.get()).add(ExtraDelightItems.MINT_CHIP_ICE_CREAM.get())
				.add(ExtraDelightItems.PUMPKIN_ICE_CREAM.get()).add(ExtraDelightItems.SWEET_BERRY_ICE_CREAM.get());

		tag(ExtraDelightTags.ROASTED_COFFEE_BEANS).add(ExtraDelightItems.COFFEE_BEANS.get())
				.addOptionalTag(CompatTags.CROPS_COFFEEBEAN).addOptionalTag(CompatTags.CROPS_COFFEE_BEANS);
		tag(CompatTags.CROPS_COFFEEBEAN).add(ExtraDelightItems.COFFEE_BEANS.get());
		tag(CompatTags.CROPS_COFFEE_BEANS).add(ExtraDelightItems.COFFEE_BEANS.get());
		tag(ExtraDelightTags.MALLOWROOT).add(ExtraDelightItems.MALLOW_ROOT.get());
		tag(ExtraDelightTags.MALLOWROOT_POWDER).add(ExtraDelightItems.MALLOW_POWDER.get());
		tag(ExtraDelightTags.MARSHMALLOW).add(ExtraDelightItems.MARSHMALLOW.get());
		tag(ExtraDelightTags.GRAHAM_CRACKER).add(ExtraDelightItems.GRAHAM_CRACKER.get());
		tag(ExtraDelightTags.PEPPERMINT_CANDY).add(ExtraDelightItems.MINT_CANDY_BLUE.get(),
				ExtraDelightItems.MINT_CANDY_GREEN.get(), ExtraDelightItems.MINT_CANDY_RED.get());
		tag(ExtraDelightTags.COFFEE).add(ExtraDelightItems.COFFEE.get());
		tag(ExtraDelightTags.CHILI).add(ExtraDelightItems.CHILI.get()).addOptionalTag(CompatTags.CHILI_PEPPER)
				.addOptionalTag(CompatTags.CHILIPEPPER).addOptionalTag(CompatTags.CHILE_PEPPER);
		tag(CompatTags.CHILI_PEPPER).add(ExtraDelightItems.CHILI.get());
		tag(CompatTags.CHILIPEPPER).add(ExtraDelightItems.CHILI.get());
		tag(CompatTags.CHILE_PEPPER).add(ExtraDelightItems.CHILI.get());

		tag(Tags.Items.CROPS).add(ExtraDelightItems.GINGER.get(), ExtraDelightItems.MINT.get(),
				ExtraDelightItems.CHILI.get(), ExtraDelightItems.MALLOW_ROOT.get(), ExtraDelightItems.PEANUTS.get(),
				ExtraDelightItems.GARLIC.get(),
                Fermentation.CUCUMBER.get(), Fermentation.SOYBEANS.get()
        );
		tag(Tags.Items.TOOLS).addTag(ExtraDelightTags.SPOONS).addTag(ExtraDelightTags.PESTLES);
		tag(Tags.Items.SEEDS).add(ExtraDelightItems.CORN_SEEDS.get(), ExtraDelightItems.CHILI_SEEDS.get(),
				ExtraDelightItems.PEANUTS.get(),
                Fermentation.CUCUMBER_SEED.get(), Fermentation.SOYBEANS.get()
                );

		tag(ExtraDelightTags.CUSTARD).add(ExtraDelightItems.APPLE_CUSTARD.get(),
				ExtraDelightItems.CARAMEL_CUSTARD.get(), ExtraDelightItems.CHOCOLATE_CUSTARD.get(),
				ModItems.GLOW_BERRY_CUSTARD.get(), ExtraDelightItems.HONEY_CUSTARD.get(),
				ExtraDelightItems.PUMPKIN_CUSTARD.get(), ExtraDelightItems.SWEET_BERRY_CUSTARD.get());

		tag(ExtraDelightTags.COCOA_POWDER).add(ExtraDelightItems.COCOA_POWDER.get());
		tag(ExtraDelightTags.PROCESSED_CHILI).addTag(ExtraDelightTags.CHILI).add(ExtraDelightItems.SLICED_CHILI.get());

		tag(ExtraDelightTags.GARLIC).add(ExtraDelightItems.GARLIC.get());
		tag(ExtraDelightTags.PROCESSED_GARLIC).add(ExtraDelightItems.GARLIC_CLOVE.get(),
				ExtraDelightItems.GRATED_GARLIC.get());

		tag(ExtraDelightTags.AIOLI).add(ExtraDelightItems.AIOLI.get());

		tag(ExtraDelightTags.SALT)
                .add(ExtraDelightItems.SALT.get())
                .addOptionalTag(CompatTags.DUSTS_SALT);
		tag(CompatTags.DUSTS_SALT).add(ExtraDelightItems.SALT.get());


		tag(ExtraDelightTags.CUCUMBER)
                .add(Fermentation.CUCUMBER.get());
		tag(ExtraDelightTags.PROCESSED_CUCUMBER)
				.add(Fermentation.CUCUMBER.get(), Fermentation.SLICED_CUCUMBER_ITEM.get())
				.addOptionalTag(CompatTags.CUCUMBERS);
		tag(CompatTags.CUCUMBERS).add(Fermentation.CUCUMBER.get());
		tag(ExtraDelightTags.SOYBEAN).add(Fermentation.SOYBEANS.get());



		tag(ExtraDelightTags.PROCESSED_CABBAGE)
                .add(Fermentation.SHREDDED_CABBAGE_ITEM.get())
				.addOptionalTag(CommonTags.Items.CROPS_CABBAGE.location());
		tag(ExtraDelightTags.PROCESSED_BEETROOT)
                .add(Items.BEETROOT
                        ,Fermentation.SLICED_BEETROOT_ITEM.get()
                );


		tag(ExtraDelightTags.PICKLED_CUCUMBER).add(Fermentation.GHERKIN_ITEM.get());
		tag(ExtraDelightTags.PICKLED_CUCUMBER_SLICED).add(Fermentation.SLICED_GHERKIN_ITEM.get());
		tag(ExtraDelightTags.PROCESSED_PICKLED_CUCUMBER).addTag(ExtraDelightTags.PICKLED_CUCUMBER)
				.addTag(ExtraDelightTags.PICKLED_CUCUMBER_SLICED);
		tag(ExtraDelightTags.PICKLED_BEETROOT).add(Fermentation.PICKLED_BEET_ITEM.get());
		tag(ExtraDelightTags.PICKLED_ONION).add(Fermentation.PICKLED_ONION_ITEM.get());
		tag(ExtraDelightTags.PICKLED_CARROT).add(Fermentation.PICKLED_CARROT_ITEM.get());
		tag(ExtraDelightTags.PICKLED_FISH).add(Fermentation.PICKLED_FISH_ITEM.get());
		tag(ExtraDelightTags.PICKLED_SAUSAGE).add(Fermentation.PICKLED_SAUSAGE_ITEM.get());




		tag(ExtraDelightTags.PICKLED).addTag(ExtraDelightTags.PICKLED_VEGETABLES).addTag(ExtraDelightTags.PICKLED_MEAT)
				.addTag(ExtraDelightTags.PICKLED_EGGS);
		tag(ExtraDelightTags.PICKLED_VEGETABLES).addTag(ExtraDelightTags.PICKLED_CUCUMBER)
				.addTag(ExtraDelightTags.PICKLED_BEETROOT).addTag(ExtraDelightTags.PICKLED_ONION)
				.addTag(ExtraDelightTags.PICKLED_CARROT);
		tag(ExtraDelightTags.PICKLED_MEAT).addTag(ExtraDelightTags.PICKLED_FISH)
				.addTag(ExtraDelightTags.PICKLED_SAUSAGE);


        tag(ExtraDelightTags.PICKLED_EGGS).add(Fermentation.PICKLED_EGG_ITEM.get());
		tag(ExtraDelightTags.PICKLE_JUICE).add(Fermentation.PICKLE_JUICE.get());
		tag(ExtraDelightTags.SOY_SAUCE).add(Fermentation.SOY_SAUCE_ITEM.get()).addOptionalTag(CompatTags.SOYSAUCE)
				.addOptionalTag(CompatTags.SOY_SAUCES);
		tag(CompatTags.SOYSAUCE).add(Fermentation.SOY_SAUCE_ITEM.get());
		tag(CompatTags.SOY_SAUCES).add(Fermentation.SOY_SAUCE_ITEM.get());
		tag(ExtraDelightTags.SAUERKRAUT).add(Fermentation.SAUERKRAUT_ITEM.get());
		tag(ExtraDelightTags.KIMCHI).add(Fermentation.KIMCHI_ITEM.get());
		tag(ExtraDelightTags.HOT_SAUCE).add(Fermentation.HOT_SAUCE_ITEM.get()).addOptionalTag(CompatTags.HOTSAUCE);
		tag(CompatTags.HOTSAUCE).add(Fermentation.HOT_SAUCE_ITEM.get());
		tag(ExtraDelightTags.MISO_PASTE).add(Fermentation.MISO_PASTE_ITEM.get());
		tag(ExtraDelightTags.NATTO).add(Fermentation.NATTO_ITEM.get());
		tag(ExtraDelightTags.FISH_SAUCE).add(Fermentation.FISH_SAUCE_ITEM.get());
		tag(ExtraDelightTags.SALAMI).add(Fermentation.SALAMI_ITEM.get());
		tag(ExtraDelightTags.SOAKED_SOYBEANS).add(Fermentation.SOAKED_SOYBEANS_ITEM.get());
		tag(ExtraDelightTags.MASHED_SOYBEANS).add(Fermentation.MASHED_SOYBEANS_ITEM.get());
		tag(ExtraDelightTags.COOKED_SOYBEANS).add(Fermentation.COOKED_SOYBEANS_ITEM.get());
		tag(ExtraDelightTags.SOY_MILK).add(Fermentation.SOY_MILK.get()).addOptionalTag(CompatTags.SOYMILK)
				.addOptionalTag(CompatTags.SOY_MILKS);
		tag(CompatTags.SOYMILK).add(Fermentation.SOY_MILK.get());
		tag(CompatTags.SOY_MILKS).add(Fermentation.SOY_MILK.get());
		tag(ExtraDelightTags.YEAST_SPREAD).add(Fermentation.YEAST_SPREAD.get());
		tag(ExtraDelightTags.SALAMI_MIX).add(Fermentation.SALAMI_MIX.get());
		tag(ExtraDelightTags.COOKED_WHEAT_SEEDS).add(Fermentation.COOKED_WHEAT_SEEDS.get());

		tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(ExtraDelightItems.CORN_SEEDS.get(),
				ExtraDelightItems.GINGER_CUTTING.get(), ExtraDelightItems.CHILI_SEEDS.get(),
				ExtraDelightItems.PEANUTS.get(), ExtraDelightItems.MALLOW_ROOT.get(),
				ExtraDelightItems.GARLIC_CLOVE.get(),
               Fermentation.CUCUMBER_SEED.get(), Fermentation.SOYBEANS.get()
        );

		tag(ExtraDelightTags.DOUGH)
                .addOptionalTag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "foods/dough")))
                .addOptionalTag(CompatTags.DOUGHS);
		tag(ExtraDelightTags.BROTH).add(ModItems.BONE_BROTH.get()).addOptionalTag(CompatTags.STOCK);
		tag(ExtraDelightTags.RAW_PASTA)
                .addOptionalTag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "foods/pasta")));

        tag(ExtraDelightTags.LEMON);
        tag(ExtraDelightTags.LIME);
        tag(ExtraDelightTags.ORANGE);
        tag(ExtraDelightTags.GRAPEFRUIT);

        tag(ExtraDelightTags.PROCESSED_LEMON);
        tag(ExtraDelightTags.PROCESSED_LIME);
        tag(ExtraDelightTags.PROCESSED_ORANGE);
        tag(ExtraDelightTags.PROCESSED_GRAPEFRUIT);


		tag(ExtraDelightTags.LEMON).add(SummerCitrus.LEMON.get());
		tag(ExtraDelightTags.LIME).add(SummerCitrus.LIME.get());
		tag(ExtraDelightTags.ORANGE).add(SummerCitrus.ORANGE.get());
		tag(ExtraDelightTags.GRAPEFRUIT).add(SummerCitrus.GRAPEFRUIT.get());
        tag(ExtraDelightTags.PROCESSED_CITRUS).addTag(ExtraDelightTags.PROCESSED_LEMON)
                .addTag(ExtraDelightTags.PROCESSED_LIME).addTag(ExtraDelightTags.PROCESSED_ORANGE)
                .addTag(ExtraDelightTags.PROCESSED_GRAPEFRUIT);
        tag(ExtraDelightTags.PROCESSED_MELON).add(Items.MELON_SLICE
                ,SummerCitrus.MELON_CHUNKS.get()
        );
        tag(ExtraDelightTags.IS_MARMALADE_INGREDIENT).addTag(ExtraDelightTags.PROCESSED_CITRUS);

		tag(ExtraDelightTags.PROCESSED_LEMON).add(SummerCitrus.LEMON.get(), SummerCitrus.SLICED_LEMON.get());
		tag(ExtraDelightTags.PROCESSED_LIME).add(SummerCitrus.LIME.get(), SummerCitrus.SLICED_LIME.get());
		tag(ExtraDelightTags.PROCESSED_ORANGE).add(SummerCitrus.ORANGE.get(), SummerCitrus.SLICED_ORANGE.get());
		tag(ExtraDelightTags.PROCESSED_GRAPEFRUIT).add(SummerCitrus.GRAPEFRUIT.get(),
				SummerCitrus.SLICED_GRAPEFRUIT.get());
        tag(ExtraDelightTags.PROCESSED_VEG).addTag(ExtraDelightTags.PROCESSED_CARROT)
                .addTag(ExtraDelightTags.PROCESSED_TOMATO).addTag(ExtraDelightTags.PROCESSED_POTATO)
                .addTag(ExtraDelightTags.PROCESSED_ONION).addTag(ExtraDelightTags.PROCESSED_GARLIC)
                .addTag(ExtraDelightTags.PROCESSED_CABBAGE).addTag(ExtraDelightTags.PROCESSED_BEETROOT)
                .addTag(ExtraDelightTags.PROCESSED_CUCUMBER);

        tag(ExtraDelightTags.PROCESSED_FRUIT).addTag(ExtraDelightTags.PROCESSED_APPLE)
                .add(Items.SWEET_BERRIES, Items.GLOW_BERRIES).addTag(ExtraDelightTags.PROCESSED_MELON)
                .addTag(ExtraDelightTags.PROCESSED_CITRUS);
        tag(ExtraDelightTags.PROCESSED_PRODUCE).addTag(ExtraDelightTags.PROCESSED_FRUIT)
                .addTag(ExtraDelightTags.PROCESSED_VEG);

    }
}
