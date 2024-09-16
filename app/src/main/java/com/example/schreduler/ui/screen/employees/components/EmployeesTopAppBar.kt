package com.example.schreduler.ui.screen.employees.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.schreduler.R
import com.example.schreduler.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeesTopAppBar(
    navController: NavHostController
) {
    val employeesTopAppBarColor = Color(0xFF2D0EE7)
    TopAppBar(
        title = {
            Text(
                "Сотрудники", // TODO Resource
                color = employeesTopAppBarColor
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = employeesTopAppBarColor
                )
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(Screen.EmployeeCreate.route)
                },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = employeesTopAppBarColor
                )
            ) {
                Icon(painter = painterResource(R.drawable.baseline_person_add_alt_24), contentDescription = "Add")
            }
        },
        modifier = Modifier.shadow(elevation = 0.5.dp)
    )
}