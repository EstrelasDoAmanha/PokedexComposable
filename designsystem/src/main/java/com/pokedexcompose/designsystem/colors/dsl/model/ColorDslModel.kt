package com.pokedexcompose.designsystem.colors.dsl.model

import androidx.compose.ui.graphics.Color

data class ColorModel(
    val light: ColorDslModel = ColorDslModel(),
    val dark: ColorDslModel = ColorDslModel()
)

data class ColorDslModel(
    val primary: ColorPrimaryModel = ColorPrimaryModel(),
    val secondary: ColorSecondaryModel = ColorSecondaryModel(),
    val tertiary: ColorTertiaryModel = ColorTertiaryModel(),
    val background: ColorBackgroundModel = ColorBackgroundModel(),
    val surface: ColorSurfaceModel = ColorSurfaceModel(),
    val error: ColorErrorModel = ColorErrorModel(),
    val outlineScim: ColorOutlineScrimModel = ColorOutlineScrimModel()
)

data class ColorPrimaryModel(
    val primary: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val container: Color = Color.Unspecified,
    val onContainer: Color = Color.Unspecified,
    val inverse: Color = Color.Unspecified
)

data class ColorSecondaryModel(
    val secondary: Color = Color.Unspecified,
    val onSecondary: Color = Color.Unspecified,
    val container: Color = Color.Unspecified,
    val onContainer: Color = Color.Unspecified,
)

data class ColorTertiaryModel(
    val tertiary: Color = Color.Unspecified,
    val onTertiary: Color = Color.Unspecified,
    val container: Color = Color.Unspecified,
    val onContainer: Color =  Color.Unspecified,
)

data class ColorBackgroundModel(
    val background: Color = Color.Unspecified,
    val onBackground: Color = Color.Unspecified
)

data class ColorSurfaceModel(
    val surface: Color = Color.Unspecified,
    val onSurface: Color = Color.Unspecified,
    val variant: Color = Color.Unspecified,
    val onVariant: Color = Color.Unspecified,
    val tint: Color = Color.Unspecified,
    val inverse: Color = Color.Unspecified,
    val onInverse: Color = Color.Unspecified,
)

data class ColorErrorModel(
    val error: Color = Color.Unspecified,
    val onError: Color = Color.Unspecified,
    val container: Color = Color.Unspecified,
    val onContainer: Color = Color.Unspecified,
)

data class ColorOutlineScrimModel(
    val outline: Color = Color.Unspecified,
    val variant: Color = Color.Unspecified,
    val scrim: Color = Color.Unspecified
)