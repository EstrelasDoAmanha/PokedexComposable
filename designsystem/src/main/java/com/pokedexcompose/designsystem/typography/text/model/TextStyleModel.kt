package com.pokedexcompose.designsystem.typography.text.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.PlatformTextStyle
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

data class TextStyleModel(
    var localeList: LocaleList = LocaleList(Locale.current),
    var textColors: TextColorModel = TextColorModel(),
    var positioning: TextPositioningModel = TextPositioningModel(),
    var decoration: TextDecorationModel = TextDecorationModel(),
    var font: TextFontModel = TextFontModel()
)

data class TextColorModel(
    var color: Color = Color(0x1E1E1E),
    var background: Color = Color.Unspecified
)

data class TextPositioningModel(
    var letterSpacing: TextUnit = TextUnit.Unspecified,
    var baselineShift: BaselineShift? = null,
    var textAlign: TextAlign? = null,
    var textIndent: TextIndent? = null,
    var lineHeight: TextUnit = TextUnit.Unspecified,
    var direction: TextDirection = TextDirection.Ltr
)

data class TextDecorationModel(
    var textDecoration: TextDecoration? = null,
    var shadow: Shadow? = null,
    var geometricTransform: TextGeometricTransform? = null,
    var drawStyle: DrawStyle = Fill,
    var platformTextStyle: PlatformTextStyle = PlatformTextStyle()
)

data class TextFontModel(
    var size: Int = 16,
    var weight: FontWeight = FontWeight.Normal,
    var style: FontStyle = FontStyle.Normal,
    var family: FontFamily = FontFamily.Default,
    var synthesis: FontSynthesis = FontSynthesis.All,
    var settings: String? = null
)
