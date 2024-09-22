package com.example.schreduler.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonMapConverter {
    @TypeConverter
    fun fromString(value: String): Map<Int, List<Int>> {
        val mapType = object : TypeToken<Map<Int, List<Int>>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromMap(map: Map<Int, List<Int>>): String {
        return Gson().toJson(map)
    }
}