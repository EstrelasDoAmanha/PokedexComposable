package com.example.pokedexcompose.data.repository

import com.example.pokedexcompose.domain.model.PokemonListDomain

internal interface PokemonRepository {
    suspend fun getPokemonList(): PokemonListDomain
}
