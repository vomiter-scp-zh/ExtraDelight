package com.vomiter.extradelight.data.tags;

import java.util.concurrent.CompletableFuture;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.Fermentation;
import com.vomiter.extradelight.registry.SummerCitrus;
import com.vomiter.extradelight.util.EDItemGenerator;
import net.minecraft.world.item.DyeColor;
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
public class EDItemTags extends ItemTagsProvider {

	public EDItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
			CompletableFuture<TagLookup<Block>> blockTagProvider,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, blockTagProvider, ExtraDelight.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(ExtraDelightTags.TRAYS).add(ExtraDelightItems.TRAY.get(), ExtraDelightItems.BAKING_STONE.get(),
				ExtraDelightItems.PIE_DISH.get(), ExtraDelightItems.LOAF_PAN.get(), ExtraDelightItems.SHEET.get(),
				ExtraDelightItems.MUFFIN_TIN.get());

		tag(ExtraDelightTags.MORTAR).add(ExtraDelightItems.MORTAR_STONE.get());

		tag(ExtraDelightTags.PESTLES).add(ExtraDelightItems.PESTLE_AMETHYST.get(),
				ExtraDelightItems.PESTLE_ANDESITE.get(), ExtraDelightItems.PESTLE_BASALT.get(),
				ExtraDelightItems.PESTLE_BLACKSTONE.get(), ExtraDelightItems.PESTLE_DEEPSLATE.get(),
				ExtraDelightItems.PESTLE_DIORITE.get(), ExtraDelightItems.PESTLE_ENDSTONE.get(),
				ExtraDelightItems.PESTLE_GILDED_BLACKSTONE.get(), ExtraDelightItems.PESTLE_GRANITE.get(),
				ExtraDelightItems.PESTLE_STONE.get());

		tag(ExtraDelightTags.SPOONS).add(ExtraDelightItems.WOODEN_SPOON.get(), ExtraDelightItems.STONE_SPOON.get(),
				ExtraDelightItems.IRON_SPOON.get(), ExtraDelightItems.GOLD_SPOON.get(),
				ExtraDelightItems.DIAMOND_SPOON.get(), ExtraDelightItems.NETHERITE_SPOON.get());

		tag(ExtraDelightTags.MAKES_STOCK).add(Items.BONE)
				.add(Items.BEEF, Items.COOKED_BEEF, Items.PORKCHOP, Items.COOKED_PORKCHOP, Items.CHICKEN,
						Items.COOKED_CHICKEN, Items.COD, Items.COOKED_COD, Items.SALMON, Items.COOKED_SALMON)
				.addOptionalTag(ResourceLocation.fromNamespaceAndPath("forge", "vegetables"));

		tag(ExtraDelightTags.FRUIT_APPLE).add(Items.APPLE);
		tag(ExtraDelightTags.FRUIT_GLOW_BERRY).add(Items.GLOW_BERRIES);
		tag(ExtraDelightTags.FRUIT_SWEET_BERRY).add(Items.SWEET_BERRIES);
		tag(ExtraDelightTags.FRUIT_MELON).add(Items.MELON_SLICE);

		tag(ExtraDelightTags.FRUIT).addTag(ExtraDelightTags.FRUIT_APPLE).addTag(ExtraDelightTags.FRUIT_GLOW_BERRY)
				.addTag(ExtraDelightTags.FRUIT_SWEET_BERRY).addTag(ExtraDelightTags.FRUIT_MELON).add(Items.CHORUS_FRUIT)
                .add(SummerCitrus.LEMON.get(), SummerCitrus.LIME.get(), SummerCitrus.ORANGE.get(), SummerCitrus.GRAPEFRUIT.get());

