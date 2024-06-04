package com.example.pokedexcompose.extensions

import com.example.pokedexcompose.domain.model.PokemonStatistics

internal const val HP = "hp"
internal const val ATTACK = "attack"
internal const val DEFENSE = "defense"
internal const val SPECIAL_ATTACK = "special-attack"
internal const val SPECIAL_DEFENSE = "special-defense"
internal const val SPEED = "speed"

internal fun PokemonStatistics.getAbbreviation(): String {
    // extract to string??
    return when (this.name.lowercase()) {
        HP -> "HP"
        ATTACK -> "Atk"
        DEFENSE -> "Def"
        SPECIAL_ATTACK -> "SpAtk"
        SPECIAL_DEFENSE -> "SpDef"
        SPEED -> "Spd"
        else -> ""
    }
}
