package com.example.pokedexcompose.domain.model

data class ResultListDomain(
    val name: String,
    val url: String = "",
    val gif: String,
    val types: List<PokemonType> = listOf((PokemonType(1, Type("fogo"))))
)
