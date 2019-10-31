package com.mfq.foodle.utils;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class GsonHelper {

    public static <T> List<T> parseGsonArray(String json, Class<T[]> model) {
        return Arrays.asList(new Gson().fromJson(json, model));
    }



}
