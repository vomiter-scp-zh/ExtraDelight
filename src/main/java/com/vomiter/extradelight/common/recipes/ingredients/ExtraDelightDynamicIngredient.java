package com.vomiter.extradelight.common.recipes.ingredients;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vomiter.extradelight.ExtraDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.AbstractIngredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ExtraDelightDynamicIngredient extends AbstractIngredient {
    public static final Serializer SERIALIZER = new Serializer();
    public static final ResourceLocation ID = ExtraDelight.modLoc("dynamic_food_ingredient");

    private static final String COMPONENTS = "components";
    private static final String DYNAMIC_FOOD = "extradelight:dynamic_food";
    private static final String GRAPHICS = "graphics";

    @Nullable
    private final Item item;

    @Nullable
    private final TagKey<Item> tag;

    private final List<String> graphics;

    public ExtraDelightDynamicIngredient(@Nullable Item item, @Nullable TagKey<Item> tag, List<String> graphics) {
        super(makeValues(item, tag));

        if ((item == null) == (tag == null)) {
            throw new IllegalArgumentException("ExtraDelightDynamicIngredient must have exactly one of item or tag");
        }

        this.item = item;
        this.tag = tag;
        this.graphics = List.copyOf(graphics);
    }

    private static Stream<? extends Ingredient.Value> makeValues(@Nullable Item item, @Nullable TagKey<Item> tag) {
        if (item != null) {
            return Stream.of(new Ingredient.ItemValue(new ItemStack(item)));
        }

        if (tag != null) {
            return Stream.of(new Ingredient.TagValue(tag));
        }

        return Stream.empty();
    }

    @Override
    public boolean test(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }

        if (this.item != null && !stack.is(this.item)) {
            return false;
        }

        if (this.tag != null && !stack.is(this.tag)) {
            return false;
        }

        if (this.graphics.isEmpty()) {
            return true;
        }

        List<String> stackGraphics = readGraphicsFromStack(stack);

        return stackGraphics.stream().anyMatch(this.graphics::contains);
    }

    private static List<String> readGraphicsFromStack(ItemStack stack) {
        CompoundTag root = stack.getTag();
        if (root == null) {
            return List.of();
        }

        //{ "extradelight:dynamic_food": { "graphics": [...] } }
        CompoundTag dynamicFood = null;

        if (root.contains(DYNAMIC_FOOD, Tag.TAG_COMPOUND)) {
            dynamicFood = root.getCompound(DYNAMIC_FOOD);
        }

        if (dynamicFood == null || !dynamicFood.contains(GRAPHICS, Tag.TAG_LIST)) {
            return List.of();
        }

        return readStringList(dynamicFood, GRAPHICS);
    }

    private static List<String> readStringList(CompoundTag tag, String key) {
        ListTag listTag = tag.getList(key, Tag.TAG_STRING);
        List<String> result = new ArrayList<>();

        for (int i = 0; i < listTag.size(); i++) {
            result.add(listTag.getString(i));
        }

        return result;
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();

        json.addProperty("type", "extradelight:components");

        if (this.item != null) {
            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(this.item);
            json.addProperty("items", itemId.toString());
        }

        if (this.tag != null) {
            json.addProperty("tag", this.tag.location().toString());
        }

        JsonObject components = new JsonObject();
        JsonObject dynamicFood = new JsonObject();
        JsonArray graphicsArray = new JsonArray();

        for (String graphic : this.graphics) {
            graphicsArray.add(graphic);
        }

        dynamicFood.add(GRAPHICS, graphicsArray);
        components.add(DYNAMIC_FOOD, dynamicFood);
        json.add(COMPONENTS, components);

        return json;
    }

    public static class Serializer implements IIngredientSerializer<ExtraDelightDynamicIngredient> {
        @Override
        public ExtraDelightDynamicIngredient parse(JsonObject json) {
            Item item = null;
            TagKey<Item> tag = null;

            if (json.has("items")) {
                ResourceLocation itemId
                        = ResourceLocation.tryParse(GsonHelper.getAsString(json, "items"));
                item = BuiltInRegistries.ITEM.getOptional(itemId)
                        .orElseThrow(() -> new IllegalArgumentException("Unknown item: " + itemId));
            }

            if (json.has("tag")) {
                ResourceLocation tagId
                        = ResourceLocation.tryParse(GsonHelper.getAsString(json, "tag"));
                tag = TagKey.create(Registries.ITEM, tagId);
            }

            List<String> graphics = parseGraphics(json);

            return new ExtraDelightDynamicIngredient(item, tag, graphics);
        }

        @Override
        public ExtraDelightDynamicIngredient parse(FriendlyByteBuf buffer) {
            boolean hasItem = buffer.readBoolean();

            Item item = null;
            TagKey<Item> tag = null;

            if (hasItem) {
                ResourceLocation itemId = buffer.readResourceLocation();
                item = BuiltInRegistries.ITEM.getOptional(itemId)
                        .orElseThrow(() -> new IllegalArgumentException("Unknown item from network: " + itemId));
            } else {
                ResourceLocation tagId = buffer.readResourceLocation();
                tag = TagKey.create(Registries.ITEM, tagId);
            }

            int graphicsSize = buffer.readVarInt();
            List<String> graphics = new ArrayList<>();

            for (int i = 0; i < graphicsSize; i++) {
                graphics.add(buffer.readUtf());
            }

            return new ExtraDelightDynamicIngredient(item, tag, graphics);
        }

        @Override
        public void write(FriendlyByteBuf buffer, ExtraDelightDynamicIngredient ingredient) {
            boolean hasItem = ingredient.item != null;
            buffer.writeBoolean(hasItem);

            if (hasItem) {
                buffer.writeResourceLocation(BuiltInRegistries.ITEM.getKey(ingredient.item));
            } else {
                buffer.writeResourceLocation(ingredient.tag.location());
            }

            buffer.writeVarInt(ingredient.graphics.size());

            for (String graphic : ingredient.graphics) {
                buffer.writeUtf(graphic);
            }
        }

        private static List<String> parseGraphics(JsonObject json) {
            if (!json.has(COMPONENTS)) {
                return List.of();
            }

            JsonObject components = GsonHelper.getAsJsonObject(json, COMPONENTS);

            if (!components.has(DYNAMIC_FOOD)) {
                return List.of();
            }

            JsonObject dynamicFood = GsonHelper.getAsJsonObject(components, DYNAMIC_FOOD);

            if (!dynamicFood.has(GRAPHICS)) {
                return List.of();
            }

            JsonArray graphicsArray = GsonHelper.getAsJsonArray(dynamicFood, GRAPHICS);
            List<String> graphics = new ArrayList<>();

            for (JsonElement element : graphicsArray) {
                graphics.add(element.getAsString());
            }

            return graphics;
        }
    }
}