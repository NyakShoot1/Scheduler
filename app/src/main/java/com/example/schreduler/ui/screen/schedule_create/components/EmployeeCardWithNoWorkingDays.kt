package com.example.schreduler.ui.screen.schedule_create.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmployeeCardWithNoWorkingDays(
    employee: Employee,
    selectedDays: MutableSet<Int>
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .padding(5.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(90.dp))
                            .background(employee.color)
                            .size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(employee.name + " " + employee.surname)
                }
                val interactionSource = remember { MutableInteractionSource() }
                Text(
                    text = if (selectedDays.isEmpty()) "Выбрать" else "Изменить",
                    modifier = Modifier
                        .clickable(interactionSource, indication = null) {
                            showDialog = true
                        },
                    color = Color.Blue,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )
            }

            if (selectedDays.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = Color.LightGray
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Нерабочие дни:",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        maxItemsInEachRow = 6
                    ) {
                        selectedDays.sorted().forEach { day ->
                            Box(
                                modifier = Modifier
                                    .padding(end = 4.dp, bottom = 4.dp)
                                    .background(
                                        color = Color.LightGray.copy(alpha = 0.3f),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .fillMaxWidth(0.1f)
                            ) {
                                Text(
                                    text = day.toString(),
                                    modifier = Modifier
                                        .padding(vertical = 4.dp, horizontal = 8.dp)
                                        .fillMaxSize(),
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    if (showDialog) {
        DaysSelectorDialog(onDismissRequest = { showDialog = false }, selectedDays = selectedDays)
    }
}

@Composable
fun DaysSelectorDialog(
    onDismissRequest: () -> Unit,
    selectedDays: MutableSet<Int>
) {
    var tempSelectedDays by remember { mutableStateOf(selectedDays.toSet()) }

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxHeight(0.5f)
                .fillMaxWidth(0.9f)
                .background(Color.White),
        ) {

            ScheduleCreateCalendar(
                selectedDays = tempSelectedDays,
                onDaysChanged = { tempSelectedDays = it.toSet() }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(2.dp),
                ) {
                    Text("Dismiss")
                }

                TextButton(
                    onClick = {
                        selectedDays.clear()
                        selectedDays.addAll(tempSelectedDays)
                        onDismissRequest()
                    },
                    modifier = Modifier.padding(2.dp),
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}