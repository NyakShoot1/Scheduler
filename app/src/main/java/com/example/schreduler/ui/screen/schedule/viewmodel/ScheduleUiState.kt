package com.example.schreduler.ui.screen.schedule.viewmodel

import com.example.schreduler.data.model.Employee
import com.example.schreduler.data.model.ScheduleDay
import com.example.schreduler.data.model.enums.CalendarType
import java.time.LocalDate

data class ScheduleUiState(
    val schedule: Map<Int, List<ScheduleDay>> = mapOf(),

    val employees: List<Employee> = listOf(),

    val selectedDay: LocalDate = LocalDate.now(),

    val selectedTypeOfCalendar: CalendarType = CalendarType.DEFAULT_CALENDAR,

    val scheduleIsLoaded: Boolean = false,

    val error: String? = null
)