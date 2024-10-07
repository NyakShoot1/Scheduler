package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.runtime.Composable
import com.example.schreduler.data.model.Employee
import com.example.schreduler.ui.screen.default_components.CalendarBase

@Composable
fun CalendarOnMonth(
    days: Map<Int, List<Employee>>,
    currentDay: String,
) {
    CalendarBase(
        dayContent = { day ->
            DayCell(
                day = day.toString(),
                isCurrentDay = day.toString() == currentDay,
                employees = days[day]!!
            )
        }
    )
}