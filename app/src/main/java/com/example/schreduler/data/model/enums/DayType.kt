package com.example.schreduler.data.model.enums

import androidx.compose.ui.graphics.Color

enum class DayType(val title: String, val color: Color) {
    MUST_WORK("P", color = Color(0xFF499C21)),
    WORK("P", color = Color(0xFF88D464)),
    NOT_WORK("H", color = Color(0xFFD9D9D9)),
    NOT_WANT_WORK("H", color = Color(0xFFee85b6))
}