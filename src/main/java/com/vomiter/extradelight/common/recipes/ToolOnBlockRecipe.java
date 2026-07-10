package com.vomiter.extradelight.common.recipes;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;

import com.vomiter.extradelight.util.JsonStackTransformer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ToolOnBlockRecipe implements Recipe<SimpleRecipeWrapper> {

    protected final ResourceLocation id;

	protected final Ingredient tool;

	public Ingredient getTool() {
		return tool;
	}

	public BlockItem getIn() {
		return in;
	}

	public BlockItem getOut() {
		return out;
	}

	protected final BlockItem in;
	protected final BlockItem out;

	public ToolOnBlockRecipe(ResourceLocation id, BlockItem in, Ingredient tool, BlockItem out) {
		this.tool = tool;
		this.in = in;
		this.out = out;
        this.id = id;
	}

	public ToolOnBlockRecipe(ResourceLocation id, ItemStack in, Ingredient tool, ItemStack out) {
		this.tool = tool;
		this.in = (BlockItem) in.getItem();
		this.out = (BlockItem) out.getItem();
        this.id = id;
	}

    public @NotNull ResourceLocation getId(){
        return id;
    }

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}

	public Block getResultBlock() {
		return out.getBlock();
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ExtraDelightRecipes.TOOL_ON_BLOCK_SERIALIZER.get();
	}

	@Override
	public RecipeType<?> getType() {
		return ExtraDelightRecipes.TOOL_ON_BLOCK.get();
	}

	@Override
	public boolean matches(SimpleRecipeWrapper input, Level level) {
		if (tool.test(input.getItem(0)))
			return this.in == input.getItem(1).getItem();
		return false;
	}

	@Override
	public ItemStack assemble(SimpleRecipeWrapper input, RegistryAccess registries) {
		// TODO Auto-generated method stub
		return new ItemStack(out);
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registries) {
		// TODO Auto-generated method stub
		return new ItemStack(out);
	}



    public static class Serializer implements RecipeSerializer<ToolOnBlockRecipe> {

        @Override
        public ToolOnBlockRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ItemStack inStack = ShapedRecipe.itemStackFromJson(
                    JsonStackTransformer.addItemForIdFormat(
                            GsonHelper.getAsJsonObject(json, "in")
                    )
            );
            if (!(inStack.getItem() instanceof BlockItem in)) {
                throw new JsonSyntaxException("'in' must be a BlockItem: " + inStack);
            }

            Ingredient tool = Ingredient.fromJson(json.get( "ingredient"));

            ItemStack outStack = ShapedRecipe.itemStackFromJson(JsonStackTransformer.addItemForIdFormat(GsonHelper.getAsJsonObject(json, "out")));
            if (!(outStack.getItem() instanceof BlockItem out)) {
                throw new JsonSyntaxException("'out' must be a BlockItem: " + outStack);
            }

            return new ToolOnBlockRecipe(recipeId, in, tool, out);
        }

        @Override
        public @Nullable ToolOnBlockRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            Ingredient tool = Ingredient.fromNetwork(buffer);

            ItemStack inStack = buffer.readItem();
            if (!(inStack.getItem() instanceof BlockItem in)) {
                throw new IllegalStateException("ToolOnBlockRecipe 'in' must be a BlockItem: " + inStack);
            }

            ItemStack outStack = buffer.readItem();
            if (!(outStack.getItem() instanceof BlockItem out)) {
                throw new IllegalStateException("ToolOnBlockRecipe 'out' must be a BlockItem: " + outStack);
            }

            return new ToolOnBlockRecipe(recipeId, in, tool, out);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ToolOnBlockRecipe recipe) {
            recipe.tool.toNetwork(buffer);

            buffer.writeItem(new ItemStack(recipe.in));

            buffer.writeItem(new ItemStack(recipe.out));
        }
    }
}
