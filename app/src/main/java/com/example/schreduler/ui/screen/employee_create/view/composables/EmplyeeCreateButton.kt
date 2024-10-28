package com.example.schreduler.ui.screen.employee_create.view.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.schreduler.R

@Composable
fun EmployeeCreateButton(
    onClick: () -> Unit
){
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
        Text(stringResource(R.string.create_btn_label).uppercase())
    }
}