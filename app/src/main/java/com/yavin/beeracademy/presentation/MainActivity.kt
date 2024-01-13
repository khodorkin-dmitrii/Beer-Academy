package com.yavin.beeracademy.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.yavin.beeracademy.Navigation
import com.yavin.beeracademy.ui.DebugRepeatBox
import com.yavin.beeracademy.ui.isDebuggable
import com.yavin.beeracademy.ui.theme.BeerAcademyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        val orientation = resources.configuration.orientation

        WindowCompat.setDecorFitsSystemWindows(
            window,
            // this help to set correct background for camera inset area for both orientations
            orientation == Configuration.ORIENTATION_PORTRAIT
        )
        setContent {
            BeerAcademyTheme(
                dynamicColor = false
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(Modifier.windowInsetsPadding(WindowInsets.safeContent)) {
                        Navigation()
                        if (isDebuggable(LocalContext.current)) {
                            DebugRepeatBox()
                        }
                    }
                }
            }
        }
    }
}