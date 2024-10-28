package com.example.schreduler.ui.screen.mainmenu.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.schreduler.R
import com.example.schreduler.ui.screen.default_components.DefaultButton
import com.example.schreduler.ui.screen.employees.view.EmployeesScreen
import com.example.schreduler.ui.screen.mainmenu.viewmodel.MainMenuViewModel
import com.example.schreduler.ui.screen.schedule.view.ScheduleScreen

class MainMenuScreen: Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel: MainMenuViewModel = getViewModel()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            DefaultButton(
                textRes = R.string.employee_button_name,
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(0.85f),
                isOutlined = true
            ) {
                navigator.push(EmployeesScreen())
            }
            Spacer(modifier = Modifier.height(10.dp))
            DefaultButton(
                textRes = R.string.schedule_button_name,
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(0.85f),
                isOutlined = true
            ) {
                navigator.push(ScheduleScreen())
            }
        }
    }
}