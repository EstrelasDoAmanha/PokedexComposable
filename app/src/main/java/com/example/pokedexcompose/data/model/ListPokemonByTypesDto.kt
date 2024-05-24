package com.example.pokedexcompose.data.model

import com.example.pokedexcompose.domain.util.PokemonGifUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class ListPokemonByTypesDto(
    @SerialName("pokemon")
    val results: List<PokemonFilterDto>
)
@Serializable
data class PokemonFilterDto(
    @SerialName("pokemon")
    val pokemon: PokemonInfoDto
)
@Serializable
data class PokemonInfoDto(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
){
    val urlGif by PokemonGifUrl(url)
}


