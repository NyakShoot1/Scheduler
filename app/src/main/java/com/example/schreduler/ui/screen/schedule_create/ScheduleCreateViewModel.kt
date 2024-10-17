package com.example.schreduler.ui.screen.schedule_create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schreduler.data.model.EmployeeForSchedule
import com.example.schreduler.data.model.Schedule
import com.example.schreduler.data.room.repository.EmployeeRepository
import com.example.schreduler.data.room.repository.ScheduleRepository
import com.example.schreduler.utils.ScheduleGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Year
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class ScheduleCreateViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val scheduleRepository: ScheduleRepository,
) : ViewModel() {

    private val _scheduleCreateUiState = mutableStateOf(ScheduleCreateUiState())
    val scheduleCreateUiState: State<ScheduleCreateUiState> = _scheduleCreateUiState

    private fun updateUIState(update: ScheduleCreateUiState.() -> ScheduleCreateUiState) {
        _scheduleCreateUiState.value = _scheduleCreateUiState.value.update()
    }

    private val _doneInsert: MutableState<Boolean> = mutableStateOf(false)
    val doneInsert: State<Boolean> = _doneInsert

    private val scheduleGenerator = ScheduleGenerator()

    fun getEmployees() = viewModelScope.launch {
        val employees = employeeRepository.getEmployees()
        updateUIState {
            for (employee in employees) {
                employeesWithNotWorkingDays[employee.tupleToEmployee()] = mutableSetOf()
            }
            copy(employeesWithNotWorkingDays = employeesWithNotWorkingDays)
        }
    }

    fun generateSchedule() = viewModelScope.launch {
        _scheduleCreateUiState.value.startGeneration.value = true

        val daysInMonth = YearMonth.now().atEndOfMonth().dayOfMonth
        val currentMonth = YearMonth.now().month.value
        val currentYear = Year.now().value

        val employeesForSchedule: MutableList<EmployeeForSchedule> = mutableListOf() //todo map

        for (employee in _scheduleCreateUiState.value.employeesWithNotWorkingDays) {
            employeesForSchedule.add(
                EmployeeForSchedule(
                    employee.key.id,
                    employee.value.toMutableSet()
                )
            )
        }

        val schedule = Schedule(
            schedule =
            scheduleGenerator.generateSchedule(
                employeesForSchedule.shuffled(),
                daysInMonth,
                _scheduleCreateUiState.value.countEmployeesPerDay.intValue
            ),
            month = currentMonth,
            year = currentYear,
        )

        try {
            scheduleRepository.insertNewScheduler(
                schedule.toScheduleDbEntity()
            )
            _doneInsert.value = true
        } catch (e: Exception) {
            _doneInsert.value = false
            _scheduleCreateUiState.value.startGeneration.value = false
        }
    }
}