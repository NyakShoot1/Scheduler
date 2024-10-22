package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schreduler.ui.screen.default_components.DaysOfWeekTitle
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun TestCalendar() {
    val currentMonth = remember { YearMonth.now() }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library
    var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }

    val state = rememberCalendarState(
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    DaysOfWeekTitle()
    VerticalCalendar(
        modifier = Modifier,
        state = state,
        dayContent = { day ->
            Day(
                day,
                isSelected = selectedDate == day.date,
                onClick = { selectedDate = day.date })
        }
    )
}

@Composable
private fun Day(day: CalendarDay, isSelected: Boolean, onClick: (CalendarDay) -> Unit) {
    val currentDay = LocalDate.now().dayOfMonth
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .border(
                    BorderStroke(
                        2.dp,
                        color = if (day.date.dayOfMonth == currentDay && isSelected) Color.Blue
                        else if (day.date.dayOfMonth == currentDay && !isSelected) Color.Gray
                        else if (day.position == DayPosition.MonthDate && isSelected) Color.Blue
                        else Color.Transparent
                    )
                )
                .background(color = Color.LightGray)
                .clickable(interactionSource, indication = null) { onClick(day) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = day.date.dayOfMonth.toString(),
                color = if (day.date.dayOfMonth == currentDay && isSelected) Color.Blue
                else if (day.date.dayOfMonth == currentDay && !isSelected) Color.Gray
                else if (day.position == DayPosition.MonthDate && isSelected) Color.Blue
                else if (day.position == DayPosition.MonthDate) Color.Black
                else Color.LightGray,
            )
        }
    }
}