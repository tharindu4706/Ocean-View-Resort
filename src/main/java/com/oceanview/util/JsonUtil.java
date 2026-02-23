package com.oceanview.util;

import com.google.gson.Gson;

// Utility class for JSON operations
public class JsonUtil {
    private static Gson gson = new Gson();

    // Convert object to JSON string
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    // Convert JSON string to object
    public static <T> T fromJson(String json, Class<T> classType) {
        return gson.fromJson(json, classType);
    }
}
