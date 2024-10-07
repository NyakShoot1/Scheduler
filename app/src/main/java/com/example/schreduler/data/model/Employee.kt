package com.example.schreduler.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.schreduler.data.room.model.EmployeeDbEntity
import com.example.schreduler.utils.nextColor
import kotlin.random.Random

data class Employee(
    val id: Long = 0,
    var name: String = "",
    var surname: String = "",
    var color: Color = Random.nextColor()
){
    fun toEmployeeDbEntity(): EmployeeDbEntity {
        return EmployeeDbEntity(
            id = 0, // Автогенерация в БД
            name = name,
            surname = surname,
            color = color.toArgb()
        )

    }

    fun toEmployeeForSchedule(): EmployeeForSchedule{
        return EmployeeForSchedule(id, mutableSetOf())
    }

    fun getFullName(): String{
        return "$name $surname"
    }
}
