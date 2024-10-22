package com.example.schreduler.utils

import androidx.room.TypeConverter
import com.example.schreduler.data.model.ScheduleDay
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonMapConverter {
    @TypeConverter
    fun fromString(value: String): Map<Int, List<ScheduleDay>> {
        val mapType = object : TypeToken<Map<Int, List<ScheduleDay>>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromMap(map: Map<Int, List<ScheduleDay>>): String {
        return Gson().toJson(map)
    }
}