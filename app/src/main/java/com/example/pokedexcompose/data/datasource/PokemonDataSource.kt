package com.example.pokedexcompose.data.datasource

import com.example.pokedexcompose.data.model.TypeListDto
import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.data.model.PokemonListDto

interface PokemonDataSource {
    suspend fun getPokemonList(
        offset: String = "",
        limit: String = ""
    ): PokemonListDto
    suspend fun getPokemonListWithFilter(
        query: String = ""
    ): PokemonListDto
    suspend fun typeList(): TypeListDto
    suspend fun getPokemonDetail(pokemonId: Int): PokemonDto
}
