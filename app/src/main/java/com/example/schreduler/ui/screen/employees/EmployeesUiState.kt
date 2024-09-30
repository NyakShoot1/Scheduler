package com.example.schreduler.ui.screen.employees

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.schreduler.data.room.schemas.EmployeeTuple

data class EmployeesUiState(
    val employees: MutableState<List<EmployeeTuple>> = mutableStateOf(listOf())
)
