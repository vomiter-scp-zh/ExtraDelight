package com.vomiter.extradelight.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class AdditionalTagKeyThreadLocal {
    public static ThreadLocal<List<TagKey<Item>>> tagkeys = ThreadLocal.withInitial(ArrayList::new);
    public static ThreadLocal<Boolean> isInIngredient = ThreadLocal.withInitial(() -> false);
    public static ThreadLocal<Boolean> isInExtraDelight = ThreadLocal.withInitial(() -> false);
}
