package com.example.pokedexcompose.domain.usecase

import com.example.pokedexcompose.domain.model.PokemonListDomain

internal interface PokemonUseCase {
    suspend fun getPokemonList(): PokemonListDomain
}
