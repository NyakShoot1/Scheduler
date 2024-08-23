package com.example.schreduler.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.schreduler.data.dao.EmployeeDao
import com.example.schreduler.data.dao.SchedulerDao
import com.example.schreduler.data.model.EmployeeDbEntity
import com.example.schreduler.data.model.SchedulerDbEntity

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