package com.vomiter.extradelight.registry;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.blocks.RecipeFeastBlock;
import com.vomiter.extradelight.common.blocks.crops.CucumberCrop;
import com.vomiter.extradelight.common.blocks.crops.SoybeanCrop;
import com.vomiter.extradelight.common.blocks.fermentation.RipeSalamiBlock;
import com.vomiter.extradelight.common.blocks.fermentation.UnripeSalamiBlock;
import com.vomiter.extradelight.common.complex.jardisplay.JarDisplayBlock;
import com.vomiter.extradelight.common.complex.jardisplay.JarSingularBlock;
import com.vomiter.extradelight.common.complex.jardisplay.JarSingularItem;
import com.vomiter.extradelight.common.fluids.VinegarFluidBlock;
import com.vomiter.extradelight.common.items.EDFoods;
import com.vomiter.extradelight.common.items.EffectDrinkItem;
import com.vomiter.extradelight.common.items.ToolTipConsumableItem;
import com.vomiter.extradelight.common.items.corn.ShuckableCorn;
import com.vomiter.extradelight.data.loot.MiscLootTables;
import com.vomiter.extradelight.util.EDItemGenerator;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;
import vectorwing.farmersdelight.common.item.MilkBottleItem;
import vectorwing.farmersdelight.common.registry.ModBlocks;

import java.util.List;

import static com.vomiter.extradelight.registry.ExtraDelightItems.drinkItem;
import static com.vomiter.extradelight.registry.ExtraDelightItems.stack1Item;
import static vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem;
import static vectorwing.farmersdelight.common.registry.ModItems.foodItem;

public class Fermentation {
	public static final RegistryObject<CucumberCrop> CUCUMBER_CROP = ExtraDelightBlocks.BLOCKS.register("cucumber_crop",
			() -> new CucumberCrop(Block.Properties.copy(Blocks.WHEAT)));
	public static final RegistryObject<SoybeanCrop> SOYBEAN_CROP = ExtraDelightBlocks.BLOCKS.register("soybean_crop",
			() -> new SoybeanCrop(Block.Properties.copy(Blocks.WHEAT)));

	public static final RegistryObject<Block> WILD_CUCUMBER = ExtraDelightBlocks.BLOCKS.register("wild_cucumber",
			() -> new WildCropBlock(MobEffects.MOVEMENT_SPEED, 6, Block.Properties.copy(Blocks.TALL_GRASS)));
	public static final RegistryObject<Block> WILD_SOYBEAN = ExtraDelightBlocks.BLOCKS.register("wild_soybean",
			() -> new WildCropBlock(MobEffects.SATURATION, 6, Block.Properties.copy(Blocks.TALL_GRASS)));

	public static final RegistryObject<Item> WILD_CUCUMBER_ITEM = ExtraDelightItems.ITEMS.register("wild_cucumber_item",
			() -> new BlockItem(WILD_CUCUMBER.get(), new Item.Properties()));
	public static final RegistryObject<Item> WILD_SOYBEAN_ITEM = ExtraDelightItems.ITEMS.register("wild_soybean_item",
			() -> new BlockItem(WILD_SOYBEAN.get(), new Item.Properties()));

	public static final RegistryObject<Item> CUCUMBER = EDItemGenerator
			.register("cucumber", () -> new Item(new Item.Properties().food(EDFoods.CUCUMBER))).advancementIngredients()
			.finish();
	public static final RegistryObject<Item> SOYBEAN_POD = EDItemGenerator
			.register("soybean_pod", () -> new ShuckableCorn(MiscLootTables.SOYBEANS, new Item.Properties()))
			.advancementIngredients().finish();

	public static final RegistryObject<Item> CUCUMBER_SEED = ExtraDelightItems.ITEMS.register("cucumber_seed",
			() -> new ItemNameBlockItem(CUCUMBER_CROP.get(), new Item.Properties()));
	public static final RegistryObject<Item> SOYBEANS = ExtraDelightItems.ITEMS.register("soybeans",
			() -> new ItemNameBlockItem(SOYBEAN_CROP.get(), new Item.Properties()));

	public static final RegistryObject<Block> CUCUMBER_CRATE = ExtraDelightBlocks.BLOCKS.register("cucumber_crate",
			() -> new Block(Block.Properties.copy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.PLANT)));
	public static final RegistryObject<Item> CUCUMBER_CRATE_ITEM = ExtraDelightItems.ITEMS.register("cucumber_crate_item",
			() -> new BlockItem(CUCUMBER_CRATE.get(), new Item.Properties()));
	public static final RegistryObject<Block> SOYBEAN_SACK = ExtraDelightBlocks.BLOCKS.register("soybean_sack",
			() -> new Block(
					Block.Properties.copy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.TERRACOTTA_WHITE)));
	public static final RegistryObject<Item> SOYBEAN_SACK_ITEM = ExtraDelightItems.ITEMS.register("soybean_sack_item",
			() -> new BlockItem(SOYBEAN_SACK.get(), new Item.Properties()));

	public static final RegistryObject<JarSingularBlock> GHERKINS_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("gherkins_block", () -> new JarSingularBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)
					.strength(0.8F).sound(SoundType.GLASS).mapColor(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Item> GHERKINS_BLOCK_ITEM = EDItemGenerator
			.register("gherkins_block_item",
					() -> new JarSingularItem(GHERKINS_BLOCK.get(),
							stack1Item()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> GHERKIN_ITEM = EDItemGenerator
			.register("gherkin_item",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.GHERKINS), true))
			.advancementIngredients().servingToolTip().finish();

	public static final RegistryObject<JarSingularBlock> PICKLED_BEETS_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("pickled_beets_block", () -> new JarSingularBlock(BlockBehaviour.Properties
					.copy(Blocks.GLASS).strength(0.8F).sound(SoundType.GLASS).mapColor(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Item> PICKLED_BEETS_BLOCK_ITEM = EDItemGenerator
			.register("pickled_beets_block_item",
					() -> new JarSingularItem(PICKLED_BEETS_BLOCK.get(),
							stack1Item()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> PICKLED_BEET_ITEM = EDItemGenerator
			.register("pickled_beet_item",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.PICKLED_BEET), true))
			.advancementIngredients().servingToolTip().finish();

	public static final RegistryObject<JarSingularBlock> PICKLED_ONIONS_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("pickled_onions_block", () -> new JarSingularBlock(BlockBehaviour.Properties
					.copy(Blocks.GLASS).strength(0.8F).sound(SoundType.GLASS).mapColor(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Item> PICKLED_ONIONS_BLOCK_ITEM = EDItemGenerator
			.register("pickled_onions_block_item",
					() -> new JarSingularItem(PICKLED_ONIONS_BLOCK.get(),
							stack1Item()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> PICKLED_ONION_ITEM = EDItemGenerator
			.register("pickled_onion_item",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.PICKLED_ONION), true))
			.advancementIngredients().servingToolTip().finish();

	public static final RegistryObject<JarSingularBlock> PICKLED_CARROTS_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("pickled_carrots_block", () -> new JarSingularBlock(BlockBehaviour.Properties
					.copy(Blocks.GLASS).strength(0.8F).sound(SoundType.GLASS).mapColor(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Item> PICKLED_CARROTS_BLOCK_ITEM = EDItemGenerator
			.register("pickled_carrots_block_item",
					() -> new JarSingularItem(PICKLED_CARROTS_BLOCK.get(),
							stack1Item()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> PICKLED_CARROT_ITEM = EDItemGenerator
			.register("pickled_carrot_item",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.PICKLED_CARROT), true))
			.advancementSnack().servingToolTip().finish();

	public static final RegistryObject<JarSingularBlock> PICKLED_EGGS_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("pickled_eggs_block", () -> new JarSingularBlock(BlockBehaviour.Properties
					.copy(Blocks.GLASS).strength(0.8F).sound(SoundType.GLASS).mapColor(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Item> PICKLED_EGGS_BLOCK_ITEM = EDItemGenerator
			.register("pickled_eggs_block_item",
					() -> new JarSingularItem(PICKLED_EGGS_BLOCK.get(),
							stack1Item()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> PICKLED_EGG_ITEM = EDItemGenerator
			.register("pickled_egg_item",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.PICKLED_EGG), true))
			.advancementSnack().servingToolTip().finish();

	public static final RegistryObject<JarSingularBlock> PICKLED_FISH_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("pickled_fish_block", () -> new JarSingularBlock(BlockBehaviour.Properties
					.copy(Blocks.GLASS).strength(0.8F).sound(SoundType.GLASS).mapColor(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Item> PICKLED_FISH_BLOCK_ITEM = EDItemGenerator
			.register("pickled_fish_block_item",
					() -> new JarSingularItem(PICKLED_FISH_BLOCK.get(),
							stack1Item()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> PICKLED_FISH_ITEM = EDItemGenerator
			.register("pickled_fish_item",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.PICKLED_FISH), true))
			.advancementSnack().servingToolTip().finish();

	public static final RegistryObject<JarSingularBlock> PICKLED_SAUSAGE_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("pickled_sausage_block", () -> new JarSingularBlock(BlockBehaviour.Properties
					.copy(Blocks.GLASS).strength(0.8F).sound(SoundType.GLASS).mapColor(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Item> PICKLED_SAUSAGE_BLOCK_ITEM = EDItemGenerator
			.register("pickled_sausage_block_item",
					() -> new JarSingularItem(PICKLED_SAUSAGE_BLOCK.get(),
							stack1Item()))
			.advancementButchercraft().finish();
	public static final RegistryObject<Item> PICKLED_SAUSAGE_ITEM = EDItemGenerator
			.register("pickled_sausage_item",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.PICKLED_SAUSAGE), true))
			.advancementButchercraft().servingToolTip().finish();

	public static final RegistryObject<JarSingularBlock> PICKLED_GINGER_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("pickled_ginger_block", () -> new JarSingularBlock(BlockBehaviour.Properties
					.copy(Blocks.GLASS).strength(0.8F).sound(SoundType.GLASS).mapColor(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Item> PICKLED_GINGER_BLOCK_ITEM = EDItemGenerator
			.register("pickled_ginger_block_item",
					() -> new JarSingularItem(PICKLED_GINGER_BLOCK.get(),
							stack1Item()))
			.advancementFeast().finish();

	public static final RegistryObject<Item> PICKLE_JUICE = EDItemGenerator
			.register("pickle_juice",
					() -> new EffectDrinkItem(drinkItem(), true, true, 0,
							new MobEffectInstance(ExtraDelightMobEffects.PICKLED.get(), FoodValues.MEDIUM_DURATION, 1)))
			.advancementIngredients().finish();
	public static final RegistryObject<Item> PICKLE_JUICE_FLUID_BUCKET = ExtraDelightItems.ITEMS.register(
			"pickle_juice_fluid_bucket", () -> ExtraDelightItems.stack1bucketItem(ExtraDelightFluids.PICKLE_JUICE));
	public static final RegistryObject<VinegarFluidBlock> PICKLE_JUICE_FLUID_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"pickle_juice_fluid_block", () -> new VinegarFluidBlock(ExtraDelightFluids.PICKLE_JUICE.FLUID.get(),
					BlockBehaviour.Properties.copy(Blocks.WATER).noCollission().strength(100.0F).noLootTable()));

	public static final RegistryObject<Item> SOY_SAUCE_ITEM = EDItemGenerator
			.register("soy_sauce_item",
					() -> new Item(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(EDFoods.SOY_SAUCE)))
			.advancementIngredients().finish();

	public static final RegistryObject<Item> SAUERKRAUT_ITEM = EDItemGenerator
			.register("sauerkraut_item",
					() -> new ToolTipConsumableItem(
							new Item.Properties().craftRemainder(Items.BOWL).food(EDFoods.SAUERKRAUT), true))
			.advancementIngredients().finish();

	public static final RegistryObject<Item> KIMCHI_ITEM = EDItemGenerator
			.register("kimchi_item",
					() -> new ToolTipConsumableItem(
							new Item.Properties().craftRemainder(Items.BOWL).food(EDFoods.KIMCHI), true))
			.advancementIngredients().finish();

	public static final RegistryObject<Item> HOT_SAUCE_ITEM = EDItemGenerator
			.register("hot_sauce_item",
					() -> new ToolTipConsumableItem(
							new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(EDFoods.HOT_SAUCE), true))
			.advancementIngredients().finish();

	public static final RegistryObject<Item> MISO_PASTE_ITEM = EDItemGenerator
			.register("miso_paste_item",
					() -> new Item(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(EDFoods.MISO_PASTE)))
			.advancementIngredients().finish();

	public static final RegistryObject<Item> NATTO_ITEM = EDItemGenerator
			.register("natto_item",
					() -> new Item(new Item.Properties().craftRemainder(Items.BOWL).food(EDFoods.NATTO)))
			.advancementIngredients().finish();

	public static final RegistryObject<Item> FISH_SAUCE_ITEM = EDItemGenerator
			.register("fish_sauce_item",
					() -> new Item(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(EDFoods.FISH_SAUCE)))
			.advancementIngredients().finish();

	public static final RegistryObject<Item> SALAMI_MIX = EDItemGenerator
			.register("salami_mix", () -> new Item(new Item.Properties().food(EDFoods.SALAMI_MIX)))
			.advancementIngredients().finish();

	public static final RegistryObject<Block> UNRIPE_SALAMI_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("unripe_salami_block", () -> new UnripeSalamiBlock(
					Block.Properties.copy(Blocks.ACACIA_LEAVES).mapColor(MapColor.TERRACOTTA_PINK)));
	public static final RegistryObject<Item> UNRIPE_SALAMI_ITEM = EDItemGenerator
			.register("unripe_salami_item",
					() -> new BlockItem(UNRIPE_SALAMI_BLOCK.get(), new Item.Properties().food(EDFoods.UNRIPE_SALAMI)))
			.advancementIngredients().finish();

	public static final RegistryObject<Block> SALAMI_BLOCK = ExtraDelightBlocks.BLOCKS.register("salami_block",
			() -> new RipeSalamiBlock(
					Block.Properties.copy(Blocks.ACACIA_LEAVES).mapColor(MapColor.TERRACOTTA_RED)));
	public static final RegistryObject<Item> SALAMI_ITEM = EDItemGenerator.register("salami_item",
			() -> new BlockItem(SALAMI_BLOCK.get(), new Item.Properties().food(EDFoods.SALAMI)) {
				@Override
				public void appendHoverText(ItemStack stack, Level context,
						List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
					tooltipComponents.add(Component.literal("")
							.append(Component.translatable(ExtraDelight.MOD_ID + ".salami.tooltip"))
							.withStyle(ChatFormatting.BLUE));
				}
			}).advancementSnack().finish();

	public static final RegistryObject<Item> SOAKED_SOYBEANS_ITEM = EDItemGenerator
			.register("soaked_soybeans_item",
					() -> new Item(new Item.Properties().craftRemainder(Items.BOWL).food(EDFoods.COOKED_SOYBEANS)))
			.advancementIngredients().finish();
	public static final RegistryObject<Item> MASHED_SOYBEANS_ITEM = EDItemGenerator
			.register("mashed_soybeans_item", () -> new Item(new Item.Properties().food(EDFoods.COOKED_SOYBEANS)))
			.advancementIngredients().finish();
	public static final RegistryObject<Item> COOKED_SOYBEANS_ITEM = EDItemGenerator
			.register("cooked_soybeans_item", () -> new Item(new Item.Properties().food(EDFoods.COOKED_SOYBEANS)))
			.advancementIngredients().finish();
	public static final RegistryObject<Item> SOY_MILK = EDItemGenerator
			.register("soy_milk", () -> new MilkBottleItem(drinkItem())).advancementIngredients().finish();

	public static final RegistryObject<Item> NAEM_MOO_ITEM = EDItemGenerator
			.register("naem_moo_item",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.NAEM_MOO), true))
			.advancementSnack().finish();

	public static final RegistryObject<Item> SLICED_BEETROOT_ITEM = EDItemGenerator
			.register("sliced_beetroot_item", () -> new Item(new Item.Properties().food(Foods.BEETROOT)))
			.advancementIngredients().finish();
	public static final RegistryObject<Item> SHREDDED_CABBAGE_ITEM = EDItemGenerator
			.register("shredded_cabbage_item", () -> new Item(new Item.Properties().food(FoodValues.CABBAGE)))
			.advancementIngredients().finish();
	public static final RegistryObject<Item> SLICED_CUCUMBER_ITEM = EDItemGenerator
			.register("sliced_cucumber_item", () -> new Item(new Item.Properties().food(EDFoods.CUCUMBER)))
			.advancementIngredients().finish();
	public static final RegistryObject<Item> SLICED_GHERKIN_ITEM = EDItemGenerator
			.register("sliced_gherkin_item",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.GHERKINS), true))
			.advancementIngredients().finish();

	public static final RegistryObject<Item> GAZPACHO = EDItemGenerator
			.register("gazpacho", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.GAZPACHO), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> EDAMAME = EDItemGenerator
			.register("edamame", () -> new Item(bowlFoodItem(EDFoods.EDAMAME))).advancementSnack().finish();
	public static final RegistryObject<Item> BEEF_BULGOGI = EDItemGenerator
			.register("beef_bulgogi", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.BEEF_BULGOGI), true))
			.advancementButchercraft().finish();
	public static final RegistryObject<Item> HONEY_CHILI_CHICKEN = EDItemGenerator
			.register("honey_chili_chicken",
					() -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.HONEY_CHILI_CHICKEN), true))
			.advancementButchercraft().finish();
	public static final RegistryObject<Item> CARAMEL_CHICKEN = EDItemGenerator
			.register("caramel_chicken", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.CARAMEL_CHICKEN), true))
			.advancementButchercraft().finish();
	public static final RegistryObject<RecipeFeastBlock> SOY_GLAZED_SALMON_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"soy_glazed_salmon_block",
			() -> new RecipeFeastBlock(
					BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_ORANGE), true,
					Block.box(0, 0, 0, 0, 0, 0), Block.box(-4.0D, 0.0D, 3.5D, 20.0D, 1.0D, 12.5D),
					Block.box(3.5D, 0.0D, -4.0D, 12.5D, 1.0D, 20.0D)));
	public static final RegistryObject<Item> SOY_GLAZED_SALMON_BLOCK_ITEM = EDItemGenerator
			.register("soy_glazed_salmon_block_item", () -> new BlockItem(SOY_GLAZED_SALMON_BLOCK.get(), stack1Item()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> SOY_GLAZED_SALMON_ITEM = EDItemGenerator
			.register("soy_glazed_salmon_item",
					() -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.SOY_GLAZED_SALMON), true))
			.advancementMeal().servingToolTip().finish();
	public static final RegistryObject<Item> STEAK_PICKLED_ONION_PIE_SLICE = EDItemGenerator
			.register("steak_pickled_onion_pie_slice",
					() -> new ToolTipConsumableItem(foodItem(EDFoods.STEAK_PICKLED_ONION_PIE_SLICE), true))
			.advancementButchercraft().servingToolTip().finish();
	public static final RegistryObject<Block> STEAK_PICKLED_ONION_PIE = ExtraDelightBlocks.BLOCKS.register(
			"steak_pickled_onion_pie",
			() -> new PieBlock(Block.Properties.copy(Blocks.CAKE), STEAK_PICKLED_ONION_PIE_SLICE));
	public static final RegistryObject<Item> STEAK_PICKLED_ONION_PIE_ITEM = EDItemGenerator
			.register("steak_pickled_onion_pie_item",
					() -> new BlockItem(STEAK_PICKLED_ONION_PIE.get(), new Item.Properties()))
			.advancementButchercraft().feastToolTip().finish();
	public static final RegistryObject<Item> KIWIBURGER = EDItemGenerator
			.register("kiwiburger",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.KIWIBURGER), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> SHIRAZI_SALAD = EDItemGenerator
			.register("shirazi_salad", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.SHIRAZI_SALAD), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> CUCUMBER_SALAD = EDItemGenerator
			.register("cucumber_salad", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.CUCUMBER_SALAD), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> MISO_SOUP = EDItemGenerator
			.register("miso_soup", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.MISO_SOUP), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> NATTO_AND_RICE = EDItemGenerator
			.register("natto_and_rice", () -> new Item(new Item.Properties().food(EDFoods.NATTO_AND_RICE)) {
				@Override
				public void appendHoverText(ItemStack stack, Level context,
						List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
					tooltipComponents.add(Component.literal("")
							.append(Component.translatable(ExtraDelight.MOD_ID + ".natto_rice.tooltip"))
							.withStyle(ChatFormatting.BLUE));
				}
			}).advancementMeal().finish();
	public static final RegistryObject<Item> SAUERKRAUT_SOUP = EDItemGenerator
			.register("sauerkraut_soup", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.SAUERKRAUT_SOUP), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> SAUERKRAUT_AND_SAUSAGE = EDItemGenerator
			.register("sauerkraut_and_sausage",
					() -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.SAUERKRAUT_AND_SAUSAGE), true))
			.advancementButchercraft().finish();
	public static final RegistryObject<Item> YEAST_SPREAD = EDItemGenerator
			.register("yeast_spread",
					() -> new ToolTipConsumableItem(
							new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(EDFoods.YEAST_SPREAD), true))
			.advancementIngredients().finish();

	public static final RegistryObject<RecipeFeastBlock> CHEESYMITE_SCROLL_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"cheesymite_scroll_block",
			() -> new RecipeFeastBlock(
					BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_ORANGE), true,
					ExtraDelightBlocks.plate));
	public static final RegistryObject<Item> CHEESYMITE_SCROLL_BLOCK_ITEM = EDItemGenerator
			.register("cheesymite_scroll_block_item", () -> new BlockItem(CHEESYMITE_SCROLL_BLOCK.get(), stack1Item()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> CHEESYMITE_SCROLL_SERVING = EDItemGenerator.register("cheesymite_scroll",
			() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.CHEESYMITE_SCROLL_SERVING), true))
			.advancementSnack().servingToolTip().finish();

	public static final RegistryObject<Item> MORKOVCHA = EDItemGenerator
			.register("morkovcha", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.MORKOVCHA), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> ZUPA_OGORKOWA = EDItemGenerator
			.register("zupa_ogorkowa", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.ZUPA_OGORKOWA), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> KIMCHI_FRIED_RICE = EDItemGenerator
			.register("kimchi_fried_rice",
					() -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.KIMCHI_FRIED_RICE), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> KONGJANG = EDItemGenerator
			.register("kongjang", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.KONGJANG), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> CHEESEBURGER_PICKLE = EDItemGenerator
			.register("cheeseburger_pickle",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.CHEESEBURGER_PICKLE), true))
			.advancementSnack().finish();
	public static final RegistryObject<Item> HOT_WINGS = EDItemGenerator
			.register("hot_wings", () -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.HOT_WINGS), true))
			.advancementMeal().finish();

	public static final RegistryObject<Block> JAR_DISPLAY_BLOCK = ExtraDelightBlocks.BLOCKS.register("jar_display_block",
			() -> new JarDisplayBlock(Block.Properties.copy(Blocks.GLASS).strength(0.8F)));

	public static final RegistryObject<Item> COOKED_WHEAT_SEEDS = EDItemGenerator
			.register("cooked_wheat_seeds", () -> new Item(new Item.Properties().food(EDFoods.COOKED_WHEAT_SEEDS)))
			.advancementIngredients().finish();

}
