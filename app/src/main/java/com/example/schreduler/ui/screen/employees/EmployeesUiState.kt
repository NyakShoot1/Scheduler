package com.example.schreduler.ui.screen.employees

import com.example.schreduler.data.schemas.EmployeeSchema

data class EmployeesUiState(
    val employees: List<EmployeeSchema> = listOf()
)
