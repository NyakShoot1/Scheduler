package com.example.schreduler.ui.screen.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.schreduler.R
import com.example.schreduler.ui.navigation.Screen
import com.example.schreduler.ui.screen.default_components.DefaultBlueButton
import com.example.schreduler.ui.screen.schedule.components.CardEmployee
import com.example.schreduler.ui.screen.schedule.components.ScheduleWeekCalendar


@Composable
fun ScheduleScreen(
    navController: NavHostController,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getSchedule()
    }

    val uiState = viewModel.scheduleUiState.value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState.schedule.value.isNotEmpty()) {
            true -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                ) {
                    ScheduleWeekCalendar(uiState.selectedDay)
                    LazyColumn(
                        modifier = Modifier.padding(top = 6.dp)
                    ) {
                        items(uiState.schedule.value[uiState.selectedDay.value.dayOfMonth]!!) { employee ->
                            CardEmployee(employee)
                        }
                    }
                }
            }

            else -> {
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.5f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "Нет расписания на этот месяц", //todo res
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        DefaultBlueButton(
            onClick = { navController.navigate(Screen.ScheduleCreate.route) },
            textRes = R.string.create_schedule_label
        )
    }

}