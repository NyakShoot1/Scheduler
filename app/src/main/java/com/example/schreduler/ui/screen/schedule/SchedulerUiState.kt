package com.example.schreduler.ui.screen.schedule

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.schreduler.data.model.Event
import java.util.Calendar

data class SchedulerUiState(
    val schedule: MutableState<String> = mutableStateOf(""),
    val events: MutableList<Event> = mutableListOf(),
    val currentMonth: MutableState<Calendar> = mutableStateOf(Calendar.getInstance()), // #TODO Под вопросом
    val daysOfMonth: MutableList<String> = mutableListOf(),
    val selectedDateEvent: MutableState<List<Event>> = mutableStateOf(listOf()),

    val selectedDay: MutableState<String?> = mutableStateOf(null),
    val currentDay: String = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
)
