package com.example.schreduler.ui.screen.employee_create

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.schreduler.R
import com.example.schreduler.ui.screen.employee_create.components.EmployeeColorSelector
import com.example.schreduler.ui.screen.employee_create.components.EmployeeCreateTextField

@Composable
fun EmployeeCreateScreen(
    navController: NavHostController,
    viewModel: EmployeeCreateViewModel = hiltViewModel()
) {
    val uiState = viewModel.employeeCreateUiState.value
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        EmployeeCreateTextField(
            value = uiState.name.value,
            onValueChange = { uiState.name.value = it },
            label = "Имя", // TODO Resource
            imeAction = ImeAction.Next
        )
        EmployeeCreateTextField(
            value = uiState.surname.value,
            onValueChange = { uiState.surname.value = it },
            label = "Фамилия", // TODO Resource
            imeAction = ImeAction.Done
        )
        EmployeeColorSelector()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                onClick = {
                    if (uiState.name.value != "" && uiState.surname.value != "") {
                        viewModel.createNewEmployee()
                        navController.popBackStack()
                    }
                    else
                        Toast.makeText(context, "Не все поля заполнены!", Toast.LENGTH_LONG).show() //todo resource
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2D0EE7)
                )
            ) {
                Text(stringResource(R.string.create_btn_label).uppercase())
            }
        }

    }
}