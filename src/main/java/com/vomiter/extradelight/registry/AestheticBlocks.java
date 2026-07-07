package com.vomiter.extradelight.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.vomiter.extradelight.common.aethetics.StepStoolBlock;
import com.vomiter.extradelight.common.complex.cabinet.countercabinet.CounterCabinetBlock;
import com.vomiter.extradelight.common.blocks.HalfCabinetBlock;
import com.vomiter.extradelight.common.complex.displays.food.FoodDisplayBlock;
import com.vomiter.extradelight.common.complex.displays.knife.KnifeBlock;
import com.vomiter.extradelight.common.complex.displays.spice.SpiceRackBlock;
import com.vomiter.extradelight.common.complex.displays.wreath.WreathBlock;
import com.vomiter.extradelight.common.complex.cabinet.sink.SinkCabinetBlock;
import com.vomiter.extradelight.common.items.HelmetBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.aethetics.MoldingBlock;
import com.vomiter.extradelight.common.aethetics.RibbonBlock;
import com.vomiter.extradelight.common.complex.cornhuskdoll.CornHuskDollBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class AestheticBlocks {
//	public static final CreativeModeTab AESTHETIC_TAB = new CreativeModeTab("extradelight.aesthetic") {
//		@Override
//		public ItemStack makeIcon() {
//			return new ItemStack(SPICE_RACKS_FULL.get(0).get());
//		}
//	};

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExtraDelight.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExtraDelight.MOD_ID);

	public static enum WOOD {
		oak, dark_oak, spruce, birch, jungle, acacia, crimson, warped, mangrove, cinnamon, cherry, bamboo, fruit
	};

	public static enum EXTRA_LEAVES {
		azalea, flowering_azalea, hazelnut, apple, lemon, lime, orange, grapefruit
	};

	public static final List<RegistryObject<Block>> STEP_STOOLS = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Block>> SPICE_RACKS = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Block>> SPICE_RACKS_FULL = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Block>> KNIFE_BLOCKS = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Block>> CABINETS = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Block>> COUNTER_CABINETS = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Block>> FOOD_DISPLAY = new ArrayList<RegistryObject<Block>>();

	public static final List<RegistryObject<Block>> WALLPAPER_BLOCKS = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Block>> MOLDED_WALLPAPER_BLOCKS = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Block>> GINGHAM_BLOCKS = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Block>> GINGHAM_CARPET_BLOCKS = new ArrayList<RegistryObject<Block>>();

	public static final List<RegistryObject<Item>> STEP_STOOL_ITEMS = new ArrayList<RegistryObject<Item>>();
	public static final List<RegistryObject<Item>> SPICE_RACKS_ITEMS = new ArrayList<RegistryObject<Item>>();
	public static final List<RegistryObject<Item>> SPICE_RACKS_FULL_ITEMS = new ArrayList<RegistryObject<Item>>();
	public static final List<RegistryObject<Item>> KNIFE_BLOCK_ITEMS = new ArrayList<RegistryObject<Item>>();
	public static final List<RegistryObject<Item>> CABINET_ITEMS = new ArrayList<RegistryObject<Item>>();
	public static final List<RegistryObject<Item>> COUNTER_CABINET_ITEMS = new ArrayList<RegistryObject<Item>>();
	public static final List<RegistryObject<Item>> FOOD_DISPLAY_ITEMS = new ArrayList<RegistryObject<Item>>();

	public static final List<RegistryObject<Item>> WALLPAPER_ITEMS = new ArrayList<RegistryObject<Item>>();
	public static final List<RegistryObject<Item>> MOLDED_WALLPAPER_ITEMS = new ArrayList<RegistryObject<Item>>();
	public static final List<RegistryObject<Item>> GINGHAM_ITEMS = new ArrayList<RegistryObject<Item>>();
	public static final List<RegistryObject<Item>> GINGHAM_CARPET_ITEMS = new ArrayList<RegistryObject<Item>>();

	public static final RegistryObject<CornHuskDollBlock> CORN_HUSK_DOLL = BLOCKS.register("corn_husk_doll",
            CornHuskDollBlock::new);
	public static final RegistryObject<BlockItem> CORN_HUSK_DOLL_ITEM = ITEMS.register("corn_husk_doll",
			() -> new BlockItem(CORN_HUSK_DOLL.get(), new Item.Properties()));

	public static final List<RegistryObject<Block>> DRIED_CORN_FENCE = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Item>> DRIED_CORN_FENCE_ITEMS = new ArrayList<RegistryObject<Item>>();

	public static final List<RegistryObject<Block>> WREATHS = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Item>> WREATH_ITEMS = new ArrayList<RegistryObject<Item>>();

	public static final List<RegistryObject<Block>> BOWS = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Item>> BOW_ITEMS = new ArrayList<RegistryObject<Item>>();

	public static final List<RegistryObject<Block>> SINKS = new ArrayList<RegistryObject<Block>>();
	public static final List<RegistryObject<Item>> SINK_ITEMS = new ArrayList<RegistryObject<Item>>();

	public static Block[] getRegistryListAsBlocks(List<RegistryObject<Block>> blocks) {
		List<Block> l = new ArrayList<Block>();

		for (RegistryObject<Block> b : blocks) {
			l.add(b.get());
		}
		Block[] a = l.toArray(Block[]::new);

		return a;
	}

	public static Item[] getRegistryListAsItems(List<RegistryObject<Item>> items) {
		List<Item> l = new ArrayList<Item>();

		for (RegistryObject<Item> b : items) {
			l.add(b.get());
		}
		Item[] a = l.toArray(Item[]::new);

		return a;
	}

	public static void registerMoldedWallpaper(String name, Supplier<? extends Block> block,
			List<RegistryObject<Block>> blocks, List<RegistryObject<Item>> items) {
		for (WOOD w : WOOD.values()) {
			for (DyeColor d : DyeColor.values()) {
				RegistryObject<Block> b = BLOCKS.register(w.toString() + "_molded_" + d.getName() + "_" + name, block);
				RegistryObject<Item> t = ITEMS.register(w.toString() + "_molded_" + d.getName() + "_" + name,
						() -> new BlockItem(b.get(), new Item.Properties()));

				blocks.add(b);
				items.add(t);
				// tag(Tags.Blocks.DYED_BLACK).add(b);
				// tag(Tags.Items.DYED_BLACK).add(t);
			}
		}
	}

	public static void registerAllWood(String name, Supplier<? extends Block> block, List<RegistryObject<Block>> blocks,
			List<RegistryObject<Item>> items) {
		for (WOOD w : WOOD.values()) {
			RegistryObject<Block> b = BLOCKS.register(w.toString() + "_" + name, block);
			RegistryObject<Item> t = ITEMS.register(w.toString() + "_" + name,
					() -> new BlockItem(b.get(), new Item.Properties()));

			blocks.add(b);
			items.add(t);
		}
	}

	public static void registerAllColors(String name, Supplier<? extends Block> block,
			List<RegistryObject<Block>> blocks, List<RegistryObject<Item>> items) {
		for (DyeColor w : DyeColor.values()) {
			RegistryObject<Block> b = BLOCKS.register(name + "_" + w.getName(), block);
			RegistryObject<Item> t = ITEMS.register(name + "_" + w.toString(),
					() -> new BlockItem(b.get(), new Item.Properties()));

			blocks.add(b);
			items.add(t);
			// tag(Tags.Blocks.DYED_BLACK).add(b);
			// tag(Tags.Items.DYED_BLACK).add(t);
		}
	}

	public static void registerAllWoodHelm(String name, Supplier<? extends Block> block,
			List<RegistryObject<Block>> blocks, List<RegistryObject<Item>> items) {
		for (WOOD w : WOOD.values()) {
			if (w.toString() != "fruit" && w.toString() != "bamboo") {
				RegistryObject<Block> b = BLOCKS.register(w.toString() + "_" + name, block);
				RegistryObject<Item> t = ITEMS.register(w.toString() + "_" + name,
						() -> new HelmetBlockItem(b.get(), new Item.Properties()));

				blocks.add(b);
				items.add(t);
			}
		}
		for (EXTRA_LEAVES f : EXTRA_LEAVES.values()) {
			RegistryObject<Block> b = BLOCKS.register(f.toString() + "_" + name, block);
			RegistryObject<Item> t = ITEMS.register(f.toString() + "_" + name,
					() -> new HelmetBlockItem(b.get(), new Item.Properties()));

			blocks.add(b);
			items.add(t);
		}
	}

	public static void registerAllColorsHelm(String name, Supplier<? extends Block> block,
			List<RegistryObject<Block>> blocks, List<RegistryObject<Item>> items) {
		for (DyeColor w : DyeColor.values()) {
			RegistryObject<Block> b = BLOCKS.register(name + "_" + w.getName(), block);
			RegistryObject<Item> t = ITEMS.register(name + "_" + w.toString(),
					() -> new HelmetBlockItem(b.get(), new Item.Properties()));

			blocks.add(b);
			items.add(t);
		}
	}

	public static void setup() {
		registerAllWood("step_stool", StepStoolBlock::new, STEP_STOOLS, STEP_STOOL_ITEMS);

		registerAllWood("spice_rack", SpiceRackBlock::new, SPICE_RACKS, SPICE_RACKS_ITEMS);
		registerAllWood("spice_rack_full", SpiceRackBlock::new, SPICE_RACKS_FULL, SPICE_RACKS_FULL_ITEMS);
		registerAllWood("knife_block", KnifeBlock::new, KNIFE_BLOCKS, KNIFE_BLOCK_ITEMS);
		registerAllWood("half_cabinet", HalfCabinetBlock::new, CABINETS, CABINET_ITEMS);
		registerAllWood("food_display", FoodDisplayBlock::new, FOOD_DISPLAY, FOOD_DISPLAY_ITEMS);
		registerAllWood("sink", SinkCabinetBlock::new, SINKS, SINK_ITEMS);
		registerAllWood("counter_cabinet", CounterCabinetBlock::new, COUNTER_CABINETS, COUNTER_CABINET_ITEMS);

		registerAllColors("wallpaper",
				() -> new Block(Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.GRASS)),
				WALLPAPER_BLOCKS, WALLPAPER_ITEMS);
		registerMoldedWallpaper("wallpaper",
				() -> new MoldingBlock(
						Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.GRASS)),
				MOLDED_WALLPAPER_BLOCKS, MOLDED_WALLPAPER_ITEMS);

		registerAllWood("dried_corn_fence", () -> new FenceBlock(Properties.copy(Blocks.ACACIA_FENCE)),
				DRIED_CORN_FENCE, DRIED_CORN_FENCE_ITEMS);

		registerAllWoodHelm("wreath", WreathBlock::new, WREATHS, WREATH_ITEMS);

		registerAllColorsHelm("ribbon_bow",
				() -> new RibbonBlock(Properties.copy(Blocks.BLACK_WOOL).noOcclusion().noCollission()), BOWS,
				BOW_ITEMS);

		registerAllColors("gingham", () -> new Block(Properties.copy(Blocks.BLACK_WOOL)), GINGHAM_BLOCKS,
				GINGHAM_ITEMS);
		registerAllColors("gingham_carpet", () -> new CarpetBlock(Properties.copy(Blocks.BLACK_CARPET)),
				GINGHAM_CARPET_BLOCKS, GINGHAM_CARPET_ITEMS);
	}

	// oak, dark_oak, spruce, birch, jungle, acacia, crimson, warped, mangrove,
	// cherry, bamboo
	// cinnamon, fruit
}
