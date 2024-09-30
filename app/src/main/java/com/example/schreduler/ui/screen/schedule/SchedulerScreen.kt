package com.example.schreduler.ui.screen.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.schreduler.ui.navigation.Screen
import com.example.schreduler.ui.screen.schedule.components.CalendarOnMonth


@Composable
fun ScheduleScreen(
    navController: NavHostController,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.generateTestSchedule()
    }

    val uiState = viewModel.scheduleUiState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.schedule.value.isNotEmpty()) {
            CalendarOnMonth(
                days = uiState.schedule,
                currentDay = uiState.currentDay,
            )
        }
        Button(
            onClick = {
                navController.navigate(Screen.ScheduleCreate.route)
            }
        ) {
            Text("Создать расписание")
        }
    }
}