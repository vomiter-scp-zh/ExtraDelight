package com.vomiter.extradelight.util;


import com.google.gson.JsonObject;

public class JsonStackTransformer {
    public static JsonObject addItemForIdFormat(JsonObject object){
        if(object.has("id")){
            object.addProperty("item", object.get("id").getAsString());
        }
        return object;
    }
}
