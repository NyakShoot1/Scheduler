package com.example.schreduler.ui.screen.employee_create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf

data class EmployeeCreateUiState(
    val fullName: MutableState<String> = mutableStateOf(""),
    val position: MutableState<String> = mutableStateOf(""),
    val noWorkingDays: MutableMap<Int, String> = mutableStateMapOf()
)
