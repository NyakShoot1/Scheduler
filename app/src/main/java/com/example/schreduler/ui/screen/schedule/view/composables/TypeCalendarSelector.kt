package com.example.schreduler.ui.screen.schedule.view.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.schreduler.R
import com.example.schreduler.data.model.enums.CalendarType
import com.example.schreduler.ui.screen.default_components.DefaultButton

@Composable
fun TypeCalendarSelector(
    selectedType: CalendarType,
    onDefaultCalendar: () -> Unit,
    onGridCalendar: () -> Unit
) {
    val textRes1 = R.string.default_calendar
    val textRes2 = R.string.grid_calendar
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        DefaultButton(
            textRes = textRes1,
            isOutlined = selectedType != CalendarType.DEFAULT_CALENDAR,
            modifier = Modifier.weight(1f)
        ) {
            onDefaultCalendar()
        }

        DefaultButton(
            textRes = textRes2,
            isOutlined = selectedType != CalendarType.GRID_CALENDAR,
            modifier = Modifier.weight(1f)
        ) {
            onGridCalendar()
        }
    }

}