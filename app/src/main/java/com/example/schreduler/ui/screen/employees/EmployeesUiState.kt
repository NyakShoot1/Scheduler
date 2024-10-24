package com.example.schreduler.ui.screen.employees

import com.example.schreduler.data.model.Employee

data class EmployeesUiState(
    val employees: List<Employee> = listOf()
)
