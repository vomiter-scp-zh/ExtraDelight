package com.vomiter.extradelight;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.HashMap;
import java.util.List;

public class DataComponents {
    public static final ResourceLocation MEAL = ResourceLocation.fromNamespaceAndPath("farmersdelight", "meal");
    public static final ResourceLocation CONTAINER = ResourceLocation.fromNamespaceAndPath("farmersdelight", "container");
    public static final ResourceLocation CHILL = ResourceLocation.fromNamespaceAndPath("extradelight", "chill");

    public static final ResourceLocation DYNAMIC = ExtraDelight.modLoc("dynamic");
    public static final ResourceLocation INGREDIENTS = ExtraDelight.modLoc("ingredients");

    public static List<ItemStack> getDynamicIngredients(ItemStack stack){
        var root = stack.getTag();
        if(root != null && root.contains(DYNAMIC.toString(), Tag.TAG_COMPOUND)){
            if(root.getCompound(DYNAMIC.toString()).contains(INGREDIENTS.toString(), Tag.TAG_LIST)){
                var list = root.getCompound(DYNAMIC.toString()).getList(INGREDIENTS.toString(), Tag.TAG_COMPOUND);
                var items = list.stream().map(tag -> ItemStack.of((CompoundTag) tag));
                return items.toList();
            }
        }

        return List.of();
    }

    public static void setDynamicIngredient(ItemStack stack, List<ItemStack> graphics) {
        CompoundTag root = stack.getOrCreateTag();

        CompoundTag dynamic = root.getCompound(DYNAMIC.toString());
        ListTag list = new ListTag();

        for (ItemStack graphic : graphics) {
            if (!graphic.isEmpty()) {
                list.add(graphic.save(new CompoundTag()));
            }
        }

        dynamic.put(INGREDIENTS.toString(), list);
        root.put(DYNAMIC.toString(), dynamic);
    }

    public static final String BLOCK_STATE_TAG = "BlockStateTag";

    private static final HashMap<Item, Integer> CHILL_MAP = new HashMap<>();
    public static void setUpBuiltInChill(){
        CHILL_MAP.put(Items.BLUE_ICE, 10000);
        CHILL_MAP.put(Items.PACKED_ICE, 1000);
        CHILL_MAP.put(Items.ICE, 100);
        CHILL_MAP.put(Items.SNOW_BLOCK, 250);
        CHILL_MAP.put(Items.SNOWBALL, 50);
    }

    public static Integer getChill(ItemStack stack){
        var builtIn = CHILL_MAP.get(stack.getItem());
        CompoundTag tag = stack.getTag();
        if(tag == null || !tag.contains(CHILL.toString())) return builtIn;
        return tag.getInt(CHILL.toString());
    }

    public static void setChillItem(Item item, int chill){
        CHILL_MAP.put(item, chill);
    }

    public static void setChill(ItemStack stack, int chill){
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(CHILL.toString(), chill);
    }

    public static ItemStack getStack(ItemStack stack, ResourceLocation key) {
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains(key.toString(), Tag.TAG_COMPOUND)) {
            return ItemStack.EMPTY;
        }

        return ItemStack.of(tag.getCompound(key.toString()));
    }

    public static void setStack(ItemStack stack, ResourceLocation key, ItemStack value) {
        CompoundTag tag = stack.getOrCreateTag();

        if (value.isEmpty()) {
            tag.remove(key.toString());
        } else {
            tag.put(key.toString(), value.save(new CompoundTag()));
        }
    }

    public static CompoundTag getBlockStateTag(ItemStack stack) {
        CompoundTag tag = stack.getTag();

        if (tag == null || !tag.contains(BLOCK_STATE_TAG, Tag.TAG_COMPOUND)) {
            return new CompoundTag();
        }

        return tag.getCompound(BLOCK_STATE_TAG);
    }

    public static void setBlockStateProperty(ItemStack stack, IntegerProperty property, int value) {
        CompoundTag blockStateTag = stack.getOrCreateTagElement(BLOCK_STATE_TAG);
        blockStateTag.putString(property.getName(), Integer.toString(value));
    }

    public static int getBlockStateInt(ItemStack stack, IntegerProperty property, int fallback) {
        CompoundTag blockStateTag = getBlockStateTag(stack);
        String propertyName = property.getName();

        if (!blockStateTag.contains(propertyName, Tag.TAG_STRING)) {
            return fallback;
        }

        try {
            int value = Integer.parseInt(blockStateTag.getString(propertyName));
            return property.getPossibleValues().contains(value) ? value : fallback;
        } catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    public static BlockState applyBlockStateTag(ItemStack stack, BlockState state) {
        CompoundTag root = stack.getTag();

        if (root == null || !root.contains(BLOCK_STATE_TAG, Tag.TAG_COMPOUND)) {
            return state;
        }

        CompoundTag blockStateTag = root.getCompound(BLOCK_STATE_TAG);

        for (Property<?> property : state.getProperties()) {
            String propertyName = property.getName();

            if (!blockStateTag.contains(propertyName, Tag.TAG_STRING)) {
                continue;
            }

            state = applyProperty(state, property, blockStateTag.getString(propertyName));
        }

        return state;
    }

    private static <T extends Comparable<T>> BlockState applyProperty(
            BlockState state,
            Property<T> property,
            String value
    ) {
        return property.getValue(value)
                .map(propertyValue -> state.setValue(property, propertyValue))
                .orElse(state);
    }
}