package com.example.pokedexcompose.domain.usecase

import com.example.pokedexcompose.data.repository.PokemonRepository
import com.example.pokedexcompose.domain.model.PokemonListDomain

internal class PokemonUseCaseImpl(private val repository: PokemonRepository) : PokemonUseCase {
    override suspend fun getPokemonList(): PokemonListDomain = repository.getPokemonList()
}
