package com.example.schreduler.ui.screen.main_menu.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainMenuButton(
    iconId: Int,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .height(100.dp)
            .width(350.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(3.dp, Color.LightGray),
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Color(0xFFBB9B7B) // TODO Через темы
        )
        Spacer(modifier = Modifier.width(75.dp))
        Text(
            modifier = Modifier,
            text = text,
            color = Color(0xFFBB9B7B),
            fontSize = 16.sp
        )
    }
}
