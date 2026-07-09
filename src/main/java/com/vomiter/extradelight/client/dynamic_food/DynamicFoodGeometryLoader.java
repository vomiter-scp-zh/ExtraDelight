package com.vomiter.extradelight.client.dynamic_food;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.vomiter.extradelight.ExtraDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicFoodGeometryLoader implements IGeometryLoader<DynamicFoodUnbakedGeometry> {
    public static ResourceLocation ID = ExtraDelight.modLoc("dynamic_item");
    @Override
    public DynamicFoodUnbakedGeometry read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) throws JsonParseException {
        return new DynamicFoodUnbakedGeometry();
    }

    public static <T extends ModelBuilder<T>> CustomLoaderBuilder<T> builder(T parent, ExistingFileHelper existingFileHelper) {
        return new CustomLoaderBuilder<T>(ID, parent, existingFileHelper) {
        };
    }
} 