package com.example.schreduler.ui.screen.employee_create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.schreduler.R
import com.example.schreduler.ui.theme.baseLightPalette

@Composable
fun EmployeeCreateScreen(
    viewModel: EmployeeCreateViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = viewModel.employeeCreateUiState.value.fullName.value,
            onValueChange = { viewModel.employeeCreateUiState.value.fullName.value = it },
            label = { Text(text = stringResource(id = R.string.full_name_label)) }
        )
        OutlinedTextField(
            value = viewModel.employeeCreateUiState.value.position.value,
            onValueChange = { viewModel.employeeCreateUiState.value.position.value = it },
            label = { Text(text = stringResource(id = R.string.position_label)) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = baseLightPalette.buttonColor
            )
        ) {
            Text(
                text = stringResource(id = R.string.not_working_days_btn_title)
            )
        }
        Button(
            onClick = { viewModel.createNewEmployee() },
            colors = ButtonDefaults.buttonColors(
                containerColor = baseLightPalette.buttonColor
            )
        ) {
            Text(
                text = "Готово" // TODO переделать на ресурс
            )
        }
    }
}