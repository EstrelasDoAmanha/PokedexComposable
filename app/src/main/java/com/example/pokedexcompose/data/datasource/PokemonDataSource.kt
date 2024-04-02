package com.example.pokedexcompose.data.datasource

import com.example.pokedexcompose.data.model.PokemonListDto

internal interface PokemonDataSource {
    suspend fun getPokemonList(offset: String = "", limit: String = ""): PokemonListDto
}
