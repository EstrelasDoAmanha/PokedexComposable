package com.example.pokedexcompose.domain.model

data class PokemonListDomain(
    val count: Int = 0,
    val next: String = "",
    val previous: String = "",
    val result: List<ResultListDomain> = emptyList()
)
