package com.example.schreduler.ui.screen.schedule.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DaysOfMonthCard(
    days: List<String>
){

    val daysOfWeek = listOf("пн", "вт", "ср", "чт", "пт", "сб", "вс") // TODO resources

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE9E4E4)
        ),
    ){
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "September", // TODO месяц автоматически
            textAlign = TextAlign.Center
        )
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(7),
            content = {
                items(daysOfWeek) { day ->
                    Text(
                        text = day,
                        textAlign = TextAlign.Center
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(7),
            content = {
                items(days) { day ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        DayCell(day = day)
                    }
                }
            },
            userScrollEnabled = false,
            verticalItemSpacing = 5.dp,
            modifier = Modifier.fillMaxSize(),
        )
    }
}