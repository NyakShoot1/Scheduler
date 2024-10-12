package com.example.schreduler.data.room.repository

import com.example.schreduler.data.room.dao.SchedulerDao
import com.example.schreduler.data.room.model.ScheduleDbEntity
import com.example.schreduler.data.room.schemas.ScheduleTuple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val schedulerDao: SchedulerDao
) {

    suspend fun insertNewScheduler(newScheduler: ScheduleDbEntity) {
        return withContext(Dispatchers.IO){
            schedulerDao.insertNewSchedule(newScheduler)
        }
    }

    suspend fun getSchedule(month: Int, year: Int): ScheduleTuple? {
        return withContext(Dispatchers.IO){
            schedulerDao.getSchedule(month, year)
        }
    }
}