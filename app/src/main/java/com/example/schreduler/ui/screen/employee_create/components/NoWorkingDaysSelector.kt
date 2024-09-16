package com.example.schreduler.ui.screen.employee_create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoWorkingDaysSelector() {
    val days = remember { mutableStateListOf(false, false, false, false, false, false, false) }
    val daysName = listOf("пн", "вт", "ср", "чт", "пт", "сб", "вс") // todo resource

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth(0.9f)
            .padding(10.dp)
    ) {
        Column {
            Text(
                text = "Нерабочие дни (максимум 3 дня)", // TODO Resource
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                days.forEachIndexed { index, checked ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            daysName[index],
                            textAlign = TextAlign.Center
                        )
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { isChecked ->
                                days[index] = isChecked
                            },
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}