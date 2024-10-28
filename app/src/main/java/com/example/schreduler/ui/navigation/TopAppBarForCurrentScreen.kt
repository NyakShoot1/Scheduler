package com.example.schreduler.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.schreduler.ui.screen.employee_create.view.EmployeeCreateScreen
import com.example.schreduler.ui.screen.employee_create.view.composables.EmployeeCreateTopAppBar
import com.example.schreduler.ui.screen.employees.view.EmployeesScreen
import com.example.schreduler.ui.screen.employees.view.composables.EmployeesTopAppBar
import com.example.schreduler.ui.screen.mainmenu.view.MainMenuScreen
import com.example.schreduler.ui.screen.schedule.view.ScheduleScreen
import com.example.schreduler.ui.screen.schedule.view.composables.ScheduleTopAppBar
import com.example.schreduler.ui.screen.schedulecreate.view.ScheduleCreateScreen

@Composable
fun TopAppBarForCurrentScreen(currentScreen: Screen) {
    when (currentScreen) {
        is MainMenuScreen -> {  }
        is EmployeesScreen -> EmployeesTopAppBar()
        is EmployeeCreateScreen -> EmployeeCreateTopAppBar()
        is ScheduleScreen -> ScheduleTopAppBar()
        is ScheduleCreateScreen -> {  }
    }
}