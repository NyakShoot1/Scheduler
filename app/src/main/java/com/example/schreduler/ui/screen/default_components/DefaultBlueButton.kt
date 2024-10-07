package com.example.schreduler.ui.screen.default_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun DefaultBlueButton(
    onClick: () -> Unit,
    textRes: Int
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2D0EE7)
        )
    ) {
        Text(stringResource(textRes).uppercase())
    }
}