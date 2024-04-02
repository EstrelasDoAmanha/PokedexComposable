package com.pokedexcompose.designsystem.colors.dsl

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.pokedexcompose.designsystem.colors.dsl.model.ColorBackgroundModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorDslModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorErrorModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorOutlineScrimModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorPrimaryModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorSecondaryModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorSurfaceModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorTertiaryModel

@DslMarker
annotation class ColorDsl

@ColorDsl
fun colors(
    darkTheme: Boolean = false,
    setup: ColorModel.() -> Unit
): ColorScheme {
    val data = ColorModel()
    data.setup()

    return if(darkTheme) {
        darkColorScheme(
            primary = data.dark.primary.primary,
            onPrimary = data.dark.primary.onPrimary,
            primaryContainer = data.dark.primary.container,
            onPrimaryContainer = data.dark.primary.onContainer,
            inversePrimary = data.dark.primary.inverse,
            secondary = data.dark.secondary.secondary,
            onSecondary = data.dark.secondary.onSecondary,
            secondaryContainer = data.dark.secondary.container,
            onSecondaryContainer = data.dark.secondary.onContainer,
            tertiary = data.dark.tertiary.tertiary,
            onTertiary = data.dark.tertiary.onTertiary,
            tertiaryContainer = data.dark.tertiary.container,
            onTertiaryContainer = data.dark.tertiary.onContainer,
            background = data.dark.background.background,
            onBackground = data.dark.background.onBackground,
            surface = data.dark.surface.surface,
            onSurface = data.dark.surface.onSurface,
            surfaceVariant = data.dark.surface.variant,
            onSurfaceVariant = data.dark.surface.onVariant,
            surfaceTint = data.dark.surface.tint,
            inverseSurface = data.dark.surface.inverse,
            inverseOnSurface = data.dark.surface.onInverse,
            error = data.dark.error.error,
            onError = data.dark.error.onError,
            errorContainer = data.dark.error.container,
            onErrorContainer = data.dark.error.onContainer,
            outline = data.dark.outlineScrim.outline,
            outlineVariant = data.dark.outlineScrim.variant,
            scrim = data.dark.outlineScrim.scrim
        )
    } else lightColorScheme(
        primary = data.light.primary.primary,
        onPrimary = data.light.primary.onPrimary,
        primaryContainer = data.light.primary.container,
        onPrimaryContainer = data.light.primary.onContainer,
        inversePrimary = data.light.primary.inverse,
        secondary = data.light.secondary.secondary,
        onSecondary = data.light.secondary.onSecondary,
        secondaryContainer = data.light.secondary.container,
        onSecondaryContainer = data.light.secondary.onContainer,
        tertiary = data.light.tertiary.tertiary,
        onTertiary = data.light.tertiary.onTertiary,
        tertiaryContainer = data.light.tertiary.container,
        onTertiaryContainer = data.light.tertiary.onContainer,
        background = data.light.background.background,
        onBackground = data.light.background.onBackground,
        surface = data.light.surface.surface,
        onSurface = data.light.surface.onSurface,
        surfaceVariant = data.light.surface.variant,
        onSurfaceVariant = data.light.surface.onVariant,
        surfaceTint = data.light.surface.tint,
        inverseSurface = data.light.surface.inverse,
        inverseOnSurface = data.light.surface.onInverse,
        error = data.light.error.error,
        onError = data.light.error.onError,
        errorContainer = data.light.error.container,
        onErrorContainer = data.light.error.onContainer,
        outline = data.light.outlineScrim.outline,
        outlineVariant = data.light.outlineScrim.variant,
        scrim = data.light.outlineScrim.scrim
    )
}

@ColorDsl
fun scheme(
    setup: ColorDslModel.() -> Unit,
): ColorDslModel {
    val data = ColorDslModel()
    data.setup()

    return data
}

@ColorDsl
fun primary(
    setup: ColorPrimaryModel.() -> Unit
): ColorPrimaryModel {
    val data = ColorPrimaryModel()
    data.setup()

    return data
}

@ColorDsl
fun secondary(
    setup: ColorSecondaryModel.() -> Unit
): ColorSecondaryModel {
    val data = ColorSecondaryModel()
    data.setup()

    return data
}

@ColorDsl
fun tertiary(
    setup: ColorTertiaryModel.() -> Unit
): ColorTertiaryModel {
    val data = ColorTertiaryModel()
    data.setup()

    return data
}

@ColorDsl
fun background(
    setup: ColorBackgroundModel.() -> Unit
): ColorBackgroundModel {
    val data = ColorBackgroundModel()
    data.setup()

    return data
}

@ColorDsl
fun surface(
    setup: ColorSurfaceModel.() -> Unit
): ColorSurfaceModel {
    val data = ColorSurfaceModel()
    data.setup()

    return data
}

@ColorDsl
fun error(
    setup: ColorErrorModel.() -> Unit
): ColorErrorModel {
    val data = ColorErrorModel()
    data.setup()

    return data
}

@ColorDsl
fun outlineScrim(
    setup: ColorOutlineScrimModel.() -> Unit
): ColorOutlineScrimModel {
    val data = ColorOutlineScrimModel()
    data.setup()

    return data
}
