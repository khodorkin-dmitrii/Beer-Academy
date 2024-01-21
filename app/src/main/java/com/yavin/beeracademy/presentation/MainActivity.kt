package com.yavin.beeracademy.presentation

import android.animation.ObjectAnimator
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
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
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.yavin.beeracademy.Navigation
import com.yavin.beeracademy.presentation.components.DebugRepeatBox
import com.yavin.beeracademy.presentation.components.isDebuggable
import com.yavin.beeracademy.ui.theme.BeerAcademyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var keepSplashOnScreen = true
        val delay = 700L
        installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }
        Handler(Looper.getMainLooper()).postDelayed({ keepSplashOnScreen = false }, delay)
        installSplashScreen().setOnExitAnimationListener { splashScreen ->
            ObjectAnimator.ofFloat(
                splashScreen.view, View.ALPHA, 1f, 0f
            ).apply {
                duration = 300L
                doOnEnd { splashScreen.remove() }
                start()
            }
        }

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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
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