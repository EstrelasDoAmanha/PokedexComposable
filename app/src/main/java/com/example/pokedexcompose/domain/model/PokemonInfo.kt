package com.example.pokedexcompose.domain.model

data class PokemonInfo(
    val name: String,
    val image: String,
    val height: Int,
    val id: Int,
    val order: Int,
    val weight: Int,
    val stats: List<PokemonStatistics>,
    val type: List<PokemonType>
)
