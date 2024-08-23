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
import com.example.schreduler.ui.theme.SchedulerTheme
import com.example.schreduler.utils.currentRoute

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
                            Screen.MainMenu.route -> SchedulerTopAppBar()
                            Screen.Employees.route -> EmployeesTopAppBar()
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