package com.example.schreduler.ui.screen.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schreduler.data.model.Employee
import com.example.schreduler.data.room.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private val mapOfDaysOfWeek: Map<String, Int> = mapOf(
        "MONDAY" to 0,
        "TUESDAY" to 1,
        "WEDNESDAY" to 2,
        "THURSDAY" to 3,
        "FRIDAY" to 4,
        "SATURDAY" to 5,
        "SUNDAY" to 6,
    )

    private val _testSchedule: MutableMap<Int, List<Employee>> = mutableMapOf()

    private val _scheduleUiState = mutableStateOf(SchedulerUiState())
    val scheduleUiState: State<SchedulerUiState> = _scheduleUiState

    private fun updateUIState(update: SchedulerUiState.() -> SchedulerUiState) {
        _scheduleUiState.value = _scheduleUiState.value.update()
    }

    fun generateTestSchedule() = viewModelScope.launch {
        for (i in 1..Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)) {
            _testSchedule[i] = listOf(
                employeeRepository.getEmployeeById(Random.nextLong(1, 5)).tupleToEmployee(),
                employeeRepository.getEmployeeById(Random.nextLong(1, 5)).tupleToEmployee()
            )
        }
        updateUIState {
            copy(
                schedule = mutableStateOf(_testSchedule)
            )
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