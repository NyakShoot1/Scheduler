package com.example.schreduler.ui.screen.schedule_create

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.example.schreduler.data.model.Employee

data class ScheduleCreateUiState(
    val employeesWithNotWorkingDays: SnapshotStateMap<Employee, MutableList<Int>> = mutableStateMapOf(),
    val countEmployeesPerDay: MutableIntState = mutableIntStateOf(0)
)