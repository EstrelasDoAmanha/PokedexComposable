package com.example.pokedexcompose.domain.usecases

import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonDetailsUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(pokemonId: Int): Flow<PokemonInfo> {
        return repository.getPokemonDetail(pokemonId)
    }
}