package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DayCell(
    day: String,
    isSelected: Boolean,
    isCurrentDay: Boolean,
    onDayClick: (String) -> Unit
) {
    val backgroundColor = when {
        isSelected -> Color.Blue.copy(alpha = 0.3f)
        isCurrentDay -> Color.Gray.copy(alpha = 0.3f)
        else -> Color.Transparent
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .background(backgroundColor, shape = CircleShape)
            .clickable { onDayClick(day) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day,
            textAlign = TextAlign.Center,
            color = if (isSelected || isCurrentDay) Color.White else Color.Black
        )
    }
}