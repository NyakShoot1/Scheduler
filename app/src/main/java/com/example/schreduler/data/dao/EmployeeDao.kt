package com.example.schreduler.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.schreduler.data.model.EmployeeDbEntity
import com.example.schreduler.data.schemas.EmployeeSchema

@Dao
interface EmployeeDao {

    @Insert(entity = EmployeeDbEntity::class)
    fun insertNewEmployee(entity: EmployeeDbEntity)

    @Query("SELECT * FROM employee")
    fun getEmployees(): List<EmployeeSchema>

    @Query("SELECT * FROM employee WHERE id = :employeeId")
    fun getEmployeeById(employeeId: Long): EmployeeSchema

    @Query("DELETE FROM employee WHERE id = :employeeId")
    fun deleteEmployeeById(employeeId: Long)

}