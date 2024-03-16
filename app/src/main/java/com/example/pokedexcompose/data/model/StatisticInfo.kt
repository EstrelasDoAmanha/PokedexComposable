package com.example.pokedexcompose.data.model


import com.example.pokedexcompose.data.extensions.emptyString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatisticInfo(
    @SerialName("name")
    val name: String = String.emptyString()
)