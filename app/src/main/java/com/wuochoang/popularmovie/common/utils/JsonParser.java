package com.wuochoang.popularmovie.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by TuanJio on 8/11/2017.
 */

public class JsonParser {
    private static JsonSerializer<Date> serializerDate = new JsonSerializer<Date>() {
        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext
                context) {
            return src == null ? null : new JsonPrimitive(TimeUnit.SECONDS.toMillis(src.getTime()));
        }
    };
    private static JsonDeserializer<Date> deserializerDate = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
            return json == null ? null : new Date(TimeUnit.SECONDS.toMillis(json.getAsLong()));
        }
    };

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static JsonElement toJsonElement(Object object) {
        return new Gson().toJsonTree(object);
    }

    public static <Object> Object fromJson(String stringJson, Class<Object> classOfT)
            throws JSONException {
        JSONObject object = (JSONObject) new JSONTokener(stringJson).nextValue();
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, serializerDate)
                .registerTypeAdapter(Date.class, deserializerDate)
                .create();
        return object != null ? gson.fromJson(stringJson, classOfT) : null;
    }

    public static JsonElement fromJsonElement(Map<String, Object> params) {
        JsonElement jsonData = null;
        try {
            jsonData =
                    JsonParser.fromJson(JsonParser.toJson(params), JsonElement.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
}