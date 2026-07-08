package com.vomiter.extradelight.common.recipes.ingredients;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.vomiter.extradelight.ExtraDelight;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.AbstractIngredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExtraDelightCompoundIngredient extends AbstractIngredient {
    public static final Serializer SERIALIZER = new Serializer();

    private final List<Ingredient> children;
    private final boolean isSimple;

    private ItemStack[] stacks;
    private IntList itemIds;

    public ExtraDelightCompoundIngredient(List<Ingredient> children) {
        if (children.isEmpty()) {
            throw new IllegalArgumentException("Compound ingredient must have at least one child");
        }

        this.children = Collections.unmodifiableList(children);
        this.isSimple = children.stream().allMatch(Ingredient::isSimple);
    }

    @Override
    public boolean test(@Nullable ItemStack stack) {
        if (stack == null) {
            return false;
        }

        for (Ingredient child : this.children) {
            if (child.test(stack)) {
                return true;
            }
        }

        return false;
    }

    @Override
    @NotNull
    public ItemStack @NotNull [] getItems() {
        if (this.stacks == null) {
            List<ItemStack> items = Lists.newArrayList();

            for (Ingredient child : this.children) {
                Collections.addAll(items, child.getItems());
            }

            this.stacks = items.toArray(new ItemStack[0]);
        }

        return this.stacks;
    }

    @Override
    @NotNull
    public IntList getStackingIds() {
        boolean childrenNeedInvalidation = false;

        for (Ingredient child : this.children) {
            childrenNeedInvalidation |= child.checkInvalidation();
        }

        if (childrenNeedInvalidation || this.itemIds == null || this.checkInvalidation()) {
            this.markValid();

            this.itemIds = new IntArrayList();

            for (Ingredient child : this.children) {
                this.itemIds.addAll(child.getStackingIds());
            }

            this.itemIds.sort(IntComparators.NATURAL_COMPARATOR);
        }

        return this.itemIds;
    }

    @Override
    public boolean isSimple() {
        return this.isSimple;
    }

    @Override
    public boolean isEmpty() {
        return this.children.stream().allMatch(Ingredient::isEmpty);
    }

    @Override
    public @NotNull IIngredientSerializer<? extends Ingredient> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public @NotNull JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", "extradelight:compound");

        JsonArray childrenJson = new JsonArray();

        for (Ingredient child : this.children) {
            childrenJson.add(child.toJson());
        }

        json.add("children", childrenJson);
        return json;
    }

    public List<Ingredient> getChildren() {
        return this.children;
    }

    public static class Serializer implements IIngredientSerializer<ExtraDelightCompoundIngredient> {
        public static final ResourceLocation ID = ExtraDelight.modLoc("compound");
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public @NotNull ExtraDelightCompoundIngredient parse(FriendlyByteBuf buffer) {
            int size = buffer.readVarInt();

            List<Ingredient> children = Stream.generate(() -> Ingredient.fromNetwork(buffer))
                    .limit(size)
                    .collect(Collectors.toList());

            return new ExtraDelightCompoundIngredient(children);
        }

        @Override
        public @NotNull ExtraDelightCompoundIngredient parse(JsonObject json) {
            JsonArray childrenJson;

            if (json.has("children")) {
                childrenJson = GsonHelper.getAsJsonArray(json, "children");
            } else if (json.has("ingredients")) {
                childrenJson = GsonHelper.getAsJsonArray(json, "ingredients");
            } else {
                throw new JsonSyntaxException("extradelight:compound requires 'children' or 'ingredients'");
            }

            if (childrenJson.isEmpty()) {
                throw new JsonSyntaxException("extradelight:compound must have at least one child");
            }

            List<Ingredient> children = Lists.newArrayList();

            for (int i = 0; i < childrenJson.size(); i++) {
                Ingredient child = Ingredient.fromJson(childrenJson.get(i));

                if (!child.isEmpty()) {
                    children.add(child);
                }
            }

            if (children.isEmpty()) {
                throw new JsonSyntaxException("extradelight:compound must have at least one non-empty child");
            }

            return new ExtraDelightCompoundIngredient(children);
        }

        @Override
        public void write(FriendlyByteBuf buffer, ExtraDelightCompoundIngredient ingredient) {
            buffer.writeVarInt(ingredient.children.size());

            for (Ingredient child : ingredient.children) {
                child.toNetwork(buffer);
            }
        }
    }
}