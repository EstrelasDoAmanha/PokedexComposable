package com.pokedexcompose.designsystem.colors.dsl.model

import androidx.compose.ui.graphics.Color

data class ColorModel(
    var light: ColorDslModel = ColorDslModel(),
    var dark: ColorDslModel = ColorDslModel()
)

data class ColorDslModel(
    var primary: ColorPrimaryModel = ColorPrimaryModel(),
    var secondary: ColorSecondaryModel = ColorSecondaryModel(),
    var tertiary: ColorTertiaryModel = ColorTertiaryModel(),
    var background: ColorBackgroundModel = ColorBackgroundModel(),
    var surface: ColorSurfaceModel = ColorSurfaceModel(),
    var error: ColorErrorModel = ColorErrorModel(),
    var outlineScrim: ColorOutlineScrimModel = ColorOutlineScrimModel()
)

data class ColorPrimaryModel(
    var primary: Color = Color.Unspecified,
    var onPrimary: Color = Color.Unspecified,
    var container: Color = Color.Unspecified,
    var onContainer: Color = Color.Unspecified,
    var inverse: Color = Color.Unspecified
)

data class ColorSecondaryModel(
    var secondary: Color = Color.Unspecified,
    var onSecondary: Color = Color.Unspecified,
    var container: Color = Color.Unspecified,
    var onContainer: Color = Color.Unspecified,
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