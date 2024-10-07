package com.example.schreduler.data.room.schemas

import androidx.room.ColumnInfo

data class ScheduleTuple(
    @ColumnInfo(name = "schedule") val schedule: Map<Int, List<Long>>,
)
