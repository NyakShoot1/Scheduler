package com.example.schreduler.ui.screen.employees

import com.example.schreduler.data.room.schemas.EmployeeTuple

data class EmployeesUiState(
    val employees: List<EmployeeTuple> = listOf()
)
