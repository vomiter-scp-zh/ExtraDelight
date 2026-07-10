package com.vomiter.extradelight.common.complex.workstations.chiller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.vomiter.extradelight.registry.ExtraDelightItems;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;

import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class ChillerRecipe implements Recipe<ChillerRecipeWrapper> {
	public static final int INPUT_SLOTS = 4;

    private final ResourceLocation id;
	private final String group;
//	private final ChillerRecipeBookTab tab;
	private final NonNullList<Ingredient> inputItems;
	private final FluidStack fluid;
	public final ItemStack output;
	private final ItemStack container;
	private final float experience;
	private final int cookTime;
	public final boolean consumeContainer;

	public ChillerRecipe(ResourceLocation id, String group, NonNullList<Ingredient> inputItems, FluidStack inputFluid, ItemStack output,
			ItemStack container, float experience, int cookTime) {
		this.id = id;
        this.group = group;
		this.fluid = inputFluid;
		this.inputItems = inputItems;
		this.output = output;

		if (!container.isEmpty()) {
			this.container = container;
		} else {
			this.container = ItemStack.EMPTY;
		}

		this.experience = experience;
		this.cookTime = cookTime;
		this.consumeContainer = false;
	}

	public ChillerRecipe(ResourceLocation id, String group, NonNullList<Ingredient> inputItems, FluidStack inputFluid, ItemStack output,
                         ItemStack container, float experience, int cookTime, boolean consumeContainer) {
        this.id = id;
        this.group = group;
		this.fluid = inputFluid;
		this.inputItems = inputItems;
		this.output = output;

		if (!container.isEmpty()) {
			this.container = container;
		} else {
			this.container = ItemStack.EMPTY;
		}

		this.experience = experience;
		this.cookTime = cookTime;
		this.consumeContainer = consumeContainer;
	}

	public ItemStack getContainerOverride() {
		return this.container;
	}

	@Override
	public String getGroup() {
		return this.group;
	}

//	@Nullable
//	public ChillerRecipeBookTab getRecipeBookTab() {
//		return this.tab;
//	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.inputItems;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registries) {
		return this.output.copy();
	}

	public ItemStack getOutputContainer() {
		return this.container;
	}

	@Override
	public ItemStack assemble(ChillerRecipeWrapper input, RegistryAccess registries) {
		return this.output.copy();
	}

	public float getExperience() {
		return this.experience;
	}

	public int getCookTime() {
		return this.cookTime;
	}

	public boolean shouldConsumeContainer() {
		return this.consumeContainer;
	}

	@Override
	public boolean matches(ChillerRecipeWrapper inv, Level level) {
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		int i = 0;

		for (int j = 0; j < INPUT_SLOTS; ++j) {
			ItemStack itemstack = inv.getItem(j);
			if (!itemstack.isEmpty()) {
				++i;
				inputs.add(itemstack);
			}
		}

		int[] matches = RecipeMatcher.findMatches(inputs, this.inputItems);
		return i == this.inputItems.size() && matches != null
				&& inv.getItem(INPUT_SLOTS + 1).getItem() == this.container.getItem()
				&& inv.getItem(INPUT_SLOTS + 1).getCount() >= this.container.getCount()
				&& inv.tank.containsFluid(this.fluid);
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= this.inputItems.size();
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.CHILLER_SERIALIZER.get();
	}

	@Override
	public RecipeType<?> getType() {
		return ExtraDelightRecipes.CHILLER.get();
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(ExtraDelightItems.CHILLER.get());
	}

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public FluidStack getFluid() {
		return fluid;
	}

    public static class Serializer implements RecipeSerializer<ChillerRecipe> {
        public Serializer() {
        }

        @Override
        public ChillerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");

            NonNullList<Ingredient> ingredients = readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));

            FluidStack fluid = FluidStack.EMPTY;
            if (json.has("fluid") && !json.get("fluid").isJsonNull()) {
                JsonObject fluidJson = GsonHelper.getAsJsonObject(json, "fluid");
                if (fluidJson.has("id")){
                    fluid = readFluidStack(fluidJson);
                }
            }

            ItemStack output = ShapedRecipe.itemStackFromJson(JsonStackTransformer.addItemForIdFormat(GsonHelper.getAsJsonObject(json, "result")));

            ItemStack container = ItemStack.EMPTY;
            if (json.has("container") && !json.get("container").isJsonNull()) {
                JsonObject containerJson = JsonStackTransformer.addItemForIdFormat(GsonHelper.getAsJsonObject(json, "container"));
                if (containerJson.has("item")) {
                    container = ShapedRecipe.itemStackFromJson(containerJson);
                }
            }

            float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);

            int cookTime = GsonHelper.getAsInt(json, "cookingtime", 200);

            boolean consumeContainer = GsonHelper.getAsBoolean(json, "consumeContainer");

            return new ChillerRecipe(
                    recipeId,
                    group,
                    ingredients,
                    fluid,
                    output,
                    container,
                    experience,
                    cookTime,
                    consumeContainer
            );
        }

        @Override
        public @Nullable ChillerRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();

            int ingredientCount = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientCount, Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); ++i) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }

            FluidStack fluid = FluidStack.readFromPacket(buffer);

            ItemStack output = buffer.readItem();

            ItemStack container = buffer.readItem();

            float experience = buffer.readFloat();

            int cookTime = buffer.readVarInt();

            boolean consumeContainer = buffer.readBoolean();

            return new ChillerRecipe(
                    recipeId,
                    group,
                    ingredients,
                    fluid,
                    output,
                    container,
                    experience,
                    cookTime,
                    consumeContainer
            );
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ChillerRecipe recipe) {
            buffer.writeUtf(recipe.group);

            buffer.writeVarInt(recipe.inputItems.size());

            for (Ingredient ingredient : recipe.inputItems) {
                ingredient.toNetwork(buffer);
            }

            recipe.fluid.writeToPacket(buffer);

            buffer.writeItem(recipe.output);

            buffer.writeItem(recipe.container);

            buffer.writeFloat(recipe.experience);

            buffer.writeVarInt(recipe.cookTime);

            buffer.writeBoolean(recipe.consumeContainer);
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray array) {
            NonNullList<Ingredient> ingredients = NonNullList.create();

            for (int i = 0; i < array.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(array.get(i));

                if (!ingredient.isEmpty()) {
                    ingredients.add(ingredient);
                }
            }

            return ingredients;
        }

        private static FluidStack readFluidStack(JsonObject json) {
            ResourceLocation fluidId = ResourceLocation.tryParse(GsonHelper.getAsString(json, "id"));
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidId);

            if (fluid == null || fluid == Fluids.EMPTY) {
                throw new JsonSyntaxException("Unknown fluid '" + fluidId + "'");
            }

            int amount = GsonHelper.getAsInt(json, "amount", 1000);
            FluidStack stack = new FluidStack(fluid, amount);

            if (json.has("nbt")) {
                try {
                    stack.setTag(TagParser.parseTag(GsonHelper.getAsString(json, "nbt")));
                } catch (CommandSyntaxException e) {
                    throw new JsonSyntaxException("Invalid fluid NBT: " + e.getMessage());
                }
            }

            return stack;
        }
    }
}
