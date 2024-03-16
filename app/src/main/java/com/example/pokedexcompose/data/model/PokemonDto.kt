package com.example.pokedexcompose.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    @SerialName("height")
    val height: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("is_default")
    val isDefault: Boolean = false,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("order")
    val order: Int = 0,
    @SerialName("sprites")
    val sprites: Sprites = Sprites(),
    @SerialName("weight")
    val weight: Int = 0
)