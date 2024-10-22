package com.example.schreduler.data.room.schemas

import androidx.room.ColumnInfo
import com.example.schreduler.data.model.ScheduleDay

data class ScheduleTuple(
    @ColumnInfo(name = "schedule") val schedule: Map<Int, List<ScheduleDay>>,
)
