package com.vomiter.extradelight.compat.jei;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.registry.ExtraDelightContainers;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import com.vomiter.extradelight.common.complex.workstations.chiller.ChillerMenu;
import com.vomiter.extradelight.common.complex.workstations.chiller.ChillerScreen;
import com.vomiter.extradelight.common.complex.workstations.vat.VatMenu;
import com.vomiter.extradelight.common.complex.workstations.vat.VatScreen;
import com.vomiter.extradelight.compat.jei.categories.*;
import com.vomiter.extradelight.common.complex.workstations.mixingbowl.MixingBowlMenu;
import com.vomiter.extradelight.common.complex.workstations.mixingbowl.MixingBowlScreen;
import com.vomiter.extradelight.common.complex.workstations.oven.OvenMenu;
import com.vomiter.extradelight.common.complex.workstations.oven.OvenScreen;
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
                new JuicerRecipeCategory(registry.getJeiHelpers().getGuiHelper()));

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