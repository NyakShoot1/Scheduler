package com.example.schreduler.ui.screen.default_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.schreduler.utils.AppConstants.DAYS_NAME
import com.example.schreduler.utils.AppConstants.MONTHS_NAME
import com.example.schreduler.utils.CalendarHandler
import java.util.Calendar

@Composable
fun CalendarBase(
    dayContent: @Composable (Int) -> Unit,
    containerColor: Color = Color.White,
    calendarHandler: CalendarHandler = CalendarHandler(),
    headerContent: @Composable () -> Unit = {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = stringResource(
                MONTHS_NAME[Calendar.getInstance().get(Calendar.MONTH)]!!
            ) + " " + calendarHandler.getCurrentYear().toString(),
            textAlign = TextAlign.Center
        )
    },
) {
    calendarHandler.refreshCalendarInstance()
    val currentMonth = calendarHandler.getCurrentMonth()
    val currentYear = calendarHandler.getCurrentYear()
    calendarHandler.getCalendarInstance().set(currentYear, currentMonth.toInt(), 1)
    val firstDayOfWeek = calendarHandler.getFirstDayOfWeek()
    val daysInMonth = calendarHandler.getCurrentDaysInMonth()

    val offset = (firstDayOfWeek + 5) % 7

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.5f),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            headerContent()

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(7),
                content = {
                    items(DAYS_NAME.values.toList()) { day ->
                        Text(
                            text = stringResource(day),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize()
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
                            dayContent(day)
                        } else {
                            Box(modifier = Modifier.aspectRatio(1f))
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp),
            )
        }
    }
}