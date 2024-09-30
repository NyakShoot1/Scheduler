package com.example.schreduler.ui.screen.schedule_create.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun CountEmployeesPerDayDropMenu(countEmployees: Int, countEmployeesPerDay: MutableIntState) {
    var dmExpended by remember { mutableStateOf(false) }

    val icon = if (dmExpended)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    OutlinedTextField(
        value = countEmployeesPerDay.intValue.toString(),
        onValueChange = { countEmployeesPerDay.intValue = it.toInt() },
        modifier = Modifier
            .fillMaxWidth(0.3f),
        label = { Text("") },
        trailingIcon = {
            Icon(icon, "contentDescription",
                Modifier.clickable { dmExpended = !dmExpended }
            )
        }
    )

    DropdownMenu(
        expanded = dmExpended,
        onDismissRequest = { dmExpended = false },
    ) {
        for (i in 1..countEmployees) {
            DropdownMenuItem(
                text = { Text(i.toString()) },
                onClick = {
                    countEmployeesPerDay.intValue = i
                    dmExpended = false
                }
            )
        }
    }
}