package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schreduler.data.model.Employee
import com.example.schreduler.data.model.ScheduleDay
import com.example.schreduler.data.model.enums.DayType
import com.example.schreduler.ui.screen.default_components.DaysOfWeekTitle
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun ScheduleCalendar(
    schedule: Map<Int, List<ScheduleDay>>,
    employees: List<Employee>,
    currentYearMonth: YearMonth
) {
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val state = rememberCalendarState(
        startMonth = currentYearMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    Column(
        modifier = Modifier.background(Color.Transparent)
    ) {
        DaysOfWeekTitle()
        HorizontalCalendar(
            modifier = Modifier,
            state = state,
            dayContent = { day ->
                Day(
                    day,
                    scheduleDays = schedule[day.date.dayOfMonth] ?: listOf(),
                    onClick = {  },
                )
            }
        )
    }
}

@Composable
private fun Day(
    day: CalendarDay,
    scheduleDays: List<ScheduleDay>,
    onClick: () -> Unit,
) {
    val today = LocalDate.now()
    val isInCurrentMonth = day.position == DayPosition.MonthDate
    val isToday = day.date.isEqual(today)

    val textColor = when {
        isToday -> Color(0xFF0295F6)
        isInCurrentMonth -> Color.Gray
        else -> Color.LightGray
    }

    Box(
        modifier = Modifier.clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(color = Color.Transparent)
            .clickable{
                if (day.position == DayPosition.MonthDate) {
                    onClick()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(0.95f)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isToday) Color(0x100295F6)
                    else Color.Transparent
                )
                .border(
                    1.dp,
                    if (isToday) Color(0xFF0295F6)
                    else Color.Transparent,
                    RoundedCornerShape(10.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = day.date.dayOfMonth.toString(),
                color = textColor
            )
            // TODO circles
        }

    }

}

@Preview
@Composable
fun ScheduleCalendarPreview() {
    val employees = listOf(
        Employee(1, "Иван", "Иванов"),
        Employee(2, "Петр", "Петров"),
        Employee(3, "Сидор", "Сидоров")
    )

    val schedule = (1..31).associateWith { day ->
        employees.map { employee ->
            ScheduleDay(
                employeeId = employee.id,
                dayType = DayType.entries.random()
            )
        }
    }

    ScheduleCalendar(
        schedule = schedule,
        employees = employees,
        currentYearMonth = YearMonth.now(),
    )
}