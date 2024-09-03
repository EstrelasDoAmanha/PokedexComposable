package com.example.pokedexcompose.domain.usecase.storagestate

import kotlinx.coroutines.flow.Flow

internal interface ReceiverStateUseCase {
    fun invoke(): Flow<Pair<Int, Int>>
}
