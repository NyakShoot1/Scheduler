package com.example.schreduler.ui.screen.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(

): ViewModel() {

    private val mapOfDaysOfWeek: Map<String, Int> = mapOf(
        "MONDAY" to 0,
        "TUESDAY" to 1,
        "WEDNESDAY" to 2,
        "THURSDAY" to 3,
        "FRIDAY" to 4,
        "SATURDAY" to 5,
        "SUNDAY" to 6,
        )

    private val _scheduleUiState = mutableStateOf(SchedulerUiState())
    val scheduleUiState: State<SchedulerUiState> = _scheduleUiState

    private fun updateUIState(update: SchedulerUiState.() -> SchedulerUiState) {
        _scheduleUiState.value = _scheduleUiState.value.update()
    }

    public fun getCurrentMonth(): Calendar {
        return _scheduleUiState.value.currentMonth.value
    }

    fun generateListOfDaysForMonth(){
        val firstDayOfMonth = mapOfDaysOfWeek[LocalDate.now().withDayOfMonth(1).dayOfWeek.toString()]
        val lastDayOfMonth = LocalDate.now().lengthOfMonth()
        val newDaysOfMonth: MutableList<String> = mutableListOf()
        for (i in 1..firstDayOfMonth!!){
            newDaysOfMonth.add("")
        }
        for (i in 1..lastDayOfMonth){
            newDaysOfMonth.add(i.toString())
        }
        updateUIState {
            copy(daysOfMonth = newDaysOfMonth)
        }
    }

}