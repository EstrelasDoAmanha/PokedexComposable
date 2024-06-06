package com.example.pokedexcompose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListByTypeDto(
    @SerialName("pokemon")
    val pokemon: ResultListDto
)
