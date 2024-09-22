package com.example.schreduler.data.room.repository

import com.example.schreduler.data.room.dao.SchedulerDao
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val schedulerDao: SchedulerDao
) {

   // suspend fun insertNewScheduler(newScheduler: SchedulerDbEntity) = schedulerDao

}