package com.pokedexcompose.designsystem.typography.text

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
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

fun bla() {
    val bla = TextStyle(
        color = Color.Unspecified,
        background = Color.Unspecified,
        letterSpacing = TextUnit.Unspecified,
        baselineShift = BaselineShift(
            multiplier = 0F
        ),
        textAlign = TextAlign.Justify,
        textIndent = TextIndent(
            firstLine = TextUnit(
                value = 0F,
                type = TextUnitType(
                    type = 0L
                )
            ),
            restLine = TextUnit(
                value = 0F,
                type = TextUnitType(
                    type = 0L
                )
            )
        ),
        textDecoration = TextDecoration.None,
        lineHeight = TextUnit.Unspecified,
        shadow = Shadow(
            color = Color.Unspecified,
            offset = Offset.Zero
        ),
        textGeometricTransform = TextGeometricTransform(
            scaleX = 1F,
            skewX = 1F
        ),
        // Part 2
        textDirection = TextDirection.Ltr,
        localeList = LocaleList(
            localeList = listOf(Locale.current)
        ),
        fontSize = TextUnit.Unspecified,
        fontWeight = FontWeight(
            weight = 1
        ),
        fontStyle = FontStyle(
            value = 1
        ),
        fontFamily = FontFamily.Cursive,
        fontSynthesis = FontSynthesis.All,
        fontFeatureSettings = ""
    )
}

// https://medium.com/@gozde.kaval/jetpack-compose-theming-typography-part-i-ac1796020ab