		tag(ExtraDelightTags.COOKING_OIL).add(ExtraDelightItems.COOKING_OIL.get())
                .addOptionalTag(CompatTags.COOKINGOIL)
				.addOptionalTag(CompatTags.OLIVE_OILS);
		tag(CompatTags.COOKINGOIL).add(ExtraDelightItems.COOKING_OIL.get());
		tag(ExtraDelightTags.FRYING_OIL).add(ExtraDelightItems.COOKING_OIL.get()).addOptionalTag(CompatTags.FRYINGOIL);
		tag(CompatTags.FRYINGOIL).add(ExtraDelightItems.COOKING_OIL.get());
		tag(ExtraDelightTags.VINEGAR).add(ExtraDelightItems.VINEGAR.get());

		tag(ExtraDelightTags.TOAST).add(ExtraDelightItems.TOAST.get());

		tag(Tags.Items.SLIMEBALLS).add(ExtraDelightItems.SEAWEED_PASTE.get());

		tag(ExtraDelightTags.FLOUR).add(ExtraDelightItems.FLOUR.get()).addOptionalTag(CompatTags.FLOURS_WHEAT);
		tag(CompatTags.FLOURS_WHEAT).add(ExtraDelightItems.FLOUR.get());

		tag(ExtraDelightTags.MEAT_COOKED).add(Items.COOKED_BEEF).add(Items.COOKED_CHICKEN).add(Items.COOKED_MUTTON)
				.add(Items.COOKED_PORKCHOP).add(Items.COOKED_RABBIT);
		tag(ExtraDelightTags.MEAT_RAW).add(Items.BEEF).add(Items.CHICKEN).add(Items.MUTTON).add(Items.PORKCHOP)
				.add(Items.RABBIT).addOptionalTag(CompatTags.RAWMEATS);
		tag(CompatTags.RAWMEATS).addTag(ExtraDelightTags.MEAT_RAW);
		tag(ExtraDelightTags.MEAT).addTag(ExtraDelightTags.MEAT_COOKED).addTag(ExtraDelightTags.MEAT_RAW);
		tag(ExtraDelightTags.BEEF_COOKED).add(Items.COOKED_BEEF);
		tag(ExtraDelightTags.BEEF_RAW).add(Items.BEEF);
		tag(ExtraDelightTags.BEEF).addTag(ExtraDelightTags.BEEF_COOKED).addTag(ExtraDelightTags.BEEF_RAW);

		tag(ExtraDelightTags.SWEETENER).add(Items.HONEY_BOTTLE).add(Items.SUGAR);
		tag(ExtraDelightTags.MAYO).add(ExtraDelightItems.MAYO.get()).addOptionalTag(CompatTags.MAYONAISE);
		tag(CompatTags.MAYONAISE).add(ExtraDelightItems.MAYO.get());
		tag(ExtraDelightTags.KETCHUP).add(ExtraDelightItems.KETCHUP.get());
		tag(ExtraDelightTags.BBQ_SAUCE).add(ExtraDelightItems.BBQ_SAUCE.get()).addOptionalTag(CompatTags.BBQSAUCE);
		tag(CompatTags.BBQSAUCE).add(ExtraDelightItems.BBQ_SAUCE.get());

		tag(ExtraDelightTags.BREAD_SLICE).add(ExtraDelightItems.BREAD_SLICE.get());
		tag(ExtraDelightTags.TOAST).add(ExtraDelightItems.TOAST.get());
		tag(ExtraDelightTags.TOAST_OR_BREAD_SLICE).addTag(ExtraDelightTags.BREAD_SLICE).addTag(ExtraDelightTags.TOAST)
				.addOptionalTag(CompatTags.BREAD_SLICES).addOptionalTag(CompatTags.BREAD_SLICE);
		tag(CompatTags.BREAD_SLICES).add(ExtraDelightItems.BREAD_SLICE.get(), ExtraDelightItems.TOAST.get());
		tag(CompatTags.BREAD_SLICE).add(ExtraDelightItems.BREAD_SLICE.get(), ExtraDelightItems.TOAST.get());

