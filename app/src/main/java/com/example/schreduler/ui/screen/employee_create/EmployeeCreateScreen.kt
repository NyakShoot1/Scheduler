package com.example.schreduler.ui.screen.employee_create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.schreduler.ui.screen.employee_create.components.EmployeeColorSelector
import com.example.schreduler.ui.screen.employee_create.components.EmployeeCreateTextField

@Composable
fun EmployeeCreateScreen(
    viewModel: EmployeeCreateViewModel = hiltViewModel()
){
    val uiState = viewModel.employeeCreateUiState

    Column(
        modifier = Modifier.fillMaxSize().padding(top=10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        EmployeeCreateTextField(
            value = viewModel.employeeCreateUiState.value.employee.value.name,
            onValueChange = { viewModel.employeeCreateUiState.value.employee.value.name = it },
            label = "Имя", // TODO Resource
            imeAction = ImeAction.Next
        )
        EmployeeCreateTextField(
            value = viewModel.employeeCreateUiState.value.employee.value.surname,
            onValueChange = { viewModel.employeeCreateUiState.value.employee.value.surname = it },
            label = "Фамилия", // TODO Resource
            imeAction = ImeAction.Done
        )
        EmployeeColorSelector(uiState.value.color)
    }
}