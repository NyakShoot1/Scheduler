package com.example.schreduler.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.schreduler.data.room.dao.EmployeeDao
import com.example.schreduler.data.room.dao.SchedulerDao
import com.example.schreduler.data.room.model.EmployeeDbEntity
import com.example.schreduler.data.room.model.SchedulerDbEntity

@Database(
    version = 1,
    entities = [
        EmployeeDbEntity::class,
        SchedulerDbEntity::class
    ]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getEmployeeDao(): EmployeeDao

    abstract fun getSchedulerDao(): SchedulerDao

}