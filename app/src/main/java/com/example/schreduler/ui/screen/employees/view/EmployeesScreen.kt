package com.example.schreduler.ui.screen.employees.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.schreduler.ui.screen.employees.view.composables.EmployeeCard
import com.example.schreduler.ui.screen.employees.viewmodel.EmployeesViewModel

class EmployeesScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel: EmployeesViewModel = getViewModel()

        val uiState = viewModel.employeesUiState.collectAsState()

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
            when (uiState.value.employees.isNotEmpty()) {
                true -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(uiState.value.employees) { employee ->
                            EmployeeCard(
                                employee = employee
                            ) {
                                viewModel.deleteEmployee(employee.id)
                            }
                        }
                    }
                }

                false -> {
                    Text("Никто не работатет :(") // todo res
                }
            }

        }
    }
}