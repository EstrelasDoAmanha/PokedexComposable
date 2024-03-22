package com.example.pokedexcompose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("next")
    val next: String = "",
    @SerialName("results")
    val result:List<ResultDto>
)
@Serializable
data class ResultDto(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
){
    fun getId():String = url.substringAfter("pokemon/").replace("/", "")
    fun getGif():String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/shiny/${getId()}.gif"
    }
}