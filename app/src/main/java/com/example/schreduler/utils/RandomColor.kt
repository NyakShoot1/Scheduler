package com.example.schreduler.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun Random.nextColor(): Color {
    return Color(
        red = nextFloat(),
        green = nextFloat(),
        blue = nextFloat(),
        alpha = 1f
    )
}