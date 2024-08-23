package com.example.schreduler.data.repository

import com.example.schreduler.data.dao.SchedulerDao
import com.example.schreduler.data.model.SchedulerDbEntity
import javax.inject.Inject

class SchedulerRepository @Inject constructor(
    private val schedulerDao: SchedulerDao
) {

   // suspend fun insertNewScheduler(newScheduler: SchedulerDbEntity) = schedulerDao

}