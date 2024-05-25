package com.example.pokedexcompose.domain.model

data class ResultListDomain(
    var id: Int,
    var name: String = "",
    val url: String = "",
    val gif: String = "",
    val types: List<PokemonType> = listOf(PokemonType(slot = 1, type = Type(name = "normal")))
)
