package com.example.schreduler.ui.screen.employees.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schreduler.data.room.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private val _employeesUiState = MutableStateFlow(EmployeesUiState())
    val employeesUiState: StateFlow<EmployeesUiState> = _employeesUiState.asStateFlow()


    fun getEmployees() = viewModelScope.launch {
        _employeesUiState.update { state ->
            state.copy(employees = employeeRepository.getEmployees().map { it.tupleToEmployee() })
        }
    }

    fun deleteEmployee(id: Long) = viewModelScope.launch {
        try {
            employeeRepository.deleteEmployeeById(id)
            getEmployees()
        } catch (e: Exception) {
            Log.d("EmployeeDelete", e.toString())
        }
    }
}