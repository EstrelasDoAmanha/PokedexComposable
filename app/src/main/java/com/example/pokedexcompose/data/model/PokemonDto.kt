package com.example.pokedexcompose.data.model

import com.example.pokedexcompose.data.extensions.emptyString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    @SerialName("height")
    val height: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String? = String.emptyString(),
    @SerialName("order")
    val order: Int = 0,
    //todo verficar se deve ser mantido
    @SerialName("sprites")
    val sprites: Sprites = Sprites(),
    @SerialName("weight")
    val weight: Int = 0,
    @SerialName("stats")
    val stats: List<Statistics> = emptyList(),
    @SerialName("types")
    val types: List<TypeDto> = emptyList()
)

@Serializable
data class Home(
    @SerialName("front_default")
    val frontDefault: String? = String.emptyString(),
    @SerialName("front_female")
    val frontFemale: String? = String.emptyString(),
    @SerialName("front_shiny")
    val frontShiny: String? = String.emptyString(),
    @SerialName("front_shiny_female")
    val frontShinyFemale: String? = String.emptyString()
)

@Serializable
data class Other(
    @SerialName("home")
    val home: Home = Home(),
    @SerialName("showdown")
    val showdown: Showdown = Showdown()
)

@Serializable
data class Showdown(
    @SerialName("back_default")
    val backDefault: String? = String.emptyString(),
    @SerialName("back_female")
    val backFemale: String? = String.emptyString(),
    @SerialName("back_shiny")
    val backShiny: String? = String.emptyString(),
    @SerialName("back_shiny_female")
    val backShinyFemale: String? = String.emptyString(),
    @SerialName("front_default")
    val frontDefault: String? = String.emptyString(),
    @SerialName("front_female")
    val frontFemale: String? = String.emptyString(),
    @SerialName("front_shiny")
    val frontShiny: String? = String.emptyString(),
    @SerialName("front_shiny_female")
    val frontShinyFemale: String? = String.emptyString()
)

@Serializable
data class Sprites(
    @SerialName("back_default")
    val backDefault: String? = String.emptyString(),
    @SerialName("back_female")
    val backFemale: String? = String.emptyString(),
    @SerialName("back_shiny")
    val backShiny: String? = String.emptyString(),
    @SerialName("back_shiny_female")
    val backShinyFemale: String? = String.emptyString(),
    @SerialName("front_default")
    val frontDefault: String? = String.emptyString(),
    @SerialName("front_female")
    val frontFemale: String? = String.emptyString(),
    @SerialName("front_shiny")
    val frontShiny: String? = String.emptyString(),
    @SerialName("front_shiny_female")
    val frontShinyFemale: String? = String.emptyString(),
    @SerialName("other")
    val other: Other = Other()
)

@Serializable
data class StatisticInfo(
    @SerialName("name")
    val name: String? = String.emptyString()
)

@Serializable
data class Statistics(
    @SerialName("base_stat")
    val baseStat: Int = 0,
    @SerialName("stat")
    val stat: StatisticInfo = StatisticInfo()
)

@Serializable
data class TypeDto(
    @SerialName("name")
    val name: String? = String.emptyString(),
)