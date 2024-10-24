package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.schreduler.ui.theme.SchedulerDefaultLightThemeColors
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun MonthNavigator(
    currentYearMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onPreviousMonth() }
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                "Arrow left",
                tint = SchedulerDefaultLightThemeColors.defaultIconColor
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(0.6f),
            text = currentYearMonth.format(
                DateTimeFormatter.ofPattern("LLLL yyyy", Locale.getDefault())
            ).replaceFirstChar { it.uppercaseChar() },
            color = SchedulerDefaultLightThemeColors.defaultTextOutlinedButton,
            textAlign = TextAlign.Center
        )
        IconButton(
            onClick = { onNextMonth() }
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                "Arrow right",
                tint = SchedulerDefaultLightThemeColors.defaultIconColor
            )
        }
    }
}
