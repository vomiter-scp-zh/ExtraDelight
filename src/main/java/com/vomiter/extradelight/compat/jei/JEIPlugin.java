package com.vomiter.extradelight.compat.jei;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.registry.*;
import com.vomiter.extradelight.common.complex.workstations.chiller.ChillerMenu;
import com.vomiter.extradelight.common.complex.workstations.chiller.ChillerScreen;
import com.vomiter.extradelight.common.complex.workstations.vat.VatMenu;
import com.vomiter.extradelight.common.complex.workstations.vat.VatScreen;
import com.vomiter.extradelight.compat.jei.categories.*;
import com.vomiter.extradelight.common.complex.workstations.mixingbowl.MixingBowlMenu;
import com.vomiter.extradelight.common.complex.workstations.mixingbowl.MixingBowlScreen;
import com.vomiter.extradelight.common.complex.workstations.oven.OvenMenu;
import com.vomiter.extradelight.common.complex.workstations.oven.OvenScreen;
import mezz.jei.api.constants.VanillaTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
	private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID, "main");

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(
                new OvenRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new OvenBulkRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new MixingBowlRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new DoughShapingRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new MortarRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new DryingRackRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new MeltingPotRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new ChillerRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new VatRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new EvaporatorRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new JuicerRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new FeastRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new BottleFluidRegistryCategory(registry.getJeiHelpers().getGuiHelper()),
                new ToolOnBlockRecipeCatagory(registry.getJeiHelpers().getGuiHelper())
                );

    }

	@Override
	public void registerRecipes(@NotNull IRecipeRegistration registry) {
        registry.addRecipes(OvenRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.OVEN.get()).stream().filter(recipe -> !recipe.getId().getPath().contains("bulk")).toList());
        registry.addRecipes(OvenBulkRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.OVEN.get()).stream().filter(recipe -> recipe.getId().getPath().contains("bulk")).toList());
        registry.addRecipes(MixingBowlRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.MIXING_BOWL.get()).stream().toList());
        registry.addRecipes(DoughShapingRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.DOUGH_SHAPING.get()).stream().toList());
        registry.addRecipes(MortarRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.MORTAR.get()).stream().toList());
        registry.addRecipes(DryingRackRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.DRYING_RACK.get()).stream().toList());
        registry.addRecipes(MeltingPotRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.MELTING_POT.get()).stream().toList());
        registry.addRecipes(ChillerRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.CHILLER.get()).stream().toList());
        registry.addRecipes(VatRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.VAT.get()).stream().toList());
        registry.addRecipes(EvaporatorRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.EVAPORATOR.get()).stream().toList());
        registry.addRecipes(JuicerRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.JUICER.get()).stream().toList());
        registry.addRecipes(FeastRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.FEAST.get()).stream().toList());
        registry.addRecipes(BottleFluidRegistryCategory.TYPE,
                Minecraft.getInstance().level.getRecipeManager()
                        .getAllRecipesFor(ExtraDelightRecipes.BOTTLE_FLUID_REGISTRY.get()).stream()
                        .toList());
        registry.addRecipes(ToolOnBlockRecipeCatagory.TYPE, Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(ExtraDelightRecipes.TOOL_ON_BLOCK.get()).stream().toList());

        registry.addIngredientInfo(new ItemStack(ExtraDelightItems.MINT.get()), VanillaTypes.ITEM_STACK,
                Component.translatable(ExtraDelight.MOD_ID + ".jei.info.mint"));
        registry.addIngredientInfo(
                List.of(new ItemStack(ExtraDelightItems.CINNAMON_SAPLING.get()),
                        new ItemStack(ExtraDelightItems.CINNAMON_LOG.get()),
                        new ItemStack(ExtraDelightItems.CINNAMON_BARK.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.cinnamon"));
        registry.addIngredientInfo(
                List.of(new ItemStack(ExtraDelightItems.WILD_GINGER.get()),
                        new ItemStack(ExtraDelightItems.GINGER.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.ginger"));
        registry.addIngredientInfo(
                List.of(new ItemStack(ExtraDelightItems.CORN_SEEDS.get()),
                        new ItemStack(ExtraDelightItems.UNSHUCKED_CORN.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.corn"));
        registry.addIngredientInfo(List.of(new ItemStack(ExtraDelightItems.WILD_CHILI_BLOCK.get()),
                        new ItemStack(ExtraDelightItems.CHILI.get()), new ItemStack(ExtraDelightItems.CHILI_SEEDS.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.chili"));
        registry.addIngredientInfo(
                List.of(new ItemStack(ExtraDelightItems.WILD_MALLOW_ROOT_BLOCK.get()),
                        new ItemStack(ExtraDelightItems.MALLOW_ROOT.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.mallow"));
        registry.addIngredientInfo(
                List.of(new ItemStack(ExtraDelightItems.WILD_PEANUT_BLOCK.get()),
                        new ItemStack(ExtraDelightItems.PEANUTS_IN_SHELL.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.peanut"));
        registry.addIngredientInfo(List.of(new ItemStack(ExtraDelightItems.COFFEE_CHERRIES.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.coffee"));
        registry.addIngredientInfo(
                List.of(new ItemStack(ExtraDelightItems.HAZELNUT_SAPLING.get()),
                        new ItemStack(ExtraDelightItems.HAZELNUTS_IN_SHELL.get()),
                        new ItemStack(ExtraDelightItems.HAZELNUT_LEAVES.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.hazelnut"));
        registry.addIngredientInfo(
                List.of(new ItemStack(ExtraDelightItems.APPLE_SAPLING.get()), new ItemStack(Items.APPLE),
                        new ItemStack(ExtraDelightItems.APPLE_LEAVES.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.apple"));
        registry.addIngredientInfo(
                List.of(new ItemStack(ExtraDelightItems.WILD_GARLIC_BLOCK.get()),
                        new ItemStack(ExtraDelightItems.GARLIC.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.garlic"));
        registry.addIngredientInfo(
                List.of(new ItemStack(Fermentation.WILD_CUCUMBER.get()), new ItemStack(Fermentation.CUCUMBER.get()),
                        new ItemStack(Fermentation.CUCUMBER_SEED.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.cucumber"));
        registry.addIngredientInfo(
                List.of(new ItemStack(Fermentation.WILD_SOYBEAN.get()), new ItemStack(Fermentation.SOYBEAN_POD.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.soybean"));
        registry.addIngredientInfo(new ItemStack(Fermentation.PICKLE_JUICE.get()), VanillaTypes.ITEM_STACK,
                Component.translatable(ExtraDelight.MOD_ID + ".jei.info.pickle_juice"));
        registry.addIngredientInfo(
                List.of(new ItemStack(ExtraDelightItems.YEAST.get()), new ItemStack(ExtraDelightItems.YEAST_POT.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.yeast"));
        registry.addIngredientInfo(
                List.of(new ItemStack(SummerCitrus.LEMON_SAPLING.get()), new ItemStack(SummerCitrus.LEMON.get()),
                        new ItemStack(SummerCitrus.LEMON_LEAVES.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.lemon"));
        registry.addIngredientInfo(
                List.of(new ItemStack(SummerCitrus.LIME_SAPLING.get()), new ItemStack(SummerCitrus.LIME.get()),
                        new ItemStack(SummerCitrus.LIME_LEAVES.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.lime"));
        registry.addIngredientInfo(
                List.of(new ItemStack(SummerCitrus.ORANGE_SAPLING.get()), new ItemStack(SummerCitrus.ORANGE.get()),
                        new ItemStack(SummerCitrus.ORANGE_LEAVES.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.orange"));
        registry.addIngredientInfo(List.of(new ItemStack(SummerCitrus.GRAPEFRUIT_SAPLING.get()),
                        new ItemStack(SummerCitrus.GRAPEFRUIT.get()), new ItemStack(SummerCitrus.GRAPEFRUIT_LEAVES.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.grapefruit"));
        registry.addIngredientInfo(
                List.of(new ItemStack(ExtraDelightItems.EGG_YOLK.get()), new ItemStack(ExtraDelightItems.EGG_WHITE.get()),
                        new ItemStack(Items.EGG)),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.egg"));
        registry.addIngredientInfo(
                List.of(new ItemStack(SummerCitrus.RAW_BAKED_ALASKA_ITEM.get()),
                        new ItemStack(SummerCitrus.BAKED_ALASKA_ITEM.get())),
                VanillaTypes.ITEM_STACK, Component.translatable(ExtraDelight.MOD_ID + ".jei.info.baked_alaska"));

        List<ItemStack> hide = List.of(ExtraDelightItems.EASTER_EGG.get().getDefaultInstance());
        registry.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, hide);

    }

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(ExtraDelightItems.OVEN.get(), OvenRecipeCategory.TYPE);
        registry.addRecipeCatalyst(ExtraDelightItems.OVEN.get(), OvenBulkRecipeCategory.TYPE);
        registry.addRecipeCatalyst(ExtraDelightItems.MIXING_BOWL.get(), MixingBowlRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.WOODEN_SPOON.get()), MixingBowlRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.STONE_SPOON.get()), MixingBowlRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.IRON_SPOON.get()), MixingBowlRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.GOLD_SPOON.get()), MixingBowlRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.DIAMOND_SPOON.get()), MixingBowlRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.NETHERITE_SPOON.get()),
                MixingBowlRecipeCategory.TYPE);
        registry.addRecipeCatalyst(ExtraDelightItems.DOUGH_SHAPING.get().getDefaultInstance(), DoughShapingRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_AMETHYST.get()), MortarRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_ANDESITE.get()), MortarRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_BASALT.get()), MortarRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_BLACKSTONE.get()), MortarRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_DEEPSLATE.get()), MortarRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_DIORITE.get()), MortarRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_ENDSTONE.get()), MortarRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_GILDED_BLACKSTONE.get()),
                MortarRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_GRANITE.get()), MortarRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_STONE.get()), MortarRecipeCategory.TYPE);

        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.MORTAR_STONE.get()), MortarRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.DRYING_RACK.get()), DryingRackRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.MELTING_POT.get()),
                MeltingPotRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.CHILLER.get()), ChillerRecipeCategory.TYPE);

        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.VAT.get()), VatRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.LID.get()), VatRecipeCategory.TYPE);
        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.EVAPORATOR.get()),
                EvaporatorRecipeCategory.TYPE);

        registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.JUICER.get()), JuicerRecipeCategory.TYPE);


    }

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(OvenMenu.class, ExtraDelightContainers.OVEN_MENU.get(),
				OvenRecipeCategory.TYPE, 0, 6, 9, 36);
        registration.addRecipeTransferHandler(OvenMenu.class, ExtraDelightContainers.OVEN_MENU.get(),
                OvenBulkRecipeCategory.TYPE, 0, 6, 9, 36);
        registration.addRecipeTransferHandler(MixingBowlMenu.class, ExtraDelightContainers.MIXING_BOWL_MENU.get(),
                MixingBowlRecipeCategory.TYPE, 0, 6, 9, 36);
        registration.addRecipeTransferHandler(ChillerMenu.class, ExtraDelightContainers.CHILLER_MENU.get(),
                ChillerRecipeCategory.TYPE, 0, 6, 9, 36);
        registration.addRecipeTransferHandler(VatMenu.class, ExtraDelightContainers.VAT_MENU.get(),
                VatRecipeCategory.TYPE, 0, 6, 9, 36);

	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(OvenScreen.class, 90, 36, 22, 22, OvenRecipeCategory.TYPE);
        registration.addRecipeClickArea(OvenScreen.class, 90, 36, 22, 22, OvenBulkRecipeCategory.TYPE);
        registration.addRecipeClickArea(MixingBowlScreen.class, 122, 23, 20, 18, MixingBowlRecipeCategory.TYPE);
        registration.addRecipeClickArea(ChillerScreen.class, 101, 42, 22, 15, ChillerRecipeCategory.TYPE);
        registration.addRecipeClickArea(VatScreen.class, 62, 50 - 19, 87, 18, VatRecipeCategory.TYPE);

    }

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
	}

}