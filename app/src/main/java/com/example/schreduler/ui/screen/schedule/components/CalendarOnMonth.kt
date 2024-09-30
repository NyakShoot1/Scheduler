package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.schreduler.data.model.Employee
import java.util.Calendar

@Composable
fun CalendarOnMonth(
    days: MutableState<Map<Int, List<Employee>>>,
    currentDay: String,
) {
    val daysOfWeek = listOf("пн", "вт", "ср", "чт", "пт", "сб", "вс")
    val calendar = Calendar.getInstance()
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentYear = calendar.get(Calendar.YEAR)

    calendar.set(currentYear, currentMonth, 1)
    val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    // Calculate the offset to align the first day with the correct day of the week
    // Assuming Monday is the first day of the week (as per Russian calendar)
    val offset = (firstDayOfWeek + 5) % 7

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.5f),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "September", //todo resource
            textAlign = TextAlign.Center
        )
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(7),
            content = {
                items(daysOfWeek) { day ->
                    Text(
                        text = day,
                        textAlign = TextAlign.Center
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            content = {
                items(42) { index ->
                    val day = index - offset + 1
                    if (day in 1..daysInMonth) {
                        DayCell(
                            day = day.toString(),
                            isCurrentDay = day.toString() == currentDay,
                            employees = days.value[day]!!
                        )
                    } else {
                        // Empty cell for days outside the current month
                        Box(modifier = Modifier.aspectRatio(1f))
                    }
                }
            },
            userScrollEnabled = false,
            modifier = Modifier.fillMaxSize(),
        )
    }
}