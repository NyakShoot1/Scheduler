package com.example.schreduler.ui.screen.main_menu.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainMenuButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(0.9f)
            .padding(8.dp),
        shape = RoundedCornerShape(90.dp),
        border = BorderStroke(1.dp, Color(0x80BDB5B5)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        )
    ) {
        Text(
            modifier = Modifier,
            text = text,
            color = Color(0xFF2D0EE7),
            fontSize = 20.sp
        )
    }
}
