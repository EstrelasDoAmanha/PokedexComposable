package com.example.pokedexcompose.data.model

import com.example.pokedexcompose.extensions.empty
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeDto(
    @SerialName("name")
    val name: String? = String.empty()
)