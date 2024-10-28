package com.example.schreduler.ui.screen.schedulecreate.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _doneInsert = MutableLiveData<Boolean>(false)
    val doneInsert: LiveData<Boolean> = _doneInsert

    private val scheduleGenerator = ScheduleGenerator()

    fun getEmployees() = viewModelScope.launch {
        val employees = employeeRepository.getEmployees()
        updateUIState {
            copy(
                employeesWithNotWorkingDays = employees.associateTo(SnapshotStateMap()) {
                    it.tupleToEmployee() to mutableSetOf()
                }
            )
        }
    }

    fun generateSchedule() = viewModelScope.launch {
        _scheduleCreateUiState.value.startGeneration.value = true

        val daysInMonth = YearMonth.now().atEndOfMonth().dayOfMonth
        val currentMonth = YearMonth.now().month.value
        val currentYear = Year.now().value

        val employeesForSchedule: List<EmployeeForSchedule> =
            _scheduleCreateUiState.value.employeesWithNotWorkingDays.map {
                EmployeeForSchedule(
                    it.key.id,
                    it.value.toMutableSet()
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