package com.example.pokedexcompose.domain.usecase.receiverPagingState

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.ResultListDomain
import kotlinx.coroutines.flow.Flow

internal interface SavePagingStateUseCase {
    suspend operator fun invoke(cachedIn: Flow<PagingData<ResultListDomain>>)
}