		tag(ExtraDelightTags.CHEESE).add(ExtraDelightItems.CHEESE.get()).addOptionalTag(CompatTags.FOODS_CHEESE)
				.addOptionalTag(CompatTags.CHEESES).addOptionalTag(CompatTags.CHEESE_WEDGE)
				.addOptionalTag(CompatTags.CHEESE_LAYER);
		tag(CompatTags.FOODS_CHEESE).add(ExtraDelightItems.CHEESE.get());
		tag(CompatTags.CHEESES).add(ExtraDelightItems.CHEESE.get());
		tag(CompatTags.CHEESE_WEDGE).add(ExtraDelightItems.CHEESE.get());
		tag(CompatTags.CHEESE_LAYER).add(ExtraDelightItems.CHEESE.get());
		tag(ExtraDelightTags.GROUND_BEEF_RAW).add(ModItems.MINCED_BEEF.get()).addOptionalTag(CompatTags.GROUNDBEEF);
		tag(ExtraDelightTags.GROUND_BEEF_COOKED).add(ModItems.BEEF_PATTY.get());
		tag(ExtraDelightTags.GROUND_PORK_RAW).addOptionalTag(CompatTags.GROUNDPORK);
		tag(ExtraDelightTags.GROUND_MUTTON_RAW).addOptionalTag(CompatTags.GROUNDMUTTON);
		tag(ExtraDelightTags.GROUND_RABBIT_RAW).addOptionalTag(CompatTags.GROUNDRABBIT);
		tag(ExtraDelightTags.GROUND_MEAT_RAW).addTag(ExtraDelightTags.GROUND_BEEF_RAW)
				.addTag(ExtraDelightTags.GROUND_PORK_RAW).addTag(ExtraDelightTags.GROUND_MUTTON_RAW)
				.addTag(ExtraDelightTags.GROUND_RABBIT_RAW);
		tag(ExtraDelightTags.GROUND_MEAT_COOKED).addTag(ExtraDelightTags.GROUND_BEEF_COOKED);
		tag(ExtraDelightTags.GELATIN).add(ExtraDelightItems.AGAR_AGAR.get());
		tag(ExtraDelightTags.BUTTER).add(ExtraDelightItems.BUTTER.get());
		tag(ExtraDelightTags.GRAVY).add(ExtraDelightItems.GRAVY.get());
		tag(ExtraDelightTags.CONDIMENTS).addTag(ExtraDelightTags.KETCHUP).addTag(ExtraDelightTags.BBQ_SAUCE)
				.addTag(ExtraDelightTags.MAYO).addTag(ExtraDelightTags.HOT_SAUCE).addTag(ExtraDelightTags.SOY_SAUCE)
				.addTag(ExtraDelightTags.AIOLI);

		tag(ExtraDelightTags.STARCH).add(ModItems.RICE.get(), ExtraDelightItems.POTATO_STICKS.get(),
				ExtraDelightItems.GRATED_POTATO.get(), ExtraDelightItems.SLICED_POTATO.get());

		tag(ExtraDelightTags.SOUP).add(ExtraDelightItems.CARROT_SOUP.get(), ExtraDelightItems.CACTUS_SOUP.get(),
				ExtraDelightItems.FISH_SOUP.get(), ExtraDelightItems.OXTAIL_SOUP.get(),
				ExtraDelightItems.POTATO_SOUP.get(), ExtraDelightItems.TOMATO_SOUP.get(), ModItems.CHICKEN_SOUP.get(),
				ModItems.NOODLE_SOUP.get(), ModItems.PUMPKIN_SOUP.get(), ModItems.VEGETABLE_SOUP.get());

		tag(ExtraDelightTags.PROCESSED_ONION).add(ExtraDelightItems.SLICED_ONION.get())
				.addOptionalTag(CommonTags.Items.CROPS_ONION.location()).addTag(CompatTags.ONION_LAYER);
		tag(CompatTags.ONION_LAYER).add(ExtraDelightItems.SLICED_ONION.get());

		tag(ExtraDelightTags.SLICED_ONION).add(ExtraDelightItems.SLICED_ONION.get());

		tag(ExtraDelightTags.PROCESSED_POTATO).addTag(Tags.Items.CROPS_POTATO).add(
				ExtraDelightItems.POTATO_STICKS.get(), ExtraDelightItems.SLICED_POTATO.get(),
				ExtraDelightItems.GRATED_POTATO.get());
		tag(ExtraDelightTags.POTATO_STICKS).add(ExtraDelightItems.POTATO_STICKS.get());
		tag(ExtraDelightTags.SLICED_POTATO).add(ExtraDelightItems.SLICED_POTATO.get());
		tag(ExtraDelightTags.GRATED_POTATO).add(ExtraDelightItems.GRATED_POTATO.get());

		tag(ExtraDelightTags.PROCESSED_CARROT).addTag(Tags.Items.CROPS_CARROT)
				.add(ExtraDelightItems.GRATED_CARROT.get());
		tag(ExtraDelightTags.GRATED_CARROT).add(ExtraDelightItems.GRATED_CARROT.get());

		tag(ExtraDelightTags.PROCESSED_TOMATO).add(ExtraDelightItems.SLICED_TOMATO.get())
				.addOptionalTag(CommonTags.Items.CROPS_TOMATO.location()).addTag(CompatTags.TOMATO_LAYER);
		tag(ExtraDelightTags.SLICED_TOMATO).add(ExtraDelightItems.SLICED_TOMATO.get());
		tag(CompatTags.TOMATO_LAYER).add(ExtraDelightItems.SLICED_TOMATO.get());

		tag(ExtraDelightTags.PROCESSED_APPLE).addTag(ExtraDelightTags.FRUIT_APPLE)
				.add(ExtraDelightItems.SLICED_APPLE.get());
		tag(ExtraDelightTags.SLICED_APPLE).add(ExtraDelightItems.SLICED_APPLE.get());

		tag(ExtraDelightTags.CACTUS).add(ExtraDelightItems.CACTUS.get());

		tag(ExtraDelightTags.RICEBALL_FILLING).add(ExtraDelightItems.EGG_SALAD.get(),
				ExtraDelightItems.SCRAMBLED_EGGS.get(), ExtraDelightItems.FISH_SALAD.get(),
				ExtraDelightItems.BOILED_EGG.get(), ExtraDelightItems.PICKLED_GINGER.get())
				.addTag(ExtraDelightTags.CHEESE);

		tag(ExtraDelightTags.JELLY).add(ExtraDelightItems.JELLY_BLACK.get(), ExtraDelightItems.JELLY_BLUE.get(),
				ExtraDelightItems.JELLY_BROWN.get(), ExtraDelightItems.JELLY_CYAN.get(),
				ExtraDelightItems.JELLY_GREEN.get(), ExtraDelightItems.JELLY_GREY.get(),
				ExtraDelightItems.JELLY_LIGHT_BLUE.get(), ExtraDelightItems.JELLY_LIGHT_GREY.get(),
				ExtraDelightItems.JELLY_LIME.get(), ExtraDelightItems.JELLY_MAGENTA.get(),
				ExtraDelightItems.JELLY_ORANGE.get(), ExtraDelightItems.JELLY_PINK.get(),
				ExtraDelightItems.JELLY_PURPLE.get(), ExtraDelightItems.JELLY_RED.get(),
				ExtraDelightItems.JELLY_WHITE.get(), ExtraDelightItems.JELLY_YELLOW.get());

		tag(ExtraDelightTags.CORN_CONFISCATE).add(ExtraDelightItems.CORN_COB.get(), ExtraDelightItems.CORN_HUSK.get(),
				ExtraDelightItems.CORN_ON_COB.get(), ExtraDelightItems.CORN_SEEDS.get(),
				ExtraDelightItems.CORN_SILK.get(),  ExtraDelightItems.UNSHUCKED_CORN.get(),
				ExtraDelightItems.CORN_CHOWDER.get(), ExtraDelightItems.CORN_FRITTERS.get(),
				ExtraDelightItems.CORN_MEAL.get(), ExtraDelightItems.CORN_PUDDING.get(),
				ExtraDelightItems.CORN_PUDDING_FEAST.get(), ExtraDelightItems.TEA.get(),
				ExtraDelightItems.CORNBREAD.get(), ExtraDelightItems.CORNBREAD_FEAST.get(),
				ExtraDelightItems.CARAMEL_POPCORN.get(), ExtraDelightItems.COOKED_CORN.get(),
				ExtraDelightItems.CREAM_CORN.get(), ExtraDelightItems.DRIED_CORN_HUSK.get(),
				ExtraDelightItems.GRILLED_CORN_ON_COB.get(), ExtraDelightItems.POPCORN.get(),
				AestheticBlocks.CORN_HUSK_DOLL_ITEM.get()
        );

		tag(ExtraDelightTags.CANDY).add(ExtraDelightItems.CANDY_BLACK.get(), ExtraDelightItems.CANDY_BLUE.get(),
				ExtraDelightItems.CANDY_BROWN.get(), ExtraDelightItems.CANDY_CYAN.get(),
				ExtraDelightItems.CANDY_GREEN.get(), ExtraDelightItems.CANDY_GRAY.get(),
				ExtraDelightItems.CANDY_LIGHT_BLUE.get(), ExtraDelightItems.CANDY_LIGHT_GRAY.get(),
				ExtraDelightItems.CANDY_LIME.get(), ExtraDelightItems.CANDY_MAGENTA.get(),
				ExtraDelightItems.CANDY_ORANGE.get(), ExtraDelightItems.CANDY_PINK.get(),
				ExtraDelightItems.CANDY_PURPLE.get(), ExtraDelightItems.CANDY_RED.get(),
				ExtraDelightItems.CANDY_WHITE.get(), ExtraDelightItems.CANDY_YELLOW.get(),
				ExtraDelightItems.CARAMEL_CANDY.get(), ExtraDelightItems.MINT_CANDY_BLUE.get(),
				ExtraDelightItems.MINT_CANDY_GREEN.get(), ExtraDelightItems.MINT_CANDY_RED.get(),
				ExtraDelightItems.CANDY_CANE_BLUE.get(), ExtraDelightItems.CANDY_CANE_RED.get(),
				ExtraDelightItems.CANDY_CANE_GREEN.get(), ExtraDelightItems.CANDIED_GINGER.get(),
				SummerCitrus.CANDIED_CITRUS_ZEST.get()
        );

		tag(ExtraDelightTags.CANDY_BOWL_VALID).addTag(ExtraDelightTags.CANDY);
		tag(ExtraDelightTags.CHOCOLATE_BOX_VALID).addTag(ExtraDelightTags.CANDY).addTag(ExtraDelightTags.CHOCOLATE_BAR)
				.addTag(ExtraDelightTags.CHOCOLATE_BAR_FILLED).addTag(ExtraDelightTags.CHOCOLATE_TRUFFLE);

		tag(ExtraDelightTags.OFFSET_SPATULAS).add(ExtraDelightItems.OFFSET_SPATULA_DIAMOND.get(),
				ExtraDelightItems.OFFSET_SPATULA_GOLD.get(), ExtraDelightItems.OFFSET_SPATULA_IRON.get(),
				ExtraDelightItems.OFFSET_SPATULA_NETHERITE.get(), ExtraDelightItems.OFFSET_SPATULA_WOOD.get());

		tag(ExtraDelightTags.GINGER).add(ExtraDelightItems.GINGER.get());
		tag(ExtraDelightTags.GRATED_GINGER).add(ExtraDelightItems.GRATED_GINGER.get());
		tag(ExtraDelightTags.SLICED_GINGER).add(ExtraDelightItems.SLICED_GINGER.get());
		tag(ExtraDelightTags.PROCESSED_GINGER).addTag(ExtraDelightTags.GRATED_GINGER)
				.addTag(ExtraDelightTags.SLICED_GINGER);

	}
}
