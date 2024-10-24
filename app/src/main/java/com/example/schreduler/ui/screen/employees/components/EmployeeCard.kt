package com.example.schreduler.ui.screen.employees.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.schreduler.data.model.Employee
import com.example.schreduler.ui.theme.SchedulerDefaultLightThemeColors

@Composable
fun EmployeeCard(
    employee: Employee,
    onClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = SchedulerDefaultLightThemeColors.defaultBackgroundElementColor
        ),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(70.dp)
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(90.dp))
                        .background(employee.color)
                        .height(25.dp)
                        .width(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(employee.name + " " + employee.surname)
            }
            TextButton(
                onClick = {
                    showDialog = true
                }
            ) {
                Text(
                    text = "Удалить",
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )
            }
        }
    }
    if (showDialog)
        AcceptDialog(
            employeeName = employee.getFullName(),
            onDismissRequest = { showDialog = false },
            onAcceptRequest = { if (it) onClick() },
        )
}

@Composable
private fun AcceptDialog(
    employeeName: String,
    onDismissRequest: () -> Unit,
    onAcceptRequest: (Boolean) -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.2f),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Вы дейстивтельно хотите удалить '$employeeName'?",
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(2.dp),
                    ) { Text("Dismiss") } //todo res
                    TextButton(
                        onClick = {
                            onAcceptRequest(true)
                            onDismissRequest()
                        },
                        modifier = Modifier.padding(2.dp),
                    ) { Text("Confirm") } //todo res
                }
            }
        }
    }
}