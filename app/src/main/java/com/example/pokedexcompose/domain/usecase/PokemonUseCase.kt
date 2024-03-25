package com.example.pokedexcompose.domain.usecase

import com.example.pokedexcompose.data.model.PokemonListDomain

internal interface PokemonUseCase {
    suspend fun getPokemonList(): PokemonListDomain
}
