package com.example.schreduler.ui.screen.schedule

import com.example.schreduler.data.model.Employee
import com.example.schreduler.data.model.ScheduleDay
import com.example.schreduler.data.model.enums.CalendarType
import java.time.LocalDate
import java.time.YearMonth

data class ScheduleUiState(
    val schedule: Map<Int, List<ScheduleDay>> = mapOf(),

    val employees: List<Employee> = listOf(),

    val selectedDay: LocalDate = LocalDate.now(),

    val selectedTypeOfCalendar: CalendarType = CalendarType.DEFAULT_CALENDAR,

    val scheduleIsLoaded: Boolean = false,

    val currentSelectedYearMonth: YearMonth = YearMonth.now(),

    val error: String? = null
)