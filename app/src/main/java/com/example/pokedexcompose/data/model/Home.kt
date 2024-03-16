package com.example.pokedexcompose.data.model


import com.example.pokedexcompose.data.extensions.emptyString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Home(
    @SerialName("front_default")
    val frontDefault: String = String.emptyString(),
    @SerialName("front_female")
    val frontFemale: String = String.emptyString(),
    @SerialName("front_shiny")
    val frontShiny: String = String.emptyString(),
    @SerialName("front_shiny_female")
    val frontShinyFemale: String = String.emptyString()
)