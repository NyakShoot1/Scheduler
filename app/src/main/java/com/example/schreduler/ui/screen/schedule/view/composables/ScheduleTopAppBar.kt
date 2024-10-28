package com.example.schreduler.ui.screen.schedule.view.composables

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
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.schreduler.ui.theme.SchedulerDefaultLightThemeColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleTopAppBar() {

    val navigator = LocalNavigator.currentOrThrow

    CenterAlignedTopAppBar(
        title = {
            Text(
                "Расписание", // TODO Resource
                color = SchedulerDefaultLightThemeColors.defaultTextOutlinedButton // todo
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navigator.pop() },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = SchedulerDefaultLightThemeColors.defaultTextOutlinedButton // todo
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