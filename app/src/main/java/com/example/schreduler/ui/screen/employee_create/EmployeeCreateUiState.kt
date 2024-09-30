package com.example.schreduler.ui.screen.employee_create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.schreduler.utils.nextColor
import kotlin.random.Random

data class EmployeeCreateUiState(
    val name: MutableState<String> = mutableStateOf(""),
    val surname: MutableState<String> = mutableStateOf(""),
    val color: MutableState<Color> = mutableStateOf(Random.nextColor()),
)
