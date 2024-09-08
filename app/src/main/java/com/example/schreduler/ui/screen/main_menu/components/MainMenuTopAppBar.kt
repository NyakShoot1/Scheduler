package com.example.schreduler.ui.screen.main_menu.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.schreduler.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.main_menu_title)
            )
        }
    )
}