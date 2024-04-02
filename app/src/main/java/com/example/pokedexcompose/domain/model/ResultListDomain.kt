package com.example.pokedexcompose.domain.model

data class ResultListDomain(
    val name: String,
    val url: String = "",
    val gif: String,
    val types: List<TypesDomain> = listOf((TypesDomain(1, TypeDomain("fogo"))))
)
