package com.example.schreduler.utils

import com.example.schreduler.R
import java.time.DayOfWeek

object AppConstants {

    val DAYS_NAME = mapOf(
        DayOfWeek.MONDAY to R.string.monday_label,
        DayOfWeek.TUESDAY to R.string.tuesday_label,
        DayOfWeek.WEDNESDAY to R.string.wednesday_label,
        DayOfWeek.THURSDAY to R.string.thursday_label,
        DayOfWeek.FRIDAY to R.string.friday_label,
        DayOfWeek.SATURDAY to R.string.saturday_label,
        DayOfWeek.SUNDAY to R.string.sunday_label
    )

}