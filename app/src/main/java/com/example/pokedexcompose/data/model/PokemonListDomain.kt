package com.example.pokedexcompose.data.model

data class PokemonListDomain(
    val count: Int = 0,
    val next: String = "",
    val result: List<ResultDomain>
)
data class ResultDomain(
    val name: String,
    val url: String = "",
    val gif: String
)
