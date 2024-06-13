package com.example.pokedexcompose.domain.usecase.receiverPagingState

import com.example.pokedexcompose.domain.repository.PokemonRepository
internal class ReceiverPagingStateUseCaseImpl(
    private val repository: PokemonRepository
) : ReceivePagingStateUseCase {
    override suspend operator fun invoke() = repository.receiverPagingState()
}
