package com.example.schreduler.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schreduler.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchedulerTopAppBar(

) {
    CenterAlignedTopAppBar(
        modifier = Modifier.height(62.dp),
        title = {
            Column{
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        stringResource(id = R.string.app_name),
                        modifier = Modifier.align(Alignment.TopCenter),
                        fontSize = 14.sp
                    )
                }
                // CustomTopAppBar2()
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF7C8C03),
            titleContentColor = Color(0xFF401E0A)
        )
    )
}