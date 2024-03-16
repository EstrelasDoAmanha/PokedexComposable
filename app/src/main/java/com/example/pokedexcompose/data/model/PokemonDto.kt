package com.example.pokedexcompose.data.model


import com.example.pokedexcompose.data.extensions.emptyString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    @SerialName("height")
    val height: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = String.emptyString(),
    @SerialName("order")
    val order: Int = 0,
    @SerialName("sprites")
    val sprites: Sprites = Sprites(),
    @SerialName("weight")
    val weight: Int = 0,
    @SerialName("stats")
    val stats: List<Statistics> = emptyList()
)