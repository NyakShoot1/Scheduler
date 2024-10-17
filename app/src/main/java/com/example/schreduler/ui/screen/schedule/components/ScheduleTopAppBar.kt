package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleTopAppBar(
    navController: NavHostController
) {
    val scheduleTopAppBarColor = Color(0xFF2D0EE7)
    CenterAlignedTopAppBar(
        title = {
            Text(
                "Расписание", // TODO Resource
                color = scheduleTopAppBarColor
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = scheduleTopAppBarColor
                )
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {

        },
        modifier = Modifier.shadow(elevation = 0.5.dp)
    )
}