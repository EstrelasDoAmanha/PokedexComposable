package com.example.pokedexcompose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class TypeListDto(
    @SerialName("results")
    val results: List<FilterListByTypeDto>
)

@Serializable
data class FilterListByTypeDto(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
