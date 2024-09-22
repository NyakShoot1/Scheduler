package com.example.schreduler.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scheduler")
data class ScheduleDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "schedule") val schedule: String,
    @ColumnInfo(name = "month") val month: Byte,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "version") val version: Int
)
