package com.example.schreduler.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.schreduler.data.room.dao.EmployeeDao
import com.example.schreduler.data.room.dao.SchedulerDao
import com.example.schreduler.data.room.model.EmployeeDbEntity
import com.example.schreduler.data.room.model.ScheduleDbEntity
import com.example.schreduler.utils.ColorConverter
import com.example.schreduler.utils.JsonMapConverter

@Database(
    version = 1,
    entities = [
        EmployeeDbEntity::class,
        ScheduleDbEntity::class
    ]
)
@TypeConverters(JsonMapConverter::class, ColorConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getEmployeeDao(): EmployeeDao

    abstract fun getSchedulerDao(): SchedulerDao

}