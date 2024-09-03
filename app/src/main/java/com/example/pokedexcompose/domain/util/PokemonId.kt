package com.example.pokedexcompose.domain.util

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class PokemonId(private val url: String) :
    ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        val id = url
            .substringAfter("pokemon/")
            .replace("/", "")
        return id
    }
}
