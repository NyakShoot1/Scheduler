package com.example.schreduler.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scheduler")
data class SchedulerDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "days_with_employees") val daysWithEmployees: String,
    @ColumnInfo(name = "month") val month: Byte,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "version") val version: Int
)
