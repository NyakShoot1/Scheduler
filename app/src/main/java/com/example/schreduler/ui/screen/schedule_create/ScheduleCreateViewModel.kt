package com.example.schreduler.ui.screen.schedule_create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schreduler.data.room.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleCreateViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
): ViewModel() {

    private val _scheduleCreateUiState = mutableStateOf(ScheduleCreateUiState())
    val scheduleCreateUiState: State<ScheduleCreateUiState> = _scheduleCreateUiState

    private fun updateUIState(update: ScheduleCreateUiState.() -> ScheduleCreateUiState) {
        _scheduleCreateUiState.value = _scheduleCreateUiState.value.update()
    }

    fun getEmployees() = viewModelScope.launch {
        val employees = employeeRepository.getEmployees()
        updateUIState {
            for (employee in employees){
                employeesWithNotWorkingDays[employee.tupleToEmployee()] = setOf()
            }
            copy(employeesWithNotWorkingDays = employeesWithNotWorkingDays)
        }
    }
}