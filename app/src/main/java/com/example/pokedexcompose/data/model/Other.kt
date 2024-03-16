package com.example.pokedexcompose.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Other(
    @SerialName("home")
    val home: Home = Home(),
    @SerialName("showdown")
    val showdown: Showdown = Showdown()
)