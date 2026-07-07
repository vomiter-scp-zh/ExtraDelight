package com.vomiter.extradelight.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vomiter.extradelight.registry.ExtraDelightFluids;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.common.fluids.SizedFluidIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidStack;
import vectorwing.farmersdelight.common.registry.ModItems;

public class BottleFluidRegistry {
	private static int bottleMB = 250;

	public static List<BottleFluid> registry = new ArrayList<BottleFluid>();

	public static void register(Ingredient bottle, SizedFluidIngredient fluid) {
		registry.add(new BottleFluid(bottle, fluid));
	}

	public static ItemStack getBottleFromFluid(FluidStack f) {
		if (f.getAmount() >= bottleMB) {
			Optional<BottleFluid> b = registry.stream().filter(bf -> bf.fluid.test(f)).findFirst();
			if (b.isPresent()) {
				return b.get().bottle.getItems()[0].copy();
			}
		}
		return ItemStack.EMPTY;
	}

	public static ItemStack getBottleFromFluidWithoutSize(Fluid f) {
		Optional<BottleFluid> b = registry.stream().filter(bf -> bf.fluid.test(new FluidStack(f, 1000))).findFirst();
		if (b.isPresent()) {
			return b.get().bottle.getItems()[0].copy();
		}
		return ItemStack.EMPTY;
	}

	public static FluidStack getFluidFromBottle(ItemStack i) {
		Optional<BottleFluid> b = registry.stream().filter(bf -> bf.bottle.test(i)).findFirst();
		if (b.isPresent()) {
			return b.get().fluid.getFluids()[0];
		}
		return FluidStack.EMPTY;
	}

    /*
	public static void createRecipesForJEI(RecipeOutput consumer) {
		registry.forEach(bf -> {
			new BottleFluidRegistryRecipeBuilder(bf.bottle, bf.fluid).save(consumer,
					bf.fluid.getFluids()[0].getFluid());
		});
	}

     */

	public static class BottleFluid {
		public Ingredient bottle;
		public SizedFluidIngredient fluid;

		public BottleFluid(Ingredient b, SizedFluidIngredient f) {
			this.bottle = b;
			this.fluid = f;
		}

	}

	static {
        
		register(Ingredient.of(ModItems.APPLE_CIDER.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.APPLE_CIDER.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.BBQ_SAUCE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.BBQ.FLUID.get(), bottleMB));
//		register(Ingredient.of(ExtraDelightItems.BLOOD_CHOCOLATE_SYRUP_BOTTLE.get()),
//				SizedFluidIngredient.ofFluid(ExtraDelightFluids.BLOOD_CHOCOLATE_SYRUP.FLUID.get(), bottleMB));
		register(Ingredient.of(ModItems.BONE_BROTH.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.BROTH.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.CACTUS_JUICE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.CACTUS_JUICE.FLUID.get(), bottleMB));
//		register(Ingredient.of(ExtraDelightItems.CARAMEL_SAUCE.get()),
//				SizedFluidIngredient.ofFluid(ExtraDelightFluids.CARAMEL_SAUCE.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.COCOA_BUTTER_BOTTLE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.COCOA_BUTTER.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.COFFEE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.COFFEE.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.COOKING_OIL.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.OIL.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.DARK_CHOCOLATE_SYRUP_BOTTLE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.DARK_CHOCOLATE_SYRUP.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.EGG_MIX.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.EGG_MIX.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.EGG_WHITE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.EGG_WHITE.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.GLOW_BERRY_JUICE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.GLOW_BERRY_JUICE.FLUID.get(), bottleMB));
//		register(Ingredient.of(SummerCitrus.GRAPEFRUIT_JUICE.get()),
//				SizedFluidIngredient.ofFluid(ExtraDelightFluids.GRAPEFRUIT_JUICE.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.GRAVY.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.GRAVY.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.HAZELNUT_SPREAD_BOTTLE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.COCOA_NUT_BUTTER_SPREAD.FLUID.get(), bottleMB));
		register(Ingredient.of(ModItems.HOT_COCOA.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.HOT_COCOA.FLUID.get(), bottleMB));
//		register(
//				DataComponentIngredient.of(false, ExtraDelightComponents.DYNAMIC_FOOD,
//						new DynamicItemComponent(List.of("sweet_berries")), ExtraDelightItems.DYNAMIC_JAM.get()),
//				SizedFluidIngredient.ofFluid(ExtraDelightFluids.JAM.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.KETCHUP.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.KETCHUP.FLUID.get(), bottleMB));
//		register(Ingredient.of(SummerCitrus.LEMON_JUICE.get()),
//				SizedFluidIngredient.ofFluid(ExtraDelightFluids.LEMON_JUICE.FLUID.get(), bottleMB));
//		register(Ingredient.of(SummerCitrus.LIME_JUICE.get()),
//				SizedFluidIngredient.ofFluid(ExtraDelightFluids.LIME_JUICE.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.MARSHMALLOW_FLUFF_BOTTLE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.MARSHMALLOW_FLUFF.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.MAYO.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.MAYO.FLUID.get(), bottleMB));
		register(Ingredient.of(ModItems.MELON_JUICE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.MELON_JUICE.FLUID.get(), bottleMB));
		register(Ingredient.of(ModItems.MILK_BOTTLE.get()), SizedFluidIngredient.ofFluid(ForgeMod.MILK.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.MILK_CHOCOLATE_SYRUP_BOTTLE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.MILK_CHOCOLATE_SYRUP.FLUID.get(), bottleMB));
//		register(Ingredient.of(ExtraDelightItems.MILKSHAKE.get()),
//				SizedFluidIngredient.ofFluid(ExtraDelightFluids.MILKSHAKE.FLUID.get(), bottleMB));
//		register(Ingredient.of(SummerCitrus.ORANGE_JUICE.get()),
//				SizedFluidIngredient.ofFluid(ExtraDelightFluids.ORANGE_JUICE.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.PEANUT_BUTTER_BOTTLE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.NUT_BUTTER.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.SWEET_BERRY_JUICE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.SWEET_BERRY_JUICE.FLUID.get(), bottleMB));
//		register(Ingredient.of(ExtraDelightItems.TEA.get()),
//				SizedFluidIngredient.ofFluid(ExtraDelightFluids.TEA.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.TOMATO_JUICE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.TOMATO_JUICE.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.VINEGAR.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.VINEGAR.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.WHIPPED_CREAM.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.WHIPPED_CREAM.FLUID.get(), bottleMB));
		register(Ingredient.of(ExtraDelightItems.WHITE_CHOCOLATE_SYRUP_BOTTLE.get()),
				SizedFluidIngredient.ofFluid(ExtraDelightFluids.WHITE_CHOCOLATE_SYRUP.FLUID.get(), bottleMB));
//		register(Ingredient.of(Fermentation.PICKLE_JUICE.get()),
//				SizedFluidIngredient.ofFluid(ExtraDelightFluids.PICKLE_JUICE.FLUID.get(), bottleMB));

		// If we just use Items.POTION we get an item called Uncraftable Potion instead
		// of Water Bottle
//		register(Ingredient.of(PotionContents.createItemStack(Items.POTION, Potions.WATER)),
//				SizedFluidIngredient.ofFluid(Fluids.WATER, bottleMB));

  	}
}