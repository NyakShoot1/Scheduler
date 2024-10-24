package com.example.schreduler.ui.screen.schedule

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schreduler.data.model.ScheduleDay
import com.example.schreduler.data.model.enums.CalendarType
import com.example.schreduler.data.model.enums.DayType
import com.example.schreduler.data.room.repository.EmployeeRepository
import com.example.schreduler.data.room.repository.ScheduleRepository
import com.example.schreduler.data.room.schemas.ScheduleTuple
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.YearMonth
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val scheduleRepository: ScheduleRepository,
) : ViewModel() {

    private val _scheduleUiState = MutableStateFlow(ScheduleUiState())
    val scheduleUiState: StateFlow<ScheduleUiState> = _scheduleUiState.asStateFlow()

    private val scheduleCache = mutableMapOf<YearMonth, ScheduleTuple?>()

    init {
        loadInitialData()
    }

    private fun loadInitialData() = viewModelScope.launch {
        try {
            val employees = employeeRepository.getEmployees().map { it.tupleToEmployee() }
            loadScheduleRange(_scheduleUiState.value.currentSelectedYearMonth)

            _scheduleUiState.update {
                it.copy(
                    employees = employees,
                    scheduleIsLoaded = true
                )
            }
            val schedule1 = generateTestSchedule(30)
            val schedule2 = generateTestSchedule(31)
            val schedule3 = generateTestSchedule(30)

            scheduleCache[YearMonth.now().minusMonths(1)] = schedule1
            scheduleCache[YearMonth.now()] = schedule2
            scheduleCache[YearMonth.now().plusMonths(1)] = schedule3

            Log.d("test", scheduleCache.toString())
        } catch (e: Exception) {
            Log.e("ScheduleViewModel", "Error loading initial data", e)
            _scheduleUiState.update { currentState ->
                currentState.copy(
                    scheduleIsLoaded = true,
                    error = e.message
                )
            }
        }
    }

    private suspend fun loadScheduleRange(centerMonth: YearMonth) = viewModelScope.launch {
        val previousMonth = centerMonth.minusMonths(1)
        val nextMonth = centerMonth.plusMonths(1)

        val scheduleLoaders = listOf(
            async { loadSingleMonthSchedule(previousMonth) },
            async { loadSingleMonthSchedule(centerMonth) },
            async { loadSingleMonthSchedule(nextMonth) }
        )
        scheduleLoaders.awaitAll()

        updateScheduleState(centerMonth)
    }

    private suspend fun loadSingleMonthSchedule(yearMonth: YearMonth) {
        try {
            if (!scheduleCache.containsKey(yearMonth)) {
                val schedule = scheduleRepository.getSchedule(
                    yearMonth.monthValue,
                    yearMonth.year
                )
                schedule?.let {
                    scheduleCache[yearMonth] = it
                }
            }
        } catch (e: Exception) {
            Log.e("ScheduleViewModel", "Error loading schedule for $yearMonth", e)
        }
    }

    private fun updateScheduleState(centerMonth: YearMonth) {
        val currentSchedule = scheduleCache[centerMonth]

        _scheduleUiState.update { currentState ->
            currentState.copy(
                currentSelectedYearMonth = centerMonth,
                schedule = currentSchedule?.schedule ?: mapOf(),
            )
        }
    }

    fun navigateToMonth(offset: Int) {
        viewModelScope.launch {
            val currentYearMonth = _scheduleUiState.value.currentSelectedYearMonth

            val targetMonth = currentYearMonth.plusMonths(offset.toLong())

            if (offset > 0) {
                loadSingleMonthSchedule(targetMonth.plusMonths(1))
            } else if (offset < 0) {
                loadSingleMonthSchedule(targetMonth.minusMonths(1))
            }

            updateScheduleState(targetMonth)
        }
    }

    fun clearOldCache() {
        val currentYearMonth = _scheduleUiState.value.currentSelectedYearMonth

        scheduleCache.keys.toList().forEach { yearMonth ->
            if (yearMonth < currentYearMonth.minusMonths(2) ||
                yearMonth > currentYearMonth.plusMonths(2)
            ) {
                scheduleCache.remove(yearMonth)
            }
        }
        Log.d("CLEAR", scheduleCache.toString())
    }

    fun updateCalendarType(calendarType: CalendarType) {
        _scheduleUiState.update {
            it.copy(selectedTypeOfCalendar = calendarType)
        }
    }

    fun generateTestSchedule(daysInMonth: Int = 31): ScheduleTuple {
        return ScheduleTuple(
            schedule = (1..daysInMonth).associateWith { day ->
                generateDaySchedule()
            }
        )
    }

    private fun generateDaySchedule(): List<ScheduleDay> {
        // Случайное количество сотрудников в день (от 1 до 4)
        val numberOfEmployees = Random.nextInt(1, 4)

        // Получаем случайный набор ID сотрудников
        val selectedEmployees = (1L..4L).shuffled().take(numberOfEmployees)

        return selectedEmployees.map { employeeId ->
            ScheduleDay(
                employeeId = employeeId,
                dayType = DayType.entries.random()
            )
        }
    }
}