package com.example.schreduler.data.room.schemas

import androidx.room.ColumnInfo

data class SchedulerSchema(
    val id: Long,
    @ColumnInfo(name = "days_with_employees") val daysWithEmployees: String,
    @ColumnInfo(name = "month") val month: Byte,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "version") val version: Int
)
