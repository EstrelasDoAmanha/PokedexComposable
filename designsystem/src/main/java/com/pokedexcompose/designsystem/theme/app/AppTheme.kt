package com.pokedexcompose.designsystem.theme.app

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun AppTheme(
    colorScheme: ColorScheme,
    shapes: Shapes,
    typography: Typography,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val currentView = LocalView.current
    val currentContext = currentView.context

    if (currentView.isInEditMode.not()) {
       SideEffect {
           val window = (currentContext as Activity).window
           window.statusBarColor = colorScheme.primary.toArgb()
           WindowCompat
               .getInsetsController(window, currentView)
               .isAppearanceLightStatusBars = darkTheme
       }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = typography,
        content = content
    )
}
