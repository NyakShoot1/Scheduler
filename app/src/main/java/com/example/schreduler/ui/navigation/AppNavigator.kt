package com.example.schreduler.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.schreduler.ui.screen.mainmenu.view.MainMenuScreen

@Composable
fun AppNavigator(){
    Navigator(MainMenuScreen())
}
