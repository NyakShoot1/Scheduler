package com.example.schreduler.ui.screen.employee_create.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.schreduler.R
import com.example.schreduler.ui.screen.default_components.DefaultButton
import com.example.schreduler.ui.screen.employee_create.view.composables.EmployeeColorSelector
import com.example.schreduler.ui.screen.employee_create.view.composables.EmployeeCreateTextField
import com.example.schreduler.ui.screen.employee_create.viewmodel.EmployeeCreateViewModel

class EmployeeCreateScreen: Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel: EmployeeCreateViewModel = getViewModel()
        val uiState = viewModel.employeeCreateUiState.collectAsState()

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
            EmployeeColorSelector(uiState.value.color, onColorChange = viewModel::updateColor)
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
                    viewModel.createNewEmployee()
                }
            }
        }

        if (uiState.value.isDone) {
            navigator.pop()
            viewModel.resetDoneState()
        }

        if (uiState.value.isError) {
            Toast.makeText(LocalContext.current, "Ошибка", Toast.LENGTH_SHORT).show()
            viewModel.resetErrorState()
        }
    }
}