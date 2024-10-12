package com.example.schreduler.utils

import java.util.Calendar
import javax.inject.Inject

class CalendarHandler @Inject constructor() {

    private var calendarInstance: Calendar = setCalendarInstance()

    private fun setCalendarInstance(): Calendar {
        return Calendar.getInstance()
    }

    fun refreshCalendarInstance() {
        calendarInstance = setCalendarInstance()
    }

    fun getCalendarInstance(): Calendar {
        return calendarInstance
    }

    fun getCurrentDay(): Int {
        return calendarInstance.get(Calendar.DAY_OF_MONTH)
    }

    fun getCurrentMonth(): Int {
        return calendarInstance.get(Calendar.MONTH)
    }

    fun getCurrentDaysInMonth(): Int {
        return calendarInstance.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun getCurrentYear(): Int {
        return calendarInstance.get(Calendar.YEAR)
    }

    fun getFirstDayOfWeek(): Int {
        return calendarInstance.get(Calendar.DAY_OF_WEEK)
    }
}
