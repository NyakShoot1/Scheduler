package com.example.schreduler.ui.screen.employees.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schreduler.R

@Composable
fun EmployeeCard(
    firstName: String,
    surname: String,
    position: String,
    priorities: List<Pair<String, Int>>,
    status: String,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$firstName $surname",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Text(
                        text = position,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Icon(
                        painter = painterResource(R.drawable.baseline_badge_24),
                        contentDescription = "Employee",
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Приоритет нерабочих дней:",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    priorities.forEach { (day, priority) ->
                        SelectedPriorityDay(
                            day = day,
                            priority = priority
                        ) // TODO Сделать видимыми ближайшие рабочие дни
                    }
                }
                val interactionSource = remember { MutableInteractionSource() }
                Text(
                    text = "Открыть",
                    modifier = Modifier
                        .clickable(interactionSource, indication = null) {
                            onClick()
                        },
                    color = Color.Blue,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
fun SelectedPriorityDay(day: String, priority: Int) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(text = day, fontSize = 12.sp)
        }
        Text(text = priority.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}