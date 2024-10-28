package com.example.schreduler.ui.screen.schedule.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.schreduler.R
import com.example.schreduler.data.model.enums.CalendarType
import com.example.schreduler.ui.screen.default_components.DefaultButton
import com.example.schreduler.ui.screen.schedule.view.composables.MonthNavigator
import com.example.schreduler.ui.screen.schedule.view.composables.ScheduleCalendar
import com.example.schreduler.ui.screen.schedule.view.composables.ScheduleGridCalendar
import com.example.schreduler.ui.screen.schedule.view.composables.TypeCalendarSelector
import com.example.schreduler.ui.screen.schedule.viewmodel.ScheduleViewModel
import com.example.schreduler.ui.screen.schedulecreate.view.ScheduleCreateScreen
import com.example.schreduler.ui.theme.SchedulerDefaultLightThemeColors
import kotlinx.coroutines.delay

class ScheduleScreen: Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        var transitionInProgress by remember { mutableStateOf(true) }

        val viewModel: ScheduleViewModel = getViewModel()
        val uiState by viewModel.scheduleUiState.collectAsState()
        val currentYearMonth by viewModel.currentSelectedYearMonth.collectAsState()

        LaunchedEffect(currentYearMonth) {
            viewModel.clearOldCache()
        }

        LaunchedEffect(Unit) {
            delay(500)
            transitionInProgress = false
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.98f)
                    .fillMaxHeight(0.9f)
                    .background(
                        SchedulerDefaultLightThemeColors.defaultBackgroundElementColor,
                        RoundedCornerShape(10.dp)
                    )
                    .padding(top = 10.dp, bottom = 10.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    TypeCalendarSelector(
                        selectedType = uiState.selectedTypeOfCalendar,
                        onDefaultCalendar = { viewModel.updateCalendarType(CalendarType.DEFAULT_CALENDAR) },
                        onGridCalendar = { viewModel.updateCalendarType(CalendarType.GRID_CALENDAR) }
                    )
                    MonthNavigator(
                        currentYearMonth = currentYearMonth,
                        onNextMonth = { viewModel.navigateToMonth(1) },
                        onPreviousMonth = { viewModel.navigateToMonth(-1) }
                    )

                    when {
                        !uiState.scheduleIsLoaded -> { CircularProgressIndicator() }
                        uiState.scheduleIsLoaded &&
                                uiState.selectedTypeOfCalendar == CalendarType.DEFAULT_CALENDAR &&
                                uiState.schedule.isNotEmpty() -> {
                            ScheduleCalendar(uiState.schedule, uiState.employees, currentYearMonth)
                        }

                        uiState.scheduleIsLoaded &&
                                uiState.selectedTypeOfCalendar == CalendarType.GRID_CALENDAR &&
                                uiState.schedule.isNotEmpty() -> {
                            ScheduleGridCalendar(uiState.schedule, uiState.employees, Modifier.fillMaxHeight(0.8f))
                        }

                        uiState.scheduleIsLoaded &&
                                uiState.schedule.isEmpty() &&
                                uiState.selectedTypeOfCalendar == CalendarType.DEFAULT_CALENDAR -> {
                            ScheduleCalendar(uiState.schedule, uiState.employees, currentYearMonth)
                            Text("Нет расписания на этот месяц")
                        }
                        uiState.scheduleIsLoaded && uiState.schedule.isEmpty() &&
                                uiState.selectedTypeOfCalendar == CalendarType.GRID_CALENDAR -> {
                            Text("Нет расписания на этот месяц") // TODO res
                        }
                    }
                }
            }
            DefaultButton(
                textRes = R.string.create_schedule_label,
                isUppercase = true,
                modifier = Modifier.fillMaxWidth(0.98f),
                transitionInProgress = !transitionInProgress
            ) {
                navigator.push(ScheduleCreateScreen())
            }
        }
    }
}