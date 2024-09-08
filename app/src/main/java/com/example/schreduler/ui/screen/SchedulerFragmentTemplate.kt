package com.example.schreduler.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.schreduler.ui.components.SchedulerTopAppBar
import com.example.schreduler.ui.navigation.SchedulerNavGraph
import com.example.schreduler.ui.navigation.Screen
import com.example.schreduler.utils.currentRoute

@Composable
fun SchedulerFragmentTemplate(
    navController: NavHostController,
){
    Scaffold(
        topBar = {
            when(currentRoute(navController)){
                Screen.MainMenu.route -> SchedulerTopAppBar()
//                Screen.Employees.route -> EmployeesTopAppBar()
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            SchedulerNavGraph(navController)
        }
    }
}