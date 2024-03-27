package com.example.pokedexcompose.domain.util

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class PokemonGifUrl(private val url:String) : ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        val id = url.substringAfter("pokemon/").replace("/", "")
       return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/shiny/${id}.gif"
    }
}