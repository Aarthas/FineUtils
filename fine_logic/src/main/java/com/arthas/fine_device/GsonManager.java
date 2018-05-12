package com.arthas.fine_device;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonManager {

    static Gson gson;

    private GsonManager() {

    }

    public static <E> E fromJson(String json, Class<E> clazz) {
        if (gson == null) {
            gson = new Gson();
        }
        return (E) gson.fromJson(json, clazz);
    }

    public static String toJson(Object src) {
        if (gson == null) {
            gson = new Gson();
        }
        return gson.toJson(src);
    }

    public static Gson createExposeGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

}
