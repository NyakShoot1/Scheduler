package com.example.schreduler.ui.screen.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.schreduler.ui.screen.schedule.components.DaysOfMonthCard


@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.generateListOfDaysForMonth()
    }

    val uiState = viewModel.scheduleUiState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DaysOfMonthCard(
            days = uiState.daysOfMonth,
            selectedDay = uiState.selectedDay.value,
            currentDay = uiState.currentDay,
            onDaySelected = { day -> viewModel.selectDay(day) }
        )
    }
}