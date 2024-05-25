package com.example.pokedexcompose.domain.usecase

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.TypeListDomain
import com.example.pokedexcompose.domain.model.ResultListDomain
import kotlinx.coroutines.flow.Flow

internal interface PokemonUseCase {
    suspend fun getPokemonList(query: String = ""): Flow<PagingData<ResultListDomain>>
    suspend fun getTypeList(): TypeListDomain

}
