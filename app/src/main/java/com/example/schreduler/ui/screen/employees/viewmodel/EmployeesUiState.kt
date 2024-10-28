package com.example.schreduler.ui.screen.employees.viewmodel

import com.example.schreduler.data.model.Employee

data class EmployeesUiState(
    val employees: List<Employee> = listOf()
)
