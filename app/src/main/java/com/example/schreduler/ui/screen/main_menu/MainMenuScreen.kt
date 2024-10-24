package com.example.schreduler.ui.screen.main_menu

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
import androidx.navigation.NavHostController
import com.example.schreduler.R
import com.example.schreduler.ui.navigation.Screen
import com.example.schreduler.ui.screen.default_components.DefaultButton

@Composable
fun MainMenuScreen(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        DefaultButton(
            textRes = R.string.employee_button_name,
            modifier = Modifier.height(60.dp).fillMaxWidth(0.85f),
            isOutlined = true
        ) {
            navController.navigate(Screen.Employees.route)
        }
        Spacer(modifier = Modifier.height(10.dp))
        DefaultButton (
            textRes = R.string.schedule_button_name,
            modifier = Modifier.height(60.dp).fillMaxWidth(0.85f),
            isOutlined = true
        ) {
            navController.navigate(Screen.Schedule.route)
        }
    }
}