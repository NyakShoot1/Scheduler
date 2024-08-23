package com.example.schreduler.ui.screen.main_menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        MainMenuButton(
            iconId = R.drawable.employee_icon,
            text = stringResource(id = R.string.employee_button_name)
        ) {
            navController.navigate(Screen.Employees.route)
        }
        MainMenuButton(
            iconId = R.drawable.calendar_icon,
            text = stringResource(id = R.string.schedule_button_name)
        ) {

        }
    }
}