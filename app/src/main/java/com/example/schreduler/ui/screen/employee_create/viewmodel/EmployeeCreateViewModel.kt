package com.example.schreduler.ui.screen.employee_create.viewmodel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schreduler.data.model.Employee
import com.example.schreduler.data.room.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeCreateViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private val _employeeCreateUiState = MutableStateFlow((EmployeeCreateUiState()))
    val employeeCreateUiState: StateFlow<EmployeeCreateUiState> =
        _employeeCreateUiState.asStateFlow()

    private val isDataValid: Boolean
        get() = _employeeCreateUiState.value.name.isNotBlank() && _employeeCreateUiState.value.surname.isNotBlank()

    fun createNewEmployee() = viewModelScope.launch {
        if (isDataValid) {
            val employee = Employee(
                name = _employeeCreateUiState.value.name,
                surname = _employeeCreateUiState.value.surname,
                color = _employeeCreateUiState.value.color
            )
            try {
                employeeRepository
                    .insertNewEmployee(employee.toEmployeeDbEntity())
                _employeeCreateUiState.update {
                    it.copy(isDone = true)
                }
            } catch (e: Exception) {
                Log.d("EmployeeCreateViewModelError", e.toString())
                _employeeCreateUiState.update {
                    it.copy(isError = true)
                }
            }
        } else {
            _employeeCreateUiState.update {
                it.copy(isError = true)
            }
        }
    }

    fun updateColor(newColor: Color) {
        _employeeCreateUiState.update {
            it.copy(color = newColor)
        }
    }

    fun updateName(newName: String) {
        _employeeCreateUiState.update {
            it.copy(name = newName)
        }
    }

    fun updateSurname(newSurname: String) {
        _employeeCreateUiState.update {
            it.copy(surname = newSurname)
        }
    }

    fun resetErrorState() {
        _employeeCreateUiState.update { it.copy(isError = false) }
    }

    fun resetDoneState() {
        _employeeCreateUiState.update { it.copy(isDone = false) }
    }

}