package com.example.pokedexcompose.domain.usecase.receiverPagingState

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

internal class SavePagingStateUseCaseImpl(
    private val repository: PokemonRepository
) : SavePagingStateUseCase {
    override suspend operator fun invoke(cachedIn: Flow<PagingData<ResultListDomain>>) = repository.savePagingState(cachedIn)
}
