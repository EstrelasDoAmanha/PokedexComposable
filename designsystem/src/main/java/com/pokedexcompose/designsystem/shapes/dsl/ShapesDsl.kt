package com.pokedexcompose.designsystem.shapes.dsl

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp
import com.pokedexcompose.designsystem.shapes.dsl.model.CornerBasedShapeModel
import com.pokedexcompose.designsystem.shapes.dsl.model.ShapeDslModel

@DslMarker
annotation class ShapesDsl

@ShapesDsl
fun shapes(setup: ShapeDslModel.() -> Unit): Shapes {
    val data = ShapeDslModel()
    data.setup()

    return Shapes(
        extraSmall = data.extraSmall,
        small = data.small,
        medium = data.medium,
        large = data.large,
        extraLarge = data.extraLarge
    )
}

@ShapesDsl
fun roundCornerShape(setup: CornerBasedShapeModel.() -> Unit): CornerBasedShape {
    val data = CornerBasedShapeModel()
    data.setup()

    return RoundedCornerShape(
        topStart = data.topStart.dp,
        topEnd = data.topEnd.dp,
        bottomEnd = data.bottomEnd.dp,
        bottomStart = data.bottomStart.dp
    )
}
