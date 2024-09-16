package com.example.schreduler.ui.screen.employees

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.schreduler.ui.screen.employees.components.EmployeeCard

@Composable
fun EmployeesScreen(
    viewModel: EmployeesViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getEmployees()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (viewModel.employeesUiState.value.employees.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(viewModel.employeesUiState.value.employees) { employee ->
                    EmployeeCard(
                        firstName = employee.fullName,
                        surname = "",
                        position = employee.position,
                        priorities = listOf(Pair("пн", 1), Pair("сб", 2)), // todo
                        status = ""
                    ){

                    }
                }
            }
        } else {
            Text(text = "Никто не работает :(")
        }
    }

}