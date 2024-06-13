package com.example.pokedexcompose.domain.usecase.storagestate

import androidx.datastore.preferences.core.Preferences
import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
internal class ReceiverStateCaseImpl(
    private val repository: PokemonRepository
) : ReceiverStateUseCase {
    override suspend fun invoke() = repository.receiverPositionState()
}
