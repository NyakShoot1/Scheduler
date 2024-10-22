package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.schreduler.data.model.Employee
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ScheduleTable(schedule: Map<Int, List<Employee>>, employees: List<Employee>) {
    Row(
        modifier = Modifier.horizontalScroll(state = ScrollState(0), enabled = true)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text( text = "",)
            Text("Сотрудник",)
            for (employee in employees) {
                Text(
                    employee.getFullName(),
                    color = employee.color
                )
            }
        }

        for (day in schedule.keys) {
            val localDate = LocalDate.of(Year.now().value, YearMonth.now().monthValue, day)
            val dayOfWeek = localDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(day.toString())
                Text(dayOfWeek)
                for (employee in employees) {
                    Text(
                        text = if (employee in schedule[day]!!) "Р" else "Н"
                    )
                }
            }
        }
    }
}