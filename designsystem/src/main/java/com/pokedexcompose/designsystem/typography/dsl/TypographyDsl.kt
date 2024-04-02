package com.pokedexcompose.designsystem.typography.dsl

import androidx.compose.material3.Typography
import com.pokedexcompose.designsystem.typography.dsl.model.TextStyleSizeModel
import com.pokedexcompose.designsystem.typography.dsl.model.TypographyModel

@DslMarker
annotation class TypographyDsl

@TypographyDsl
fun typography(setup: TypographyModel.() -> Unit): Typography {
    val data = TypographyModel()
    data.setup()

    return Typography(
        displayLarge = data.display.large,
        displayMedium = data.display.medium,
        displaySmall = data.display.small,
        headlineLarge = data.headline.large,
        headlineMedium = data.headline.medium,
        headlineSmall = data.headline.small,
        titleLarge = data.title.large,
        titleMedium = data.title.medium,
        titleSmall = data.title.small,
        bodyLarge = data.body.large,
        bodyMedium = data.body.medium,
        bodySmall = data.body.small,
        labelLarge = data.label.large,
        labelMedium = data.label.medium,
        labelSmall = data.label.small
    )
}

@TypographyDsl
fun configure(setup: TextStyleSizeModel.() -> Unit): TextStyleSizeModel {
    val data = TextStyleSizeModel()
    data.setup()

    return data
}
