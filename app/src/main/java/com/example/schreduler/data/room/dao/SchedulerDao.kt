package com.example.schreduler.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.schreduler.data.room.model.ScheduleDbEntity
import com.example.schreduler.data.room.schemas.ScheduleTuple

@Dao
interface SchedulerDao {

    @Insert(entity = ScheduleDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertNewSchedule(entity: ScheduleDbEntity)

    @Query("SELECT schedule FROM schedule WHERE month= :month AND year= :year")
    fun getSchedule(month: Byte, year: Int): ScheduleTuple?
}