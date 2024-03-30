package com.example.pokedexcompose.domain.mapper

internal interface Mapper<in F, out T> {
    fun map(from: F): T
}
