package com.example.schreduler.ui.screen.schedulecreate.viewmodel

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.example.schreduler.data.model.Employee
import com.example.schreduler.data.model.ScheduleDay

data class ScheduleCreateUiState(
    val employeesWithNotWorkingDays: SnapshotStateMap<Employee, MutableSet<Int>> = mutableStateMapOf(),
    val countEmployeesPerDay: MutableIntState = mutableIntStateOf(0),
    val startGeneration: MutableState<Boolean> = mutableStateOf(false),
    val scheduleCreateGrid: List<List<ScheduleDay>> = listOf(),
)