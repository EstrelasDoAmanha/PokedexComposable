package com.pokedexcompose.designsystem.typography.text

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.pokedexcompose.designsystem.typography.text.model.TextColorModel
import com.pokedexcompose.designsystem.typography.text.model.TextDecorationModel
import com.pokedexcompose.designsystem.typography.text.model.TextFontModel
import com.pokedexcompose.designsystem.typography.text.model.TextPositioningModel
import com.pokedexcompose.designsystem.typography.text.model.TextStyleModel

@DslMarker
annotation class TextStyleDsl

@TextStyleDsl
fun textStyle(setup: TextStyleModel.() -> Unit): TextStyle {
    val data = TextStyleModel()
    data.setup()

    return TextStyle(
        color = data.textColors.color,
        background = data.textColors.background,
        fontSize = data.font.size.sp,
        fontWeight = data.font.weight,
        fontStyle = data.font.style,
        fontSynthesis = data.font.synthesis,
        fontFamily = data.font.family,
        fontFeatureSettings = data.font.settings,
        letterSpacing = data.positioning.letterSpacing,
        baselineShift = data.positioning.baselineShift,
        textGeometricTransform = data.decoration.geometricTransform,
        localeList = data.localeList,
        textDecoration = data.decoration.textDecoration,
        shadow = data.decoration.shadow,
        drawStyle = data.decoration.drawStyle,
        textAlign = data.positioning.textAlign,
        textDirection = data.positioning.direction,
        lineHeight = data.positioning.lineHeight,
        textIndent = data.positioning.textIndent,
        platformStyle = data.decoration.platformTextStyle
    )
}

@TextStyleDsl
fun textColors(setup: TextColorModel.() -> Unit): TextColorModel {
    val data = TextColorModel()
    data.setup()

    return data
}

@TextStyleDsl
fun positioning(setup: TextPositioningModel.() -> Unit): TextPositioningModel {
    val data = TextPositioningModel()
    data.setup()

    return data
}

@TextStyleDsl
fun decoration(setup: TextDecorationModel.() -> Unit): TextDecorationModel {
    val data = TextDecorationModel()
    data.setup()

    return data
}

@TextStyleDsl
fun font(setup: TextFontModel.() -> Unit): TextFontModel {
    val data = TextFontModel()
    data.setup()

    return data
}

// https://medium.com/@gozde.kaval/jetpack-compose-theming-typography-part-i-ac1796020ab
