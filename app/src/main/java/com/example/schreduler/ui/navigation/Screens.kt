package com.example.schreduler.ui.navigation

import com.example.schreduler.R

sealed class Screen(val title: String, val route: String){

    object MainMenu: Screen(R.string.main_menu_title.toString(), Destination.MAIN_MENU)

    object Employees: Screen(R.string.employees_title.toString(), Destination.EMPLOYEES)

    object Schedule: Screen(R.string.schedule_button_name.toString(), Destination.SCHEDULE)

    object EmployeeDetails: Screen("", Destination.EMPLOYEE_DETAILS)

    object EmployeeCreate: Screen(R.string.employee_create_title.toString(), Destination.EMPLOYEE_CREATE)

    object ScheduleCreate: Screen("", Destination.SCHEDULE_CREATE)
}

object Destination{

    const val MAIN_MENU = "main_menu"

    const val EMPLOYEES = "employees"
    const val SCHEDULE = "schedule"
    const val SCHEDULE_CREATE = "schedule_create"

    const val EMPLOYEE_DETAILS = "employee_details/{employee_id}"
    const val EMPLOYEE_CREATE = "employee_create"

}