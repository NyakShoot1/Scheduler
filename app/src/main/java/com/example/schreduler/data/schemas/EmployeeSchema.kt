package com.example.schreduler.data.schemas

import androidx.room.ColumnInfo

data class EmployeeSchema(
    val id: Long,
    @ColumnInfo(name = "position") val position: String,
    @ColumnInfo(name = "no_working_days") val noWorkingDays: String,
    @ColumnInfo(name = "status") val status: String
)
