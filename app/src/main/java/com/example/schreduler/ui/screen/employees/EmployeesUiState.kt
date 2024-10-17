package com.example.schreduler.ui.screen.employees

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.schreduler.data.model.Employee

data class EmployeesUiState(
    val employees: MutableState<List<Employee>> = mutableStateOf(listOf())
)
