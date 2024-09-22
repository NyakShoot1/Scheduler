package com.example.schreduler.ui.screen.employee_create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.schreduler.data.model.Employee
import com.example.schreduler.utils.nextColor
import kotlin.random.Random

data class EmployeeCreateUiState(
    val employee: MutableState<Employee> = mutableStateOf(Employee()),
    val color: MutableState<Color> = mutableStateOf(Random.nextColor()),
)
