package com.example.schreduler.ui.screen.employee_create

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.schreduler.R
import com.example.schreduler.ui.screen.default_components.DefaultButton
import com.example.schreduler.ui.screen.employee_create.components.EmployeeColorSelector
import com.example.schreduler.ui.screen.employee_create.components.EmployeeCreateTextField

@Composable
fun EmployeeCreateScreen(
    navController: NavHostController,
    viewModel: EmployeeCreateViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState = viewModel.employeeCreateUiState.collectAsState()
    val isValid by viewModel.isValid.observeAsState()
    val isDone by viewModel.isDone.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        EmployeeCreateTextField(
            value = uiState.value.name,
            onValueChange = { viewModel.updateName(it) },
            label = stringResource(R.string.name_label),
            imeAction = ImeAction.Next
        )
        EmployeeCreateTextField(
            value = uiState.value.surname,
            onValueChange = { viewModel.updateSurname(it) },
            label = stringResource(R.string.surname_label),
            imeAction = ImeAction.Done
        )
        EmployeeColorSelector(uiState.value.color, onColorChange = { newColor ->
            viewModel.updateColor(newColor)
        })
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            DefaultButton(
                textRes = R.string.create_btn_label,
                isUppercase = true,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                viewModel.checkValid()
            }
        }
    }

    LaunchedEffect(isValid) {
        when (isValid) {
            true -> {
                viewModel.createNewEmployee()
            }

            false -> {
                Toast.makeText(context, "Введите имя и фамилию!", Toast.LENGTH_SHORT)
                    .show() // todo res }
            }
            null -> {}
        }
    }

    LaunchedEffect(isDone) {
        when (isDone) {
            true -> {
                navController.popBackStack()
            }

            false -> {
                Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show() // todo res} }
            }
            null -> {}
        }
    }
}
