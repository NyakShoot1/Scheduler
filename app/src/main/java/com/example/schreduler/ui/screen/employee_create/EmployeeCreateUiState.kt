package com.example.schreduler.ui.screen.employee_create

import androidx.collection.MutableIntIntMap
import androidx.collection.MutableIntList
import androidx.collection.mutableIntIntMapOf
import androidx.collection.mutableIntListOf
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

data class EmployeeCreateUiState(
    val firstName: MutableState<String> = mutableStateOf(""),
    val surname: MutableState<String> = mutableStateOf(""),
    val position: MutableState<String> = mutableStateOf(""),
    val color: MutableState<Color> = mutableStateOf(Color.Green), // todo random
    val noWorkingDays: MutableIntList = mutableIntListOf(),
    val priority: MutableIntIntMap = mutableIntIntMapOf()
)
