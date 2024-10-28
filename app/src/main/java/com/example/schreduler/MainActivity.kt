package com.example.schreduler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.example.schreduler.ui.navigation.TopAppBarForCurrentScreen
import com.example.schreduler.ui.screen.mainmenu.view.MainMenuScreen
import com.example.schreduler.ui.theme.SchedulerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchedulerTheme {
                Navigator(MainMenuScreen()) { navigation ->
                    val currentScreen = navigation.lastItem

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBarForCurrentScreen(currentScreen)
                        }
                    ) { innerPadding ->
                        Crossfade(
                            targetState = currentScreen,
                            modifier = Modifier.padding(innerPadding),
                            label = currentScreen.key,
                            animationSpec = tween(500)
                        ) { screen ->
                            screen.Content()
                        }
                    }
                }
            }
        }
    }
}