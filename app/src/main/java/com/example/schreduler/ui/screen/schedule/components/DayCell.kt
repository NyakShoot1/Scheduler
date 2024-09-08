package com.example.schreduler.ui.screen.schedule.components

import android.content.res.Resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.schreduler.utils.convertPixelsToDp

@Composable
fun DayCell(
    day: String,
) {
    val context = LocalContext.current
    val heightOfCell = convertPixelsToDp(Resources.getSystem().displayMetrics.heightPixels.toFloat(), context = context)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = day)
    }
}