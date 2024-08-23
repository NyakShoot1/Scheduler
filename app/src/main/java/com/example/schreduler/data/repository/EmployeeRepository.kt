package com.example.schreduler.data.repository

import com.example.schreduler.data.dao.EmployeeDao
import com.example.schreduler.data.model.EmployeeDbEntity
import com.example.schreduler.data.schemas.EmployeeSchema
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeDao: EmployeeDao
) {

    suspend fun insertNewEmployee(newEmployee: EmployeeDbEntity) =
        employeeDao.insertNewEmployee(newEmployee)

    suspend fun getEmployees() = employeeDao.getEmployees()

    suspend fun getEmployeeById(employeeId: Long): EmployeeSchema =
        employeeDao.getEmployeeById(employeeId)

    suspend fun deleteEmployeeById(employeeId: Long) = employeeDao.deleteEmployeeById(employeeId)

}