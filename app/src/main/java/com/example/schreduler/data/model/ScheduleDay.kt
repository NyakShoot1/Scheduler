package com.example.schreduler.data.model

import com.example.schreduler.data.model.enums.DayType

data class ScheduleDay(
    val employeeId: Long,
    val dayType: DayType
)