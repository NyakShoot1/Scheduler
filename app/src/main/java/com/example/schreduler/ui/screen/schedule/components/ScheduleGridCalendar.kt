package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schreduler.data.model.Employee
import com.example.schreduler.data.model.ScheduleDay
import com.example.schreduler.data.model.enums.DayType
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

private object GraphCalendarColors {
    val columnTitleColor = Color(0xFF0295F6)
    val columnWeekend = Color(0xFF9AD5FB)
    val defaultCellColor = Color(0xFFD9D9D9)
}

private object GraphCalendarSizes {
    val titleDayHeight = 45.dp

    val titleEmployeeWeight = 106.dp
    val titleEmployeeHeight = 45.dp

    val cellSpacing = 7.dp
}
@Composable
fun ScheduleGridCalendar(
    schedule: Map<Int, List<ScheduleDay>>,
    employees: List<Employee>,
    modifier: Modifier
) {
    val horizontalScrollState = rememberScrollState()
    val verticalScrollState = rememberScrollState()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(GraphCalendarSizes.cellSpacing),
    ) {
        // Левая колонка с именами
        Column(
            verticalArrangement = Arrangement.spacedBy(GraphCalendarSizes.cellSpacing)
        ) {
            // Заголовок колонки с именами
            EmployeeTitle()

            // Список имен сотрудников
            Column(
                modifier = Modifier
                    .verticalScroll(verticalScrollState)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(GraphCalendarSizes.cellSpacing)
            ) {
                employees.forEach { employee ->
                    EmployeeNameTitle(employee)
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(GraphCalendarSizes.cellSpacing)
        ) {
            // Верхняя строка с днями
            Row(
                modifier = Modifier
                    .horizontalScroll(horizontalScrollState),
                horizontalArrangement = Arrangement.spacedBy(GraphCalendarSizes.cellSpacing)
            ) {
                schedule.forEach { (day, _) ->
                    DayTitle(day)
                }
            }

            // Основная сетка календаря
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(verticalScrollState)
                    .horizontalScroll(horizontalScrollState),
                verticalArrangement = Arrangement.spacedBy(GraphCalendarSizes.cellSpacing)
            ) {
                employees.forEach { employee ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(GraphCalendarSizes.cellSpacing)
                    ) {
                        schedule.forEach { (day, scheduleDays) ->
                            val daySchedule = scheduleDays.find { it.employeeId == employee.id }
                            ScheduleCell(dayType = daySchedule?.dayType ?: DayType.NOT_WORK)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun ScheduleGridCalendar2(
    schedule: Map<Int, List<ScheduleDay>>,
    employees: List<Employee>,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            EmployeeTitle()
            employees.forEach { employee ->
                EmployeeNameTitle(employee)
            }
        }

        Column(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                schedule.forEach { (day, _) ->
                    DayTitle(day)
                }
            }

            // Ячейки расписания
            employees.forEach { employee ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    schedule.forEach { (day, scheduleDays) ->
                        val daySchedule = scheduleDays.find { it.employeeId == employee.id }
                        ScheduleCell(dayType = daySchedule?.dayType ?: DayType.NOT_WORK)
                    }
                }
            }
        }
    }
}

@Composable
fun EmployeeNameTitle(employee: Employee) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
            .background(GraphCalendarColors.defaultCellColor)
            .size(
                width = GraphCalendarSizes.titleEmployeeWeight,
                height = GraphCalendarSizes.titleEmployeeHeight
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 5.dp, bottomEnd = 5.dp))
                    .fillMaxHeight()
                    .width(11.dp)
                    .background(employee.color)

            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = employee.name
            )
        }

    }
}

@Composable
fun EmployeeTitle() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
            .background(GraphCalendarColors.columnTitleColor)
            .size(
                width = GraphCalendarSizes.titleEmployeeWeight,
                height = GraphCalendarSizes.titleEmployeeHeight
            ),
        contentAlignment = Alignment.Center
    ) {
        Text("Сотрудник", color = Color.White) // todo res
    }
}

@Composable
fun DayTitle(day: Int) {
    val dayBackgroundColor = if (
        LocalDate.now().withDayOfMonth(day).dayOfWeek.value in listOf(6, 7)
    ) GraphCalendarColors.columnWeekend else GraphCalendarColors.columnTitleColor

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .size(
                width = GraphCalendarSizes.titleEmployeeHeight,
                height = GraphCalendarSizes.titleDayHeight
            )
            .background(dayBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(day.toString(), color = Color.White)
            Text(LocalDate.now().withDayOfMonth(day).dayOfWeek.getDisplayName(
                TextStyle.SHORT,
                Locale.getDefault()
            ).replaceFirstChar { it.uppercaseChar() }, color = Color.White
            )
        }
    }
}

@Composable
fun ScheduleCell(dayType: DayType) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(dayType.color)
            .size(
                width = GraphCalendarSizes.titleEmployeeHeight,
                height = GraphCalendarSizes.titleEmployeeHeight
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(dayType.title)
    }
}

@Preview
@Composable
fun GraphCalendarPreview() {
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

    ScheduleGridCalendar(
        schedule = schedule,
        employees = employees,
        modifier = Modifier
    )
}