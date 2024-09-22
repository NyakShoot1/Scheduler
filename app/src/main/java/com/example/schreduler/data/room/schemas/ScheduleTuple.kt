package com.example.schreduler.data.room.schemas

import androidx.room.ColumnInfo

data class ScheduleTuple(
    val id: Long,
    @ColumnInfo(name = "schedule") val schedule: Map<Int, List<Int>>,
    @ColumnInfo(name = "month") val month: Byte,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "version") val version: Int
)
