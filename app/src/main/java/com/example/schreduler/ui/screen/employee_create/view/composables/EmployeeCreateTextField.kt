@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.schreduler.ui.screen.employee_create.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schreduler.ui.theme.SchedulerDefaultLightThemeColors

@Composable
fun EmployeeCreateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    imeAction: ImeAction
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth(0.9f)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = label,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction
            ),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.LightGray,
                unfocusedContainerColor = SchedulerDefaultLightThemeColors.defaultBackgroundElementColor,
                focusedContainerColor = SchedulerDefaultLightThemeColors.defaultBackgroundElementColor
            ),
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}