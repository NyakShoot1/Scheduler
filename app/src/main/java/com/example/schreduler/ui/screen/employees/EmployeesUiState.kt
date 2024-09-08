package com.example.schreduler.ui.screen.employees

import com.example.schreduler.data.room.schemas.EmployeeSchema

data class EmployeesUiState(
    val employees: List<EmployeeSchema> = listOf()
)
