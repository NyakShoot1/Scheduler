package com.example.schreduler.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class EmployeeDbEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "position") val position: String,
    @ColumnInfo(name = "no_working_days") val noWorkingDays: String,
    @ColumnInfo(name = "status") val status: String, // TODO переделать в enum https://stackoverflow.com/questions/57326789/how-to-save-enum-field-in-the-database-room
)