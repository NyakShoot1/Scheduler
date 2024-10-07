package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.schreduler.data.model.Employee
import com.example.schreduler.ui.screen.schedule.ScheduleViewModel

@Composable
fun DayCell(
    day: String,
    isCurrentDay: Boolean,
    employees: List<Employee>,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val colors = viewModel.getEmployeesColors(employees)

    var showDialog by remember { mutableStateOf(false) }
    val backgroundColor = when {
        isCurrentDay -> Color.Gray.copy(alpha = 0.2f)
        else -> Color.Transparent
    }

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .aspectRatio(1f)
//            .padding(2.dp)
            .background(backgroundColor, shape = CircleShape)
            .clickable { showDialog = true },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = day,
                textAlign = TextAlign.Center,
                color = if (isCurrentDay) Color.White else Color.Black
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                if (colors.size > 2){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(color = Color.Blue, shape = CircleShape)
                            .size(12.dp)
                            .clip(CircleShape)
                    ){
                        Text( //todo fix отображение
                            text = colors.size.toString(),
                            color = Color.White,
                            fontSize = 8.sp,
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }else{
                    Box(
                        modifier = Modifier
                            .background(colors[0], shape = CircleShape)
                            .size(7.dp)
                    )
                    Box(
                        modifier = Modifier
                            .background(colors[1], shape = CircleShape)
                            .size(7.dp)
                    )
                }


            }
        }
    }

    if (showDialog) {
        WorkingEmployeesDialog(employees = employees, onDismissRequest = { showDialog = false })
    }
}


@Composable
fun WorkingEmployeesDialog(onDismissRequest: () -> Unit, employees: List<Employee>) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        LazyColumn(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxHeight(0.5f)
                .fillMaxWidth(0.9f)
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            userScrollEnabled = false
        ) {
            items(employees) { employee ->
                Row() {
                    Box(modifier = Modifier.background(employee.color, CircleShape))
                    Text(employee.getFullName())
                }
            }
        }
    }
}