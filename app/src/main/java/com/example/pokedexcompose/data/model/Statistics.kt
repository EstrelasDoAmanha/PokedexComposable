package com.example.pokedexcompose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Statistics(
    @SerialName("base_stat")
    val baseStat: Int = 0,
    @SerialName("stat")
    val stat: StatisticInfo = StatisticInfo()
)