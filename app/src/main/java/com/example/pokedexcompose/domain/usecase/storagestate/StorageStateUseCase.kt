package com.example.pokedexcompose.domain.usecase.storagestate

import kotlinx.coroutines.flow.Flow

internal interface StorageStateUseCase {
    fun invoke(): Flow<Pair<Int, Int>>
    suspend fun updatePositionState(
        position: Pair<Int, Int>
    )
}
