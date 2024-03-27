package com.example.pokedexcompose.data.model

import com.example.pokedexcompose.domain.util.PokemonGifUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultListDto(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
){
    val urlGif by PokemonGifUrl(url)
}