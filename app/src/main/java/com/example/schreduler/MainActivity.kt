package com.example.schreduler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.schreduler.ui.components.SchedulerTopAppBar
import com.example.schreduler.ui.navigation.SchedulerNavGraph
import com.example.schreduler.ui.navigation.Screen
import com.example.schreduler.ui.screen.employees.components.EmployeesTopAppBar
import com.example.schreduler.ui.screen.main_menu.components.MainMenuTopAppBar
import com.example.schreduler.ui.theme.SchedulerTheme
import com.example.schreduler.utils.currentRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SchedulerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        when(currentRoute(navController)){
                            Screen.MainMenu.route -> MainMenuTopAppBar()
                            Screen.Employees.route -> EmployeesTopAppBar(navController)
                            Screen.Schedule.route -> SchedulerTopAppBar()
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        SchedulerNavGraph(navController)
                    }
                }
            }
        }
    }
}