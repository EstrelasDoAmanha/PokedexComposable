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
    val localeList: LocaleList = LocaleList(Locale.current),
    val colors: TextColorModel = TextColorModel(),
    val positioning: TextPositioningModel = TextPositioningModel(),
    val decoration: TextDecorationModel = TextDecorationModel(),
    val font: TextFontModel = TextFontModel()
)

data class TextColorModel(
    val textColor: Color = Color(0x1E1E1E),
    val textBackgroundColor: Color = Color.Unspecified
)

data class TextPositioningModel(
    val letterSpacing: TextUnit = TextUnit.Unspecified,
    val baselineShift: BaselineShift? = null,
    val textAlign: TextAlign? = null,
    val textIndent: TextIndent? = null,
    val lineHeight: TextUnit = TextUnit.Unspecified,
    val direction: TextDirection = TextDirection.Ltr
)

data class TextDecorationModel(
    val textDecoration: TextDecoration? = null,
    val shadow: Shadow? = null,
    val geometricTransform: TextGeometricTransform? = null,
    val drawStyle: DrawStyle = Fill,
    val platformTextStyle: PlatformTextStyle = PlatformTextStyle()
)

data class TextFontModel(
    val size: Int = 16,
    val weight: FontWeight = FontWeight.Normal,
    val style: FontStyle = FontStyle.Normal,
    val family: FontFamily = FontFamily.Default,
    val synthesis: FontSynthesis = FontSynthesis.All,
    val settings: String? = null
)
