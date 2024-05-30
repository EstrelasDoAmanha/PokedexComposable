package com.example.pokedexcompose.domain.model

import com.example.pokedexcompose.extensions.empty

data class PokemonInfo(
    val name: String = String.empty(),
    val image: String = String.empty(),
    val height: Int = 0,
    val id: Int = 0,
    val order: Int = 0,
    val weight: Int = 0,
    val stats: List<PokemonStatistics> = emptyList(),
    val type: List<PokemonType> = emptyList()
)
