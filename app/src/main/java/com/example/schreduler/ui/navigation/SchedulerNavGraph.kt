package com.example.schreduler.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.schreduler.ui.screen.employee_create.EmployeeCreateScreen
import com.example.schreduler.ui.screen.employees.EmployeesScreen
import com.example.schreduler.ui.screen.main_menu.MainMenuScreen
import com.example.schreduler.ui.screen.schedule.ScheduleScreen

@Composable
fun SchedulerNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.MainMenu.route
    ){
        composable(Screen.MainMenu.route){
            MainMenuScreen(navController)
        }
        composable(Screen.Employees.route){
            EmployeesScreen()
        }
        composable(Screen.Schedule.route){
            ScheduleScreen()
        }
        composable(Screen.EmployeeCreate.route){
            EmployeeCreateScreen()
        }
    }
}