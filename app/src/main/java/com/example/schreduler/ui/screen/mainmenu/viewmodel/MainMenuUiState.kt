package com.example.schreduler.ui.screen.mainmenu.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.schreduler.data.model.Employee

data class MainMenuUiState (
    val employeesToday: MutableState<List<Employee>> = mutableStateOf(listOf())
)