package com.example.schreduler.ui.screen.schedule

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.schreduler.data.model.Employee
import java.time.LocalDate

data class ScheduleUiState(
    val schedule: MutableState<Map<Int, List<Employee>>> = mutableStateOf(mapOf()),
    val employees: MutableState<List<Employee>> = mutableStateOf(listOf()),
    val selectedDay: MutableState<LocalDate> = mutableStateOf(LocalDate.now())
)
