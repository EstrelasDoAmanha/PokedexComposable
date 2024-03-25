package com.example.pokedexcompose.domain.usecase

import com.example.pokedexcompose.data.model.PokemonListDomain
import com.example.pokedexcompose.data.repository.PokemonRepository

internal class PokemonUseCaseImpl(private val repository: PokemonRepository) : PokemonUseCase {
    override suspend fun getPokemonList(): PokemonListDomain = repository.getPokemonList()
}
