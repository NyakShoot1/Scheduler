package com.example.schreduler.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.schreduler.data.room.model.EmployeeDbEntity
import com.example.schreduler.data.room.schemas.EmployeeTuple

@Dao
interface EmployeeDao {

    @Insert(entity = EmployeeDbEntity::class)
    fun insertNewEmployee(entity: EmployeeDbEntity)

    @Query("SELECT * FROM employee")
    fun getEmployees(): List<EmployeeTuple>

    @Query("SELECT * FROM employee WHERE id = :employeeId")
    fun getEmployeeById(employeeId: Long): EmployeeTuple

    @Query("DELETE FROM employee WHERE id = :employeeId")
    fun deleteEmployeeById(employeeId: Long)

}