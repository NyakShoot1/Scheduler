package com.example.schreduler.ui.screen.employees

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schreduler.data.room.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
): ViewModel() {

    private val _employeesUiState = mutableStateOf(EmployeesUiState())
    val employeesUiState: State<EmployeesUiState> = _employeesUiState

    private fun updateUIState(update: EmployeesUiState.() -> EmployeesUiState) {
        _employeesUiState.value = _employeesUiState.value.update()
    }

    fun getEmployees() = viewModelScope.launch {
        val listEmployees = employeeRepository.getEmployees()
        updateUIState {
            copy(employees = listEmployees)
        }
    }
}