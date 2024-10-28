package com.example.schreduler.ui.screen.employee_create.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun EmployeeColorSelector(
    color: Color,
    onColorChange: (Color) -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Цвет") // todo resource
        Spacer(modifier = Modifier.width(5.dp))
        Box(
            modifier = Modifier
                .height(35.dp)
                .width(120.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(color)
                .clickable { showDialog = true }
        )
    }
    if (showDialog) {
        ColorPicker(onDismissRequest = { showDialog = false }, onColorChange = { newColor ->
            onColorChange(newColor)
        })
    }
}

@Composable
private fun ColorPicker(
    onDismissRequest: () -> Unit,
    onColorChange: (Color) -> Unit
) {
    val controller = rememberColorPickerController()
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxHeight(0.8f)
                .fillMaxWidth(0.9f)
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(30.dp)
                    .clip(RoundedCornerShape(6.dp)),
                controller = controller
            )
            HsvColorPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp),
                controller = controller,
                onColorChanged = { }
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AlphaSlider(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(5.dp)
                        .height(35.dp),
                    controller = controller,
                    tileOddColor = Color.White,
                    tileEvenColor = Color.Black
                )
                BrightnessSlider(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(5.dp)
                        .height(35.dp),
                    controller = controller,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.Center,
            ) {
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(2.dp),
                ) { Text("Dismiss") } //todo
                TextButton(
                    onClick = {
                        onColorChange(controller.selectedColor.value)
                        onDismissRequest()
                    },
                    modifier = Modifier.padding(2.dp),
                ) { Text("Confirm") }
            }
        }
    }
}