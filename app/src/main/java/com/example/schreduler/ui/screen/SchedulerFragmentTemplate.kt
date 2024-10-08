package com.example.schreduler.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.schreduler.ui.navigation.SchedulerNavGraph

@Composable
fun SchedulerFragmentTemplate(
    navController: NavHostController,
){
    Scaffold(
        topBar = {
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            SchedulerNavGraph(navController)
        }
    }
}