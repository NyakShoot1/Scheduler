package com.example.schreduler.data.room.schemas

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import com.example.schreduler.data.model.Employee

data class EmployeeTuple( // Класс для вытягивания данных из БД
    val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "color") val color: Int
) {
    fun tupleToEmployee(): Employee {
        return Employee(id = id, name = name, surname = surname, color = Color(color))
    }
}
