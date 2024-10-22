package com.example.schreduler.ui.screen.schedule

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schreduler.data.model.Employee
import com.example.schreduler.data.room.repository.EmployeeRepository
import com.example.schreduler.data.room.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Year
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val scheduleRepository: ScheduleRepository,
) : ViewModel() {


    private val _scheduleUiState = mutableStateOf(ScheduleUiState())
    val scheduleUiState: State<ScheduleUiState> = _scheduleUiState

    private fun updateUIState(update: ScheduleUiState.() -> ScheduleUiState) {
        _scheduleUiState.value = _scheduleUiState.value.update()
    }


    fun getSchedule() = viewModelScope.launch {
        updateUIState { copy(schedule = mutableStateOf(mapOf())) }
        try {
            val employees = employeeRepository.getEmployees().map { it.tupleToEmployee() }
            val currentSchedule =
                scheduleRepository.getSchedule(
                    YearMonth.now().monthValue,
                    Year.now().value
                )
            if (currentSchedule != null)
                updateUIState {
                    copy( //todo
//                        employees = mutableStateOf(employees),
//                        schedule = mutableStateOf(currentSchedule.schedule.mapValues { entry ->
//                            entry.value.mapNotNull { userId -> employees.find { it.id == userId } }
//                        })
                    )
                }
            else
                updateUIState { copy(schedule = schedule) }
        } catch (e: Exception) {
            Log.d("get_schedule_error", e.toString())
        }
    }

    fun getEmployeesColors(employees: List<Employee>): List<Color> {
        val colors: MutableList<Color> = mutableListOf()
        for (employee in employees) {
            colors.add(employee.color)
        }
        return colors
    }
}