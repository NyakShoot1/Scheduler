package com.example.schreduler.ui.screen.employee_create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schreduler.data.room.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeCreateViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private val _employeeCreateUiState = mutableStateOf((EmployeeCreateUiState()))
    val employeeCreateUiState: State<EmployeeCreateUiState> = _employeeCreateUiState

    private fun updateUIState(update: EmployeeCreateUiState.() -> EmployeeCreateUiState) {
        _employeeCreateUiState.value = _employeeCreateUiState.value.update()
    }

    fun createNewEmployee() = viewModelScope.launch {
        try {
            employeeRepository.insertNewEmployee(_employeeCreateUiState.value.employee.value.toEmployeeDbEntity())
        } catch (e: Exception) {

        }
    }
}