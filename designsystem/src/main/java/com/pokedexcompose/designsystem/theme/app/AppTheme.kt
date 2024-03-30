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
import com.pokedexcompose.designsystem.colors.dsl.background
import com.pokedexcompose.designsystem.colors.dsl.colors
import com.pokedexcompose.designsystem.colors.dsl.error
import com.pokedexcompose.designsystem.colors.dsl.outlineScrim
import com.pokedexcompose.designsystem.colors.dsl.primary
import com.pokedexcompose.designsystem.colors.dsl.scheme
import com.pokedexcompose.designsystem.colors.dsl.secondary
import com.pokedexcompose.designsystem.colors.dsl.surface
import com.pokedexcompose.designsystem.colors.dsl.tertiary
import com.pokedexcompose.designsystem.shapes.dsl.roundCornerShape
import com.pokedexcompose.designsystem.shapes.dsl.shapes
import com.pokedexcompose.designsystem.typography.dsl.configure
import com.pokedexcompose.designsystem.typography.dsl.typography
import com.pokedexcompose.designsystem.typography.text.textStyle

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

@Composable
fun bla() {
    AppTheme(
        colorScheme = colors {
            dark = scheme {
                primary = primary {

                }
                secondary = secondary {

                }
                tertiary = tertiary {

                }
                background = background {

                }
                surface = surface {

                }
                error = error {

                }
                outlineScrim = outlineScrim {

                }
            }
            light = scheme {
                primary = primary {

                }
                secondary = secondary {

                }
                tertiary = tertiary {

                }
                background = background {

                }
                surface = surface {

                }
                error = error {

                }
                outlineScrim = outlineScrim {

                }
            }
        },
        shapes = shapes {
            extraSmall = roundCornerShape {

            }
            small = roundCornerShape {

            }
            medium = roundCornerShape {

            }
            large = roundCornerShape {

            }
            extraLarge = roundCornerShape {

            }
        },
        typography = typography {
            display =  configure {
                small = textStyle {

                }
                medium = textStyle {

                }
                large = textStyle {

                }
            }
            headline = configure {
                small = textStyle {

                }
                medium = textStyle {

                }
                large = textStyle {

                }
            }
            title = configure {
                small = textStyle {

                }
                medium = textStyle {

                }
                large = textStyle {

                }
            }
            body = configure {
                small = textStyle {

                }
                medium = textStyle {

                }
                large = textStyle {

                }
            }
            label = configure {
                small = textStyle {

                }
                medium = textStyle {

                }
                large = textStyle {

                }
            }
        },
    ) {

    }
}