package com.example.schreduler.ui.screen.main_menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.schreduler.R
import com.example.schreduler.ui.navigation.Screen
import com.example.schreduler.ui.screen.main_menu.components.MainMenuButton

@Composable
fun MainMenuScreen(
    navController: NavHostController
){
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        MainMenuButton(
            text = stringResource(id = R.string.employee_button_name)
        ) {
            navController.navigate(Screen.Employees.route)

        }
        MainMenuButton(
            text = stringResource(id = R.string.schedule_button_name)
        ) {
            navController.navigate(Screen.Schedule.route)
        }
    }
}