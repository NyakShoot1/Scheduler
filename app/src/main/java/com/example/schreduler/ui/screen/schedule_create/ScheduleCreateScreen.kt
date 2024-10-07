package com.example.schreduler.ui.screen.schedule_create

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.schreduler.R
import com.example.schreduler.ui.screen.default_components.DefaultBlueButton
import com.example.schreduler.ui.screen.schedule_create.components.CountEmployeesPerDayDropMenu
import com.example.schreduler.ui.screen.schedule_create.components.EmployeeCardWithNoWorkingDays

@Composable
fun ScheduleCreateScreen(
    navController: NavHostController,
    viewModel: ScheduleCreateViewModel = hiltViewModel()
) {
    val uiState = viewModel.scheduleCreateUiState.value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getEmployees()
    }

    when (uiState.employeesWithNotWorkingDays.isNotEmpty()) {
        true -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 5.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Людей на смене: ")
                    CountEmployeesPerDayDropMenu(
                        uiState.employeesWithNotWorkingDays.size,
                        uiState.countEmployeesPerDay
                    )
                }
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f),
                ) {
                    items(uiState.employeesWithNotWorkingDays.keys.toList()) { employee ->
                        EmployeeCardWithNoWorkingDays(
                            employee,
                            uiState.employeesWithNotWorkingDays[employee]!!
                        )
                    }
                }
                DefaultBlueButton(
                    onClick = {
                        if (uiState.countEmployeesPerDay.intValue > 0) {
                            viewModel.generateSchedule() //todo проверка на создание и добавление расписания
                            navController.popBackStack()
                        } else
                            Toast.makeText(
                                context,
                                "Введите кол-во человек которые будут на смене!",
                                Toast.LENGTH_SHORT
                            ).show()
                    },
                    textRes = R.string.create_btn_label
                )
            }
        }

        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

}