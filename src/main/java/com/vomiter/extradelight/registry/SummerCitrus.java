package com.vomiter.extradelight.registry;

import static com.vomiter.extradelight.registry.ExtraDelightBlocks.bowl;
import static com.vomiter.extradelight.registry.ExtraDelightBlocks.plate;
import static com.vomiter.extradelight.registry.ExtraDelightItems.*;
import static vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem;
import static vectorwing.farmersdelight.common.registry.ModItems.foodItem;

import java.util.List;


import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.blocks.leaf.FruitLeafBlock;
import com.vomiter.extradelight.common.blocks.RecipeFeastBlock;
import com.vomiter.extradelight.common.items.GourmetHotCocoa;
import com.vomiter.extradelight.common.items.SourJuiceItem;
import com.vomiter.extradelight.common.items.XAdeDrink;
import com.vomiter.extradelight.common.complex.portable.picnicbasket.PicnicBasketBlock;
import com.vomiter.extradelight.common.complex.jardisplay.JarSingularBlock;
import com.vomiter.extradelight.common.complex.jardisplay.JarSingularItem;
import com.vomiter.extradelight.common.fluids.VinegarFluidBlock;
import com.vomiter.extradelight.common.items.EDFoods;
import com.vomiter.extradelight.common.items.ToolTipConsumableItem;
import com.vomiter.extradelight.common.blocks.RawBakedAlaskaBlock;
import com.vomiter.extradelight.util.EDItemGenerator;
import com.vomiter.extradelight.worldgen.features.trees.ExtraDelightTreeGrowers;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class SummerCitrus {
	// Lemon
	public static final RegistryObject<Item> LEMON = EDItemGenerator
			.register("lemon", () -> new ToolTipConsumableItem(foodItem(EDFoods.LEMON), true)).advancementIngredients()
			.finish();
	public static final RegistryObject<Block> LEMON_PETAL_LITTER = ExtraDelightBlocks.BLOCKS
			.register("lemon_petal_litter", () -> new CarpetBlock(Block.Properties.copy(Blocks.ACACIA_LEAVES)));
	public static final RegistryObject<FruitLeafBlock> LEMON_LEAVES = ExtraDelightBlocks.BLOCKS.register("lemon_leaves",
			() -> new FruitLeafBlock(Block.Properties.copy(Blocks.ACACIA_LEAVES), LEMON, LEMON_PETAL_LITTER,
					ExtraDelightParticles.CITRUS_PETALS));
	public static final RegistryObject<Item> LEMON_PETAL_LITTER_ITEM = ExtraDelightItems.ITEMS.register(
			"lemon_petal_litter_item",
			() -> new BlockItem(SummerCitrus.LEMON_PETAL_LITTER.get(), new Item.Properties()));
	public static final RegistryObject<Item> LEMON_LEAVES_ITEM = ExtraDelightItems.ITEMS.register("lemon_leaves",
			() -> new BlockItem(LEMON_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<SaplingBlock> LEMON_SAPLING = ExtraDelightBlocks.BLOCKS.register("lemon_sapling",
			() -> new SaplingBlock(ExtraDelightTreeGrowers.LEMON,
					Block.Properties.copy(Blocks.DARK_OAK_SAPLING)));
	public static final RegistryObject<Item> LEMON_SAPLING_ITEM = ExtraDelightItems.ITEMS.register("lemon_sapling",
			() -> new BlockItem(LEMON_SAPLING.get(), new Item.Properties()));
	public static final RegistryObject<Block> POTTED_LEMON_SAPLING = ExtraDelightBlocks.BLOCKS
			.register("potted_lemon_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT,
					LEMON_SAPLING, Block.Properties.copy(Blocks.POTTED_ACACIA_SAPLING).mapColor(MapColor.PLANT)));
	public static final RegistryObject<Item> LEMON_JUICE = EDItemGenerator
			.register("lemon_juice", () -> new SourJuiceItem(drinkItem(), 3, 100)).advancementIngredients().finish();
	public static final RegistryObject<Item> LEMON_JUICE_FLUID_BUCKET = ExtraDelightItems.ITEMS.register(
			"lemon_juice_fluid_bucket", () -> ExtraDelightItems.stack1bucketItem(ExtraDelightFluids.LEMON_JUICE));
	public static final RegistryObject<VinegarFluidBlock> LEMON_JUICE_FLUID_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("lemon_juice_fluid_block", () -> new VinegarFluidBlock(ExtraDelightFluids.LEMON_JUICE.FLUID.get(),
					BlockBehaviour.Properties.copy(Blocks.WATER).noCollission().strength(100.0F).noLootTable()));
	public static final RegistryObject<Block> LEMON_CRATE = ExtraDelightBlocks.BLOCKS.register("lemon_crate",
			() -> new Block(Block.Properties.copy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.PLANT)));
	public static final RegistryObject<Item> LEMON_CRATE_ITEM = ExtraDelightItems.ITEMS.register("lemon_crate_item",
			() -> new BlockItem(LEMON_CRATE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SLICED_LEMON = EDItemGenerator
			.register("sliced_lemon", () -> new Item(new Item.Properties().food(EDFoods.LEMON)))
			.advancementIngredients().finish();
	public static final RegistryObject<Item> LEMON_ZEST = EDItemGenerator
			.register("lemon_zest", () -> new Item(new Item.Properties())).advancementIngredients().finish();

	// Lime
	public static final RegistryObject<Item> LIME = EDItemGenerator
			.register("lime", () -> new ToolTipConsumableItem(foodItem(EDFoods.LIME), true)).advancementIngredients()
			.finish();
	public static final RegistryObject<Block> LIME_PETAL_LITTER = ExtraDelightBlocks.BLOCKS.register("lime_petal_litter",
			() -> new CarpetBlock(Block.Properties.copy(Blocks.ACACIA_LEAVES)));
	public static final RegistryObject<Item> LIME_PETAL_LITTER_ITEM = ExtraDelightItems.ITEMS.register(
			"lime_petal_litter_item", () -> new BlockItem(SummerCitrus.LIME_PETAL_LITTER.get(), new Item.Properties()));
	public static final RegistryObject<FruitLeafBlock> LIME_LEAVES = ExtraDelightBlocks.BLOCKS.register("lime_leaves",
			() -> new FruitLeafBlock(Block.Properties.copy(Blocks.ACACIA_LEAVES), LIME, LIME_PETAL_LITTER,
					ExtraDelightParticles.CITRUS_PETALS));
	public static final RegistryObject<Item> LIME_LEAVES_ITEM = ExtraDelightItems.ITEMS.register("lime_leaves",
			() -> new BlockItem(LIME_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<SaplingBlock> LIME_SAPLING = ExtraDelightBlocks.BLOCKS.register("lime_sapling",
			() -> new SaplingBlock(ExtraDelightTreeGrowers.LIME, Block.Properties.copy(Blocks.DARK_OAK_SAPLING)));
	public static final RegistryObject<Item> LIME_SAPLING_ITEM = ExtraDelightItems.ITEMS.register("lime_sapling",
			() -> new BlockItem(LIME_SAPLING.get(), new Item.Properties()));
	public static final RegistryObject<Block> POTTED_LIME_SAPLING = ExtraDelightBlocks.BLOCKS
			.register("potted_lime_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT,
					LIME_SAPLING, Block.Properties.copy(Blocks.POTTED_ACACIA_SAPLING).mapColor(MapColor.PLANT)));
	public static final RegistryObject<Item> LIME_JUICE = EDItemGenerator
			.register("lime_juice", () -> new SourJuiceItem(drinkItem(), 4, 100)).advancementIngredients().finish();
	public static final RegistryObject<Item> LIME_JUICE_FLUID_BUCKET = ExtraDelightItems.ITEMS.register(
			"lime_juice_fluid_bucket", () -> ExtraDelightItems.stack1bucketItem(ExtraDelightFluids.LIME_JUICE));
	public static final RegistryObject<VinegarFluidBlock> LIME_JUICE_FLUID_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("lime_juice_fluid_block", () -> new VinegarFluidBlock(ExtraDelightFluids.LIME_JUICE.FLUID.get(),
					BlockBehaviour.Properties.copy(Blocks.WATER).noCollission().strength(100.0F).noLootTable()));
	public static final RegistryObject<Block> LIME_CRATE = ExtraDelightBlocks.BLOCKS.register("lime_crate",
			() -> new Block(Block.Properties.copy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.PLANT)));
	public static final RegistryObject<Item> LIME_CRATE_ITEM = ExtraDelightItems.ITEMS.register("lime_crate_item",
			() -> new BlockItem(LIME_CRATE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SLICED_LIME = EDItemGenerator
			.register("sliced_lime", () -> new Item(new Item.Properties().food(EDFoods.LIME))).advancementIngredients()
			.finish();
	public static final RegistryObject<Item> LIME_ZEST = EDItemGenerator
			.register("lime_zest", () -> new Item(new Item.Properties())).advancementIngredients().finish();

	// Orange
	public static final RegistryObject<Item> ORANGE = EDItemGenerator
			.register("orange", () -> new ToolTipConsumableItem(foodItem(EDFoods.ORANGE), true))
			.advancementIngredients().finish();
	public static final RegistryObject<Block> ORANGE_PETAL_LITTER = ExtraDelightBlocks.BLOCKS
			.register("orange_petal_litter", () -> new CarpetBlock(Block.Properties.copy(Blocks.ACACIA_LEAVES)));
	public static final RegistryObject<Item> ORANGE_PETAL_LITTER_ITEM = ExtraDelightItems.ITEMS.register(
			"orange_petal_litter_item",
			() -> new BlockItem(SummerCitrus.ORANGE_PETAL_LITTER.get(), new Item.Properties()));
	public static final RegistryObject<FruitLeafBlock> ORANGE_LEAVES = ExtraDelightBlocks.BLOCKS
			.register("orange_leaves", () -> new FruitLeafBlock(Block.Properties.copy(Blocks.ACACIA_LEAVES),
					ORANGE, ORANGE_PETAL_LITTER, ExtraDelightParticles.CITRUS_PETALS));
	public static final RegistryObject<Item> ORANGE_LEAVES_ITEM = ExtraDelightItems.ITEMS.register("orange_leaves",
			() -> new BlockItem(ORANGE_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<SaplingBlock> ORANGE_SAPLING = ExtraDelightBlocks.BLOCKS
			.register("orange_sapling", () -> new SaplingBlock(ExtraDelightTreeGrowers.ORANGE,
					Block.Properties.copy(Blocks.DARK_OAK_SAPLING)));
	public static final RegistryObject<Item> ORANGE_SAPLING_ITEM = ExtraDelightItems.ITEMS.register("orange_sapling",
			() -> new BlockItem(ORANGE_SAPLING.get(), new Item.Properties()));
	public static final RegistryObject<Block> POTTED_ORANGE_SAPLING = ExtraDelightBlocks.BLOCKS.register(
			"potted_orange_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ORANGE_SAPLING,
					Block.Properties.copy(Blocks.POTTED_ACACIA_SAPLING).mapColor(MapColor.PLANT)));
	public static final RegistryObject<Item> ORANGE_JUICE = EDItemGenerator
			.register("orange_juice", () -> new SourJuiceItem(drinkItem(), 1, 50)).advancementIngredients().finish();
	public static final RegistryObject<Item> ORANGE_JUICE_FLUID_BUCKET = ExtraDelightItems.ITEMS.register(
			"orange_juice_fluid_bucket", () -> ExtraDelightItems.stack1bucketItem(ExtraDelightFluids.ORANGE_JUICE));
	public static final RegistryObject<VinegarFluidBlock> ORANGE_JUICE_FLUID_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"orange_juice_fluid_block", () -> new VinegarFluidBlock(ExtraDelightFluids.ORANGE_JUICE.FLUID.get(),
					BlockBehaviour.Properties.copy(Blocks.WATER).noCollission().strength(100.0F).noLootTable()));
	public static final RegistryObject<Block> ORANGE_CRATE = ExtraDelightBlocks.BLOCKS.register("orange_crate",
			() -> new Block(Block.Properties.copy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.PLANT)));
	public static final RegistryObject<Item> ORANGE_CRATE_ITEM = ExtraDelightItems.ITEMS.register("orange_crate_item",
			() -> new BlockItem(ORANGE_CRATE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SLICED_ORANGE = EDItemGenerator
			.register("sliced_orange", () -> new Item(new Item.Properties().food(EDFoods.ORANGE)))
			.advancementIngredients().finish();
	public static final RegistryObject<Item> ORANGE_ZEST = EDItemGenerator
			.register("orange_zest", () -> new Item(new Item.Properties())).advancementIngredients().finish();

	// Grapefruit
	public static final RegistryObject<Item> GRAPEFRUIT = EDItemGenerator
			.register("grapefruit", () -> new ToolTipConsumableItem(foodItem(EDFoods.GRAPEFRUIT), true))
			.advancementIngredients().finish();
	public static final RegistryObject<Block> GRAPEFRUIT_PETAL_LITTER = ExtraDelightBlocks.BLOCKS.register(
			"grapefruit_petal_litter", () -> new CarpetBlock(Block.Properties.copy(Blocks.ACACIA_LEAVES)));
	public static final RegistryObject<Item> GRAPEFRUIT_PETAL_LITTER_ITEM = ExtraDelightItems.ITEMS.register(
			"grapefruit_petal_litter_item",
			() -> new BlockItem(SummerCitrus.GRAPEFRUIT_PETAL_LITTER.get(), new Item.Properties()));
	public static final RegistryObject<FruitLeafBlock> GRAPEFRUIT_LEAVES = ExtraDelightBlocks.BLOCKS
			.register("grapefruit_leaves", () -> new FruitLeafBlock(Block.Properties.copy(Blocks.ACACIA_LEAVES),
					GRAPEFRUIT, GRAPEFRUIT_PETAL_LITTER, ExtraDelightParticles.CITRUS_PETALS));
	public static final RegistryObject<Item> GRAPEFRUIT_LEAVES_ITEM = ExtraDelightItems.ITEMS
			.register("grapefruit_leaves", () -> new BlockItem(GRAPEFRUIT_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<SaplingBlock> GRAPEFRUIT_SAPLING = ExtraDelightBlocks.BLOCKS
			.register("grapefruit_sapling", () -> new SaplingBlock(ExtraDelightTreeGrowers.GRAPEFRUIT,
					Block.Properties.copy(Blocks.DARK_OAK_SAPLING)));
	public static final RegistryObject<Item> GRAPEFRUIT_SAPLING_ITEM = ExtraDelightItems.ITEMS
			.register("grapefruit_sapling", () -> new BlockItem(GRAPEFRUIT_SAPLING.get(), new Item.Properties()));
	public static final RegistryObject<Block> POTTED_GRAPEFRUIT_SAPLING = ExtraDelightBlocks.BLOCKS.register(
			"potted_grapefruit_sapling",
			() -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GRAPEFRUIT_SAPLING,
					Block.Properties.copy(Blocks.POTTED_ACACIA_SAPLING).mapColor(MapColor.PLANT)));
	public static final RegistryObject<Item> GRAPEFRUIT_JUICE = EDItemGenerator
			.register("grapefruit_juice", () -> new SourJuiceItem(drinkItem(), 1, 50)).advancementIngredients()
			.finish();
	public static final RegistryObject<Item> GRAPEFRUIT_JUICE_FLUID_BUCKET = ExtraDelightItems.ITEMS.register(
			"grapefruit_juice_fluid_bucket",
			() -> ExtraDelightItems.stack1bucketItem(ExtraDelightFluids.GRAPEFRUIT_JUICE));
	public static final RegistryObject<VinegarFluidBlock> GRAPEFRUIT_JUICE_FLUID_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("grapefruit_juice_fluid_block", () -> new VinegarFluidBlock(
					ExtraDelightFluids.GRAPEFRUIT_JUICE.FLUID.get(),
					BlockBehaviour.Properties.copy(Blocks.WATER).noCollission().strength(100.0F).noLootTable()));
	public static final RegistryObject<Block> GRAPEFRUIT_CRATE = ExtraDelightBlocks.BLOCKS.register("grapefruit_crate",
			() -> new Block(Block.Properties.copy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.PLANT)));
	public static final RegistryObject<Item> GRAPEFRUIT_CRATE_ITEM = ExtraDelightItems.ITEMS
			.register("grapefruit_crate_item", () -> new BlockItem(GRAPEFRUIT_CRATE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SLICED_GRAPEFRUIT = EDItemGenerator
			.register("sliced_grapefruit", () -> new Item(new Item.Properties().food(EDFoods.GRAPEFRUIT)))
			.advancementIngredients().finish();

	// Watermelon
	public static final RegistryObject<Item> MELON_CHUNKS = EDItemGenerator
			.register("melon_chunks", () -> new Item(new Item.Properties())).advancementIngredients().finish();
	public static final RegistryObject<Item> MELON_RIND = EDItemGenerator
			.register("melon_rind", () -> new Item(foodItem(EDFoods.MELON_RIND))).advancementIngredients().finish();

	// The rest!
	public static final RegistryObject<Item> ICE_CUBES = EDItemGenerator
			.register("ice_cubes",
					() -> new ToolTipConsumableItem(foodItem(EDFoods.ICE_CUBE))
            ).advancementIngredients().finish();
	public static final RegistryObject<Item> MERINGUE = EDItemGenerator
			.register("meringue", () -> new Item(foodItem(EDFoods.MERINGUE))).advancementIngredients().finish();
	public static final RegistryObject<Item> LEMON_CURD = EDItemGenerator
			.register("lemon_curd", () -> new ToolTipConsumableItem(bottleFoodItem(EDFoods.LEMON_CURD), true))
			.advancementIngredients().finish();
	public static final RegistryObject<Item> LEMON_MERINGUE_PIE_SLICE = EDItemGenerator
			.register("lemon_meringue_pie_slice",
					() -> new ToolTipConsumableItem(foodItem(EDFoods.LEMON_MERINGUE_PIE_SLICE), true))
			.advancementDessert().servingToolTip().finish();
	public static final RegistryObject<Block> LEMON_MERINGUE_PIE = ExtraDelightBlocks.BLOCKS.register(
			"lemon_meringue_pie",
			() -> new PieBlock(Block.Properties.copy(Blocks.CAKE), LEMON_MERINGUE_PIE_SLICE));
	public static final RegistryObject<Item> LEMON_MERINGUE_PIE_ITEM = EDItemGenerator
			.register("lemon_meringue_pie_item", () -> new BlockItem(LEMON_MERINGUE_PIE.get(), new Item.Properties()))
			.advancementFeast().feastToolTip().finish();
	public static final RegistryObject<Item> KEY_LIME_PIE_SLICE = EDItemGenerator
			.register("key_lime_pie_slice", () -> new ToolTipConsumableItem(foodItem(EDFoods.KEY_LIME_PIE_SLICE), true))
			.advancementDessert().servingToolTip().finish();
	public static final RegistryObject<Block> KEY_LIME_PIE = ExtraDelightBlocks.BLOCKS.register("key_lime_pie",
			() -> new PieBlock(Block.Properties.copy(Blocks.CAKE), KEY_LIME_PIE_SLICE));
	public static final RegistryObject<Item> KEY_LIME_PIE_ITEM = EDItemGenerator
			.register("key_lime_pie_item", () -> new BlockItem(KEY_LIME_PIE.get(), new Item.Properties()))
			.advancementFeast().feastToolTip().finish();
	public static final RegistryObject<Item> LEMONADE = EDItemGenerator
			.register("lemonade", () -> new XAdeDrink(drinkItem(), 2)).drink().setHydration(20).setThirst(8)
			.setPoison(0).isCold(true).finish();
	public static final RegistryObject<RecipeFeastBlock> LEMONADE_TRAY = ExtraDelightBlocks.BLOCKS.register(
			"lemonade_tray",
			() -> new RecipeFeastBlock(Block.Properties.copy(Blocks.GLASS).mapColor(MapColor.COLOR_YELLOW), true,
					plate));
	public static final RegistryObject<Item> LEMONADE_TRAY_ITEM = EDItemGenerator
			.register("lemonade_tray_item", () -> new BlockItem(LEMONADE_TRAY.get(), new Item.Properties()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> LIMEADE = EDItemGenerator
			.register("limeade", () -> new XAdeDrink(drinkItem(), 2)).drink().setHydration(20).setThirst(6).setPoison(0)
			.isHot(false).finish();
	public static final RegistryObject<RecipeFeastBlock> LIMEADE_TRAY = ExtraDelightBlocks.BLOCKS.register(
			"limeade_tray",
			() -> new RecipeFeastBlock(Block.Properties.copy(Blocks.GLASS).mapColor(MapColor.COLOR_GREEN), true,
					plate));
	public static final RegistryObject<Item> LIMEADE_TRAY_ITEM = EDItemGenerator
			.register("limeade_tray_item", () -> new BlockItem(LIMEADE_TRAY.get(), new Item.Properties()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> ORANGEADE = EDItemGenerator
			.register("orangeade", () -> new XAdeDrink(drinkItem(), 2)).drink().setHydration(20).setThirst(6)
			.setPoison(0).isHot(false).finish();
	public static final RegistryObject<RecipeFeastBlock> ORANGEADE_TRAY = ExtraDelightBlocks.BLOCKS.register(
			"orangeade_tray",
			() -> new RecipeFeastBlock(Block.Properties.copy(Blocks.GLASS).mapColor(MapColor.COLOR_ORANGE), true,
					plate));
	public static final RegistryObject<Item> ORANGEADE_TRAY_ITEM = EDItemGenerator
			.register("orangeade_tray_item", () -> new BlockItem(ORANGEADE_TRAY.get(), new Item.Properties()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> MELON_GAZPACHO = EDItemGenerator
			.register("melon_gazpacho", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.MELON_GAZPACHO), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> THAI_MELON_SALAD = EDItemGenerator
			.register("thai_melon_salad", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.THAI_MELON_SALAD), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> ETON_MESS = EDItemGenerator
			.register("eton_mess", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.ICE_CREAM_SUNDAE), true))
			.advancementDessert().finish();
	public static final RegistryObject<Item> DALGONA_COFFEE = EDItemGenerator
			.register("dalgona_coffee", () -> new GourmetHotCocoa(drinkItem())).drink().setHydration(40).setThirst(4)
			.isHot(true).setPoison(0).finish();
	public static final RegistryObject<Item> GRAPEFRUIT_BEETROOT_SALAD = EDItemGenerator
			.register("grapefruit_beetroot_salad",
					() -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.GRAPEFRUIT_BEET_SALAD), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> CITRUS_ONION_SALAD = EDItemGenerator
			.register("citrus_onion_salad",
					() -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.CITRUS_ONION_SALAD), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> MELON_FRUIT_SALAD_SERVING = EDItemGenerator
			.register("melon_fruit_salad_serving",
					() -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.MELON_FRUIT_SALAD), true))
			.advancementDessert().servingToolTip().finish();
	public static final RegistryObject<RecipeFeastBlock> MELON_FRUIT_SALAD = ExtraDelightBlocks.BLOCKS.register(
			"melon_fruit_salad",
			() -> new RecipeFeastBlock(Block.Properties.copy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_GREEN),
					true, bowl));
	public static final RegistryObject<Item> MELON_FRUIT_SALAD_ITEM = EDItemGenerator
			.register("melon_fruit_salad_item", () -> new BlockItem(MELON_FRUIT_SALAD.get(), new Item.Properties()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> LEMON_CUCUMBER_CAKE_SLICE = EDItemGenerator
			.register("lemon_cucumber_cake_slice", () -> new Item(foodItem(FoodValues.CAKE_SLICE))).advancementDessert()
			.servingToolTip().finish();
	public static final RegistryObject<Block> LEMON_CUCUMBER_CAKE = ExtraDelightBlocks.BLOCKS
			.register("lemon_cucumber_cake", () -> new CakeBlock(Block.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Item> LEMON_CUCUMBER_CAKE_ITEM = EDItemGenerator
			.register("lemon_cucumber_cake_item", () -> new BlockItem(LEMON_CUCUMBER_CAKE.get(), new Item.Properties()))
			.advancementFeast().feastToolTip().finish();
	public static final RegistryObject<RecipeFeastBlock> BAKED_COD = ExtraDelightBlocks.BLOCKS.register("baked_cod",
			() -> new RecipeFeastBlock(
					BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_ORANGE), true,
					Block.box(0, 0, 0, 0, 0, 0), Block.box(-4.0D, 0.0D, 3.5D, 20.0D, 1.0D, 12.5D),
					Block.box(3.5D, 0.0D, -4.0D, 12.5D, 1.0D, 20.0D)));
	public static final RegistryObject<Item> BAKED_COD_ITEM = EDItemGenerator
			.register("baked_cod_item", () -> new BlockItem(BAKED_COD.get(), new Item.Properties())).advancementFeast()
			.finish();
	public static final RegistryObject<Item> BAKED_COD_SERVING = EDItemGenerator
			.register("baked_cod_serving",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.BAKED_COD), true))
			.advancementMeal().servingToolTip().finish();
	public static final RegistryObject<Item> MELON_LAYER_CAKE_SLICE = EDItemGenerator
			.register("melon_layer_cake_slice", () -> new Item(foodItem(FoodValues.CAKE_SLICE))).advancementDessert()
			.servingToolTip().finish();
	public static final RegistryObject<Block> MELON_LAYER_CAKE = ExtraDelightBlocks.BLOCKS.register("melon_layer_cake",
			() -> new CakeBlock(Block.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Item> MELON_LAYER_CAKE_ITEM = EDItemGenerator
			.register("melon_layer_cake_item", () -> new BlockItem(MELON_LAYER_CAKE.get(), new Item.Properties()))
			.advancementFeast().feastToolTip().finish();
	public static final RegistryObject<Item> GRAPEFRUIT_SORBET = EDItemGenerator
			.register("grapefruit_sorbet",
					() -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.GRAPEFRUIT_SORBET), true))
			.advancementDessert().isColdFood().finish();
	public static final RegistryObject<Item> CHOCOLATE_ORANGE = EDItemGenerator
			.register("chocolate_orange", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.CHOCOLATE_ORANGE), true))
			.advancementCandy().finish();
	public static final RegistryObject<Item> CHOCOLATE_MOUSSE = EDItemGenerator
			.register("chocolate_mousse", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.CHOCOLATE_MOUSSE), true))
			.advancementDessert().finish();
	public static final RegistryObject<Item> JAFFA_CAKE = EDItemGenerator
			.register("jaffa_cake", () -> new Item(bowlFoodItem(EDFoods.JAFFA_CAKE))).advancementDessert().finish();
	public static final RegistryObject<Item> GRILLED_GRAPEFRUIT = EDItemGenerator
			.register("grilled_grapefruit",
					() -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.GRILLED_GRAPEFRUIT), true))
			.advancementDessert().finish();
	public static final RegistryObject<Item> LEMON_DELICIOUS = EDItemGenerator
			.register("lemon_delicious", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.LEMON_DELICIOUS), true))
			.advancementDessert().finish();
	public static final RegistryObject<Item> ORANGE_CHICKEN = EDItemGenerator
			.register("orange_chicken", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.ORANGE_CHICKEN), true))
			.advancementButchercraft().finish();
	public static final RegistryObject<Item> MELON_RIND_STIRFRY = EDItemGenerator
			.register("melon_rind_stirfry",
					() -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.MELON_RIND_STIRFRY), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> LIME_SOUFFLE = EDItemGenerator
			.register("lime_souffle", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.LIME_SOUFFLE), true))
			.advancementDessert().finish();
	public static final RegistryObject<Item> CHEESE_SOUFFLE = EDItemGenerator
			.register("cheese_souffle", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.CHEESE_SOUFFLE), true))
			.advancementMeal().isHotFood().finish();
	public static final RegistryObject<Item> PAVLOVA_SLICE = EDItemGenerator
			.register("pavlova_slice", () -> new ToolTipConsumableItem(foodItem(EDFoods.PAVLOVA_SLICE), true))
			.advancementDessert().servingToolTip().finish();
	public static final RegistryObject<PieBlock> PAVLOVA = ExtraDelightBlocks.BLOCKS.register("pavlova",
			() -> new PieBlock(Block.Properties.copy(Blocks.CAKE), PAVLOVA_SLICE) {
				@Override
				public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos,
						CollisionContext context) {
					return Block.box(3.0D, 0.0D, 3.0D, 13.0D, 3.0D, 13.0D);
				}
			});
	public static final RegistryObject<Item> PAVLOVA_ITEM = EDItemGenerator
			.register("pavlova_item", () -> new BlockItem(PAVLOVA.get(), new Item.Properties())).advancementFeast()
			.feastToolTip().finish();
	public static final RegistryObject<JarSingularBlock> PRESERVED_LEMONS_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("preserved_lemons_block", () -> new JarSingularBlock(BlockBehaviour.Properties
					.copy(Blocks.GLASS).strength(0.8F).sound(SoundType.GLASS).mapColor(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Item> PRESERVED_LEMONS_BLOCK_ITEM = EDItemGenerator
			.register("preserved_lemons_block_item",
					() -> new JarSingularItem(PRESERVED_LEMONS_BLOCK.get(),
							new Item.Properties()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> PRESERVED_LEMON_ITEM = EDItemGenerator
			.register("preserved_lemon_item",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.PRESERVED_LEMON), true))
			.advancementSnack().servingToolTip().finish();
	public static final RegistryObject<JarSingularBlock> PICKLED_RINDS_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("pickled_rinds_block", () -> new JarSingularBlock(BlockBehaviour.Properties
					.copy(Blocks.GLASS).strength(0.8F).sound(SoundType.GLASS).mapColor(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Item> PICKLED_RINDS_BLOCK_ITEM = EDItemGenerator
			.register("pickled_rinds_block_item",
					() -> new JarSingularItem(PICKLED_RINDS_BLOCK.get(),
							new Item.Properties()))
			.advancementFeast().finish();
	public static final RegistryObject<Item> PICKLED_RIND_ITEM = EDItemGenerator
			.register("pickled_rind_item", () -> new Item(new Item.Properties().food(EDFoods.PICKLED_MELON_RIND)))
			.advancementSnack().servingToolTip().finish();
	public static final RegistryObject<Item> PRESERVED_LEMON_PASTA = EDItemGenerator
			.register("preserved_lemon_pasta", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.LEMON_PASTA), true))
			.advancementMeal().finish();
	public static final RegistryObject<Item> CANDIED_CITRUS_ZEST = EDItemGenerator
			.register("candied_citrus_zest",
					() -> new ToolTipConsumableItem(new Item.Properties().food(EDFoods.CANDIED_CITRUS_ZEST), true))
			.advancementCandy().finish();
	public static final RegistryObject<Item> LEMON_POSSET = EDItemGenerator
			.register("lemon_posset", () -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.LEMON_POSSET), true))
			.advancementDessert().finish();
	public static final RegistryObject<Item> MELON_LIME_GLAZED_CHICKEN = EDItemGenerator
			.register("melon_lime_glazed_chicken",
					() -> new ToolTipConsumableItem(bowlFoodItem(EDFoods.MELON_LIME_CHICKEN), true))
			.advancementButchercraft().finish();
	public static final RegistryObject<Item> KYIV_CAKE_SLICE = EDItemGenerator
			.register("kyiv_cake_slice", () -> new ToolTipConsumableItem(foodItem(EDFoods.KYIV_CAKE_SLICE), true))
			.advancementDessert().servingToolTip().finish();
	public static final RegistryObject<PieBlock> KYIV_CAKE = ExtraDelightBlocks.BLOCKS.register("kyiv_cake",
			() -> new PieBlock(Block.Properties.copy(Blocks.CAKE), KYIV_CAKE_SLICE) {
				@Override
				public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos,
						CollisionContext context) {
					return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
				}
			});
	public static final RegistryObject<Item> KYIV_CAKE_ITEM = EDItemGenerator
			.register("kyiv_cake_item", () -> new BlockItem(KYIV_CAKE.get(), new Item.Properties())).advancementFeast()
			.feastToolTip().finish();
	public static final RegistryObject<RawBakedAlaskaBlock> RAW_BAKED_ALASKA = ExtraDelightBlocks.BLOCKS
			.register("raw_baked_alaska", () -> new RawBakedAlaskaBlock(
					Block.Properties.copy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_WHITE).lightLevel(s -> {
						if (s.getValue(RawBakedAlaskaBlock.ON_FIRE))
							return 10;
						return 0;
					})) {
				@Override
				public void appendHoverText(ItemStack stack, BlockGetter context,
						List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
					tooltipComponents.add(Component.literal("")
							.append(Component.translatable(ExtraDelight.MOD_ID + ".raw_baked_alaska.tooltip"))
							.withStyle(ChatFormatting.GOLD));
				}
			});
	public static final RegistryObject<Item> RAW_BAKED_ALASKA_ITEM = EDItemGenerator
			.register("raw_baked_alaska_item", () -> new BlockItem(RAW_BAKED_ALASKA.get(), new Item.Properties()))
			.finish();
	public static final RegistryObject<Item> BAKED_ALASKA_SERVING = EDItemGenerator
			.register("baked_alaska_serving",
					() -> new ToolTipConsumableItem(foodItem(EDFoods.BAKED_ALASKA_SLICE), true))
			.advancementDessert().servingToolTip().finish();
	public static final RegistryObject<RecipeFeastBlock> BAKED_ALASKA = ExtraDelightBlocks.BLOCKS
			.register("baked_alaska",
					() -> new RecipeFeastBlock(
							Block.Properties.copy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_WHITE), false,
							plate));
	public static final RegistryObject<Item> BAKED_ALASKA_ITEM = EDItemGenerator
			.register("baked_alaska_item", () -> new BlockItem(BAKED_ALASKA.get(), new Item.Properties()))
			.advancementFeast().finish();

	public static final RegistryObject<PicnicBasketBlock> WHITE_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"white_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.WHITE, Block.Properties.copy(Blocks.WHITE_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> ORANGE_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"orange_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.ORANGE, Block.Properties.copy(Blocks.ORANGE_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> MAGENTA_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"magenta_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.MAGENTA, Block.Properties.copy(Blocks.MAGENTA_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> LIGHT_BLUE_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"light_blue_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.LIGHT_BLUE, Block.Properties.copy(Blocks.LIGHT_BLUE_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> YELLOW_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"yellow_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.YELLOW, Block.Properties.copy(Blocks.YELLOW_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> LIME_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"lime_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.LIME, Block.Properties.copy(Blocks.LIME_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> PINK_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"pink_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.PINK, Block.Properties.copy(Blocks.PINK_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> GRAY_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"gray_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.GRAY, Block.Properties.copy(Blocks.GRAY_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> LIGHT_GRAY_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"light_gray_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.LIGHT_GRAY, Block.Properties.copy(Blocks.LIGHT_GRAY_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> CYAN_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"cyan_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.CYAN, Block.Properties.copy(Blocks.CYAN_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> BLUE_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"blue_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.BLUE, Block.Properties.copy(Blocks.BLUE_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> BROWN_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"brown_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.BROWN, Block.Properties.copy(Blocks.BROWN_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> GREEN_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"green_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.GREEN, Block.Properties.copy(Blocks.GREEN_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> RED_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"red_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.RED, Block.Properties.copy(Blocks.RED_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> BLACK_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"black_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.BLACK, Block.Properties.copy(Blocks.BLACK_WOOL)));
	public static final RegistryObject<PicnicBasketBlock> PURPLE_PICNIC_BASKET = ExtraDelightBlocks.BLOCKS.register(
			"purple_picnic_basket",
			() -> new PicnicBasketBlock(DyeColor.PURPLE, Block.Properties.copy(Blocks.PURPLE_WOOL)));

	public static final RegistryObject<Item> WHITE_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("white_picnic_basket", () -> new BlockItem(SummerCitrus.WHITE_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> ORANGE_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("orange_picnic_basket", () -> new BlockItem(SummerCitrus.ORANGE_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> MAGENTA_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("magenta_picnic_basket", () -> new BlockItem(SummerCitrus.MAGENTA_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> LIGHT_BLUE_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("light_blue_picnic_basket", () -> new BlockItem(SummerCitrus.LIGHT_BLUE_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> YELLOW_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("yellow_picnic_basket", () -> new BlockItem(SummerCitrus.YELLOW_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> LIME_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("lime_picnic_basket", () -> new BlockItem(SummerCitrus.LIME_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> PINK_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("pink_picnic_basket", () -> new BlockItem(SummerCitrus.PINK_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> GRAY_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("gray_picnic_basket", () -> new BlockItem(SummerCitrus.GRAY_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> LIGHT_GRAY_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("light_gray_picnic_basket", () -> new BlockItem(SummerCitrus.LIGHT_GRAY_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> CYAN_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("cyan_picnic_basket", () -> new BlockItem(SummerCitrus.CYAN_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> BLUE_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("blue_picnic_basket", () -> new BlockItem(SummerCitrus.BLUE_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> BROWN_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("brown_picnic_basket", () -> new BlockItem(SummerCitrus.BROWN_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> GREEN_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("green_picnic_basket", () -> new BlockItem(SummerCitrus.GREEN_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> RED_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("red_picnic_basket", () -> new BlockItem(SummerCitrus.RED_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> BLACK_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("black_picnic_basket", () -> new BlockItem(SummerCitrus.BLACK_PICNIC_BASKET.get(),
					stack1Item()));
	public static final RegistryObject<Item> PURPLE_PICNIC_BASKET_ITEM = ExtraDelightItems.ITEMS
			.register("purple_picnic_basket", () -> new BlockItem(SummerCitrus.PURPLE_PICNIC_BASKET.get(),
					stack1Item()));

    /*
	public static final RegistryObject<Item> CITRUS_PATTERN_ITEM = ExtraDelightItems.ITEMS.register(
			"citrus_rind_banner_item",
			() -> new BannerPatternItem(ExtraDelightBanners.CITRUS_PATTERN_TAG, stack1Item()));

     */

}
