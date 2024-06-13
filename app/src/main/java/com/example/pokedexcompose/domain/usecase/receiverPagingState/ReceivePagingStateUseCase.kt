package com.example.pokedexcompose.domain.usecase.receiverPagingState

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.ResultListDomain
import kotlinx.coroutines.flow.Flow

internal interface ReceivePagingStateUseCase {
    suspend operator fun invoke():Flow<PagingData<ResultListDomain>>
}
