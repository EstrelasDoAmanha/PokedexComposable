package com.example.pokedexcompose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class ListPokemonTypesDto(
    @SerialName("results")
    val results: List<TypeDto>
)
