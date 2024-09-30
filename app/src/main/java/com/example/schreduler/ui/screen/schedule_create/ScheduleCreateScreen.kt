package com.example.schreduler.ui.screen.schedule_create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.schreduler.ui.screen.schedule_create.components.CountEmployeesPerDayDropMenu

@Composable
fun ScheduleCreateScreen(
    viewModel: ScheduleCreateViewModel = hiltViewModel()
){
    val uiState = viewModel.scheduleCreateUiState.value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize().padding(top = 5.dp),
    ) {
        CountEmployeesPerDayDropMenu(4, uiState.countEmployeesPerDay)
    }
}