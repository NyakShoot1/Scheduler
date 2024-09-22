package com.example.schreduler.data.room.repository

import com.example.schreduler.data.room.dao.EmployeeDao
import com.example.schreduler.data.room.model.EmployeeDbEntity
import com.example.schreduler.data.room.schemas.EmployeeTuple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeDao: EmployeeDao
) {

    suspend fun insertNewEmployee(newEmployee: EmployeeDbEntity) {
        return withContext(Dispatchers.IO){
            employeeDao.insertNewEmployee(newEmployee)
        }
    }

    suspend fun getEmployees(): List<EmployeeTuple>{
        return withContext(Dispatchers.IO) {
            return@withContext employeeDao.getEmployees()
        }
    }

    suspend fun getEmployeeById(employeeId: Long): EmployeeTuple {
        return withContext(Dispatchers.IO) {
            return@withContext employeeDao.getEmployeeById(employeeId)
        }
    }

    suspend fun deleteEmployeeById(employeeId: Long) {
        return withContext(Dispatchers.IO) {
            return@withContext employeeDao.deleteEmployeeById(employeeId)
        }
    }

}