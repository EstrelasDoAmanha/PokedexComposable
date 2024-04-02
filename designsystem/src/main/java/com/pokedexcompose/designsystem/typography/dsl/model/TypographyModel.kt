package com.pokedexcompose.designsystem.typography.dsl.model

import androidx.compose.ui.text.TextStyle

data class TypographyModel(
    var display: TextStyleSizeModel = TextStyleSizeModel(),
    var headline: TextStyleSizeModel = TextStyleSizeModel(),
    var title: TextStyleSizeModel = TextStyleSizeModel(),
    var body: TextStyleSizeModel = TextStyleSizeModel(),
    var label: TextStyleSizeModel = TextStyleSizeModel()
)

data class TextStyleSizeModel(
    var large: TextStyle = TextStyle.Default,
    var medium: TextStyle = TextStyle.Default,
    var small: TextStyle = TextStyle.Default
)
