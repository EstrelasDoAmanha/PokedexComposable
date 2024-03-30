package com.pokedexcompose.designsystem.colors.dsl

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.pokedexcompose.designsystem.colors.dsl.model.ColorBackgroundModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorDslModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorErrorModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorOutlineScrimModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorPrimaryModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorSecondaryModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorSurfaceModel
import com.pokedexcompose.designsystem.colors.dsl.model.ColorTertiaryModel

@DslMarker
annotation class ColorDsl

@ColorDsl
fun colors(
    darkTheme: Boolean = false,
    setup: ColorModel.() -> Unit
): ColorScheme {
    val data = ColorModel()
    data.setup()

    return if(darkTheme) {
        darkColorScheme(

        )
    } else lightColorScheme(

    )
}

@ColorDsl
fun scheme(
    setup: ColorDslModel.() -> Unit,
): ColorDslModel {
    val data = ColorDslModel()
    data.setup()

    return data
}

@ColorDsl
fun primary(
    setup: ColorPrimaryModel.() -> Unit
): ColorPrimaryModel {
    val data = ColorPrimaryModel()
    data.setup()

    return data
}

@ColorDsl
fun secondary(
    setup: ColorSecondaryModel.() -> Unit
): ColorSecondaryModel {
    val data = ColorSecondaryModel()
    data.setup()

    return data
}

@ColorDsl
fun tertiary(
    setup: ColorTertiaryModel.() -> Unit
): ColorTertiaryModel {
    val data = ColorTertiaryModel()
    data.setup()

    return data
}

@ColorDsl
fun background(
    setup: ColorBackgroundModel.() -> Unit
): ColorBackgroundModel {
    val data = ColorBackgroundModel()
    data.setup()

    return data
}

@ColorDsl
fun surface(
    setup: ColorSurfaceModel.() -> Unit
): ColorSurfaceModel {
    val data = ColorSurfaceModel()
    data.setup()

    return data
}

@ColorDsl
fun error(
    setup: ColorErrorModel.() -> Unit
): ColorErrorModel {
    val data = ColorErrorModel()
    data.setup()

    return data
}

@ColorDsl
fun outlineScrim(
    setup: ColorOutlineScrimModel.() -> Unit
): ColorOutlineScrimModel {
    val data = ColorOutlineScrimModel()
    data.setup()

    return data
}
