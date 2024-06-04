package com.example.pokedexcompose.domain.usecase.storagestate

import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
internal class ReceiverStateCaseImpl(
    private val repository: PokemonRepository
) : ReceiverStateUseCase {
    override fun invoke(): Flow<Pair<Int, Int>>  = repository.receiverPositionState()
}
