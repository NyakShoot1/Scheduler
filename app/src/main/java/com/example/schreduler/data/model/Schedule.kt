package com.example.schreduler.data.model

import com.example.schreduler.data.room.model.ScheduleDbEntity

data class Schedule(
    val id: Long = 0,
    val schedule: MutableMap<Int, MutableList<Long>>,
    val month: Byte,
    val year: Int
){
    fun toScheduleDbEntity(): ScheduleDbEntity{
        return ScheduleDbEntity(
            id,
            schedule.toString(),
            month,
            year
        )
    }
}
