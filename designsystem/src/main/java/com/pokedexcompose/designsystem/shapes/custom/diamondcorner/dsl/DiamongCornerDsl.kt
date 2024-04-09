package com.pokedexcompose.designsystem.shapes.custom.diamondcorner.dsl

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import com.pokedexcompose.designsystem.shapes.custom.diamondcorner.DiamondCornerShape
import com.pokedexcompose.designsystem.shapes.custom.diamondcorner.dsl.model.DiamondCornerShapeModel

@DslMarker
annotation class DiamondCornerShapeDsl

@DiamondCornerShapeDsl
fun diamondCorner(setup: DiamondCornerShapeModel.() -> Unit): CornerBasedShape {
    val data = DiamondCornerShapeModel()
    data.setup()

    return DiamondCornerShape(
        topStart = CornerSize(data.topStart),
        topEnd = CornerSize(data.topEnd),
        bottomEnd = CornerSize(data.bottomEnd),
        bottomStart = CornerSize(data.bottomStart)
    )
}
