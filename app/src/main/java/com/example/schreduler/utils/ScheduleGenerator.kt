package com.example.schreduler.utils

import com.example.schreduler.data.model.EmployeeForSchedule

class ScheduleGenerator {

    fun generateSchedule(
        employees: List<EmployeeForSchedule>,
        totalDays: Int,
        employeesPerDay: Int
    ): MutableMap<Int, MutableList<Long>> {
        val schedule = mutableMapOf<Int, MutableList<Long>>()
        val employeeWorkDays = employees.associate { it.id to 0 }.toMutableMap()

        for (day in 1..totalDays) {
            val availableEmployees = employees.filter { day !in it.unavailableDays }
            var selectedEmployees =
                availableEmployees.sortedBy { employeeWorkDays[it.id] }
                    .take(employeesPerDay)

            // Если не хватает доступных сотрудников, выбираем из всех, игнорируя их предпочтения
            if (selectedEmployees.size < employeesPerDay) {
                val remainingCount = employeesPerDay - selectedEmployees.size
                val forcedEmployees = (employees - selectedEmployees.toSet())
                    .sortedBy { employeeWorkDays[it.id] }
                    .take(remainingCount)
                selectedEmployees =
                    (selectedEmployees + forcedEmployees).sortedBy { it.id }
            }

            schedule[day] = selectedEmployees.map { it.id }.toMutableList()
            selectedEmployees.forEach {
                employeeWorkDays[it.id] = employeeWorkDays[it.id]!! + 1
            }
        }

        // Балансировка расписания
        balanceSchedule(schedule, employees, employeeWorkDays)

        return schedule
    }

    private fun balanceSchedule(
        schedule: MutableMap<Int, MutableList<Long>>,
        employees: List<EmployeeForSchedule>,
        employeeWorkDays: MutableMap<Long, Int>
    ) {
        val maxIterations = 100
        var iteration = 0

        while (employeeWorkDays.values.max() - employeeWorkDays.values.min() > 1 && iteration < maxIterations) {
            val overworkedEmployees =
                employeeWorkDays.filter { it.value == employeeWorkDays.values.max() }.keys
            val underworkedEmployees =
                employeeWorkDays.filter { it.value == employeeWorkDays.values.min() }.keys

            for (day in schedule.keys) {
                val currentEmployees = schedule[day]!!
                for (overworkedId in overworkedEmployees) {
                    if (overworkedId in currentEmployees) {
                        for (underworkedId in underworkedEmployees) {
                            val underworkedEmployee =
                                employees.find { it.id == underworkedId }
                            if (underworkedId !in currentEmployees && day !in underworkedEmployee!!.unavailableDays) {
                                currentEmployees[currentEmployees.indexOf(overworkedId)] =
                                    underworkedId
                                employeeWorkDays[overworkedId] =
                                    employeeWorkDays[overworkedId]!! - 1
                                employeeWorkDays[underworkedId] =
                                    employeeWorkDays[underworkedId]!! + 1
                                break
                            }
                        }
                    }
                }
            }
            iteration++
        }
    }
}