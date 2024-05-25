package com.example.pokedexcompose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class PokemonByTypesDto(
    @SerialName("pokemon")
    val results: List<PokemonListByTypeDto>
)
