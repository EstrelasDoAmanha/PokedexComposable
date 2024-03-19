package com.example.pokedexcompose.data.mappers

interface Mapper<F,T> {
    fun map(from: F) : T
}