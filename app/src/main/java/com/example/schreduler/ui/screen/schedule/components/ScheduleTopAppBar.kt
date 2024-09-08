package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.schreduler.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleTopAppBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.schedule_button_name)
            )
        },
        navigationIcon = {

        },
        actions = {

        }
    )
}