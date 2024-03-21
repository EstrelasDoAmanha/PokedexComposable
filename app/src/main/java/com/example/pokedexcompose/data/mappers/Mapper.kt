package com.example.pokedexcompose.data.mappers

interface Mapper<in F,out T> {
    fun map(from: F) : T
}