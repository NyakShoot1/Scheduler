package com.example.schreduler.ui.screen.employee_create

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _isValid = MutableLiveData<Boolean?>()
    val isValid: LiveData<Boolean?> = _isValid

    private val _isDone = MutableLiveData<Boolean>()
    val isDone: LiveData<Boolean> = _isDone

    fun checkValid() {
        _isValid.value =
            _employeeCreateUiState.value.name != "" && _employeeCreateUiState.value.surname != ""
    }

    fun createNewEmployee() = viewModelScope.launch {
        val employee = Employee(
            name = _employeeCreateUiState.value.name,
            surname = _employeeCreateUiState.value.surname,
            color = _employeeCreateUiState.value.color
        )
        try {
            employeeRepository
                .insertNewEmployee(employee.toEmployeeDbEntity())
            _isDone.value = true
        } catch (e: Exception) {
            Log.d("EmployeeCreateViewModelError", e.toString())
            _isDone.value = false
        }
    }

    fun updateColor(newColor: Color){
        _employeeCreateUiState.update {
            it.copy(color = newColor)
        }
        _isValid.value = null
    }

    fun updateName(newName: String){
        _employeeCreateUiState.update {
            it.copy(name = newName)
        }
        _isValid.value = null
    }

    fun updateSurname(newSurname: String){
        _employeeCreateUiState.update {
            it.copy(surname = newSurname)
        }
    }
}