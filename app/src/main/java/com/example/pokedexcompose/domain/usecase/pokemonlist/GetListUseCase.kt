package com.example.pokedexcompose.domain.usecase.pokemonlist

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.ResultListDomain
import kotlinx.coroutines.flow.Flow

internal interface GetListUseCase {
    suspend operator fun invoke(query: String = ""): Flow<PagingData<ResultListDomain>>
}
