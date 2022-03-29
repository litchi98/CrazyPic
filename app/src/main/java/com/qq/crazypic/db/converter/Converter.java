package com.qq.crazypic.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Converter {

    static Gson gson = new Gson();

    @TypeConverter
    public static String stringListToString(List<String> stringList) {
        return gson.toJson(stringList);
    }

    @TypeConverter
    public static List<String> stringToStringList(String string) {
        return gson.fromJson(string, new TypeToken<List<String>>() {
        }.getType());
    }
}
