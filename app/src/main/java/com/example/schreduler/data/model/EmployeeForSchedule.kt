package com.example.schreduler.data.model

data class EmployeeForSchedule(
    val id: Long,
    val unavailableDays: MutableSet<Int>
)
