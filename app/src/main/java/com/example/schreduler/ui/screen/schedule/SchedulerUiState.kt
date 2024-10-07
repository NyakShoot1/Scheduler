package com.example.schreduler.ui.screen.schedule

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.schreduler.data.model.Employee

data class SchedulerUiState(
    val schedule: MutableState<Map<Int, List<Employee>>> = mutableStateOf(mapOf()),
    val employees: MutableState<List<Employee>> = mutableStateOf(listOf()),
    val currentDay: MutableState<String> = mutableStateOf("")
)
