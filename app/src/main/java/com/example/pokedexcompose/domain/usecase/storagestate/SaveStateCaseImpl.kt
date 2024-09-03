package com.example.pokedexcompose.domain.usecase.storagestate

import com.example.pokedexcompose.domain.repository.PokemonRepository

internal class SaveStateCaseImpl(
    private val repository: PokemonRepository
) : SaveStateUseCase {
    override suspend fun invoke(position: Pair<Int, Int>) {
        repository.updatePositionState(position)
    }
}
