package com.example.schreduler.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.schreduler.ui.screen.employees.EmployeesScreen
import com.example.schreduler.ui.screen.main_menu.MainMenuScreen

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

        }
    }
}