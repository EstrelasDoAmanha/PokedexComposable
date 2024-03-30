package com.pokedexcompose.designsystem.shapes.custom.diamondcorner

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.LayoutDirection

class DiamondCornerShape(
    topStart: CornerSize,
    topEnd: CornerSize,
    bottomEnd: CornerSize,
    bottomStart: CornerSize
) : CornerBasedShape(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart,
) {
    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize
    ): CornerBasedShape = DiamondCornerShape(
        topStart,
        topEnd,
        bottomEnd,
        bottomStart
    )

    override fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection
    ): Outline {
        val width = size.width
        val height  = size.height

        val path = Path().apply {
            moveTo(0f, 3 * topStart)
            lineTo(topStart, topStart)
            lineTo(3 * topStart, 0f)
            lineTo(width - 3 * topEnd, 0f)
            lineTo(width - topEnd, topEnd)
            lineTo(width, 3 * bottomEnd)
            lineTo(width, height - 3 * bottomEnd)
            lineTo(width - bottomEnd, height - bottomEnd)
            lineTo(width - 3 * bottomStart, height)
            lineTo(3 * bottomStart, height)
            lineTo(bottomStart, height - bottomStart)
            lineTo(0f, height - 3 * bottomStart)
            close()
        }

        return Outline.Generic(path)
    }
}