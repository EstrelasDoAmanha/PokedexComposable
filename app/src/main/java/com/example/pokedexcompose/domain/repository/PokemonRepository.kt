package com.example.pokedexcompose.domain.repository

import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.PokemonListDomain
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonDetail(pokemonId: Int): Flow<PokemonInfo>
    suspend fun getPokemonList(): PokemonListDomain
}