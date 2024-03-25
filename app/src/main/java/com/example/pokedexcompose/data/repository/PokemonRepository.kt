package com.example.pokedexcompose.data.repository

import com.example.pokedexcompose.data.model.PokemonListDomain

internal interface PokemonRepository {
    suspend fun getPokemonList(): PokemonListDomain
}
