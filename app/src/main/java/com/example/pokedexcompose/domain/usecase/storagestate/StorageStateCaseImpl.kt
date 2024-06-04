package com.example.pokedexcompose.domain.usecase.storagestate

import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
internal class StorageStateCaseImpl(
    private val repository: PokemonRepository
) : StorageStateUseCase {

    override fun invoke(): Flow<Pair<Int, Int>> {
        return repository.receiverPositionState()
    }

    override suspend fun updatePositionState(position: Pair<Int, Int>) {
        repository.updatePositionState(position)
    }
}
