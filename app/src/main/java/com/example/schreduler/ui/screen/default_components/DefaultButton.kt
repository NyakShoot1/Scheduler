package com.example.schreduler.ui.screen.default_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.schreduler.ui.theme.SchedulerDefaultLightThemeColors

@Composable
fun DefaultButton(
    textRes: Int,
    isUppercase: Boolean = false,
    isOutlined: Boolean = false,
    modifier: Modifier,
    transitionInProgress: Boolean = true,
    onClick: () -> Unit,
) {
    val border = BorderStroke(2.dp, SchedulerDefaultLightThemeColors.defaultButtonColor)

    TextButton(
        modifier = modifier
            .border(
                if (isOutlined) border else BorderStroke(0.dp, Color.Transparent), RoundedCornerShape(45.dp)
            ),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (!isOutlined) SchedulerDefaultLightThemeColors.defaultButtonColor else Color.Transparent,
        ),
        shape = RoundedCornerShape(45.dp),
        enabled = transitionInProgress
    ) {
        val text = stringResource(textRes)
        Text(
            text = if (isUppercase) text.uppercase() else text,
            color = if (!isOutlined)
                SchedulerDefaultLightThemeColors.defaultTextFilledButtonColor
            else
                SchedulerDefaultLightThemeColors.defaultButtonColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}