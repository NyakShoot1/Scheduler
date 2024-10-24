package com.example.schreduler.ui.screen.employee_create

import androidx.compose.ui.graphics.Color
import com.example.schreduler.utils.nextColor
import kotlin.random.Random

data class EmployeeCreateUiState(
    val name: String = "",
    val surname: String = "",
    val color: Color = Random.nextColor(),
)
