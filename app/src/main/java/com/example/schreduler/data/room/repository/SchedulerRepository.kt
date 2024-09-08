package com.example.schreduler.data.room.repository

import com.example.schreduler.data.room.dao.SchedulerDao
import com.example.schreduler.data.room.model.SchedulerDbEntity
import javax.inject.Inject

class SchedulerRepository @Inject constructor(
    private val schedulerDao: SchedulerDao
) {

   // suspend fun insertNewScheduler(newScheduler: SchedulerDbEntity) = schedulerDao

}