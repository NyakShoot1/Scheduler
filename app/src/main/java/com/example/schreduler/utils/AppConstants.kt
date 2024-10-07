package com.example.schreduler.utils

import com.example.schreduler.R
import java.time.DayOfWeek
import java.util.Calendar

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

    val MONTHS_NAME = mapOf( // todo посмотреть в дефолтной библиотеке есть ли перевод на все языки
        Calendar.JANUARY to R.string.january_label,
        Calendar.FEBRUARY to R.string.february_label,
        Calendar.MARCH to R.string.march_label,
        Calendar.APRIL to R.string.april_label,
        Calendar.MAY to R.string.may_label,
        Calendar.JUNE to R.string.june_label,
        Calendar.JULY to R.string.july_label,
        Calendar.AUGUST to R.string.august_label,
        Calendar.SEPTEMBER to R.string.september_label,
        Calendar.OCTOBER to R.string.october_label,
        Calendar.NOVEMBER to R.string.november_label,
        Calendar.DECEMBER to R.string.december_label
    )

}