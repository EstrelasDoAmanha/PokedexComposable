package com.pokedexcompose.designsystem.shapes.dsl.model

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

data class ShapeDslModel(
    var extraSmall: CornerBasedShape = RoundedCornerShape(32.dp),
    var small: CornerBasedShape = RoundedCornerShape(24.dp),
    var medium: CornerBasedShape = RoundedCornerShape(16.dp),
    var large: CornerBasedShape = RoundedCornerShape(8.dp),
    var extraLarge: CornerBasedShape = RoundedCornerShape(4.dp),
)

data class CornerBasedShapeModel(
    var topStart: Int = 0,
    var topEnd: Int = 0,
    var bottomEnd: Int = 0,
    var bottomStart: Int = 0
)
