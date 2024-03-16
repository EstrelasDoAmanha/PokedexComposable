package com.example.pokedexcompose.data.model


import com.example.pokedexcompose.data.extensions.emptyString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Showdown(
    @SerialName("back_default")
    val backDefault: String = String.emptyString(),
    @SerialName("back_female")
    val backFemale: String = String.emptyString(),
    @SerialName("back_shiny")
    val backShiny: String = String.emptyString(),
    @SerialName("back_shiny_female")
    val backShinyFemale: String = String.emptyString(),
    @SerialName("front_default")
    val frontDefault: String = String.emptyString(),
    @SerialName("front_female")
    val frontFemale: String = String.emptyString(),
    @SerialName("front_shiny")
    val frontShiny: String = String.emptyString(),
    @SerialName("front_shiny_female")
    val frontShinyFemale: String = String.emptyString()
)