package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.schreduler.ui.screen.default_components.DaysOfWeekTitle
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.WeekDayPosition
import java.time.LocalDate

@Composable
fun ScheduleWeekCalendar(selectedDate: MutableState<LocalDate>) {
    val state = rememberWeekCalendarState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFF7F7F7))
    ) {
        DaysOfWeekTitle()
        WeekCalendar(
            state = state,
            dayContent = { day ->
                Day(
                    day,
                    isSelected = selectedDate.value == day.date,
                    onClick = { selectedDate.value = day.date }
                )
            }
        )
    }

}

@Composable
private fun Day(day: WeekDay, isSelected: Boolean, onClick: (WeekDay) -> Unit) {
    val currentDay = LocalDate.now().dayOfMonth
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .clickable(interactionSource, indication = null) { onClick(day) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = day.date.dayOfMonth.toString(),
                color = if (day.date.dayOfMonth == currentDay && isSelected) Color.Blue
                else if (day.date.dayOfMonth == currentDay && !isSelected) Color.Gray
                else if (day.position == WeekDayPosition.RangeDate && isSelected) Color.Blue
                else if (day.position == WeekDayPosition.RangeDate) Color.Black
                else Color.LightGray
            )
        }
        HorizontalDivider(
            thickness = 5.dp,
            color = if (day.date.dayOfMonth == currentDay && isSelected) Color.Blue
            else if (day.date.dayOfMonth == currentDay && !isSelected) Color.Gray
            else if (day.position == WeekDayPosition.RangeDate && isSelected) Color.Blue
            else Color.Transparent
        )
    }
}