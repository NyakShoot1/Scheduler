package com.example.schreduler.ui.screen.employees.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.schreduler.R
import com.example.schreduler.ui.theme.baseLightPalette

@Composable
fun EmployeeCard(
    fullName: String,
    position: String,
    notWorkingDays: String,
    status: String,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.5.dp, baseLightPalette.lightTextColor),
        modifier = Modifier.size(width = 309.dp, height = 128.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = fullName) //TODO красивый текст
            Text(text = stringResource(id = R.string.position_label) + ": " + position)
            Text(text = stringResource(id = R.string.not_working_days_label) + ": " + notWorkingDays)
            Text(text = stringResource(id = R.string.status_label) + ": " + status)
        }
    }
}