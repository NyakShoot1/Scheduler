package com.example.schreduler.ui.screen.schedule_create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.schreduler.ui.screen.default_components.DaysOfWeekTitle
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition

@Composable
fun ScheduleCreateCalendar(selectedDays: Set<Int>, onDaysChanged: (List<Int>) -> Unit) {
    val state = rememberCalendarState()

    DaysOfWeekTitle()
    VerticalCalendar(
        modifier = Modifier,
        state = state,
        dayContent = { day ->
            Day(
                day,
                day.date.dayOfMonth in selectedDays,
                onClick = {
                    val updatedDays = if (day.date.dayOfMonth in selectedDays) {
                        selectedDays - day.date.dayOfMonth
                    } else {
                        selectedDays + day.date.dayOfMonth
                    }
                    onDaysChanged(updatedDays.toList())
                }
            )
        }
    )
}

@Composable
private fun Day(day: CalendarDay, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(
                if (day.position == DayPosition.MonthDate && isSelected) Color.Blue
                else Color.Transparent,
                CircleShape
            )
            .aspectRatio(1f)  // This is important for square sizing!
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = if (day.position == DayPosition.MonthDate && isSelected) Color.White
            else if (day.position == DayPosition.MonthDate) Color.Black
            else Color.LightGray
        )
    }
}