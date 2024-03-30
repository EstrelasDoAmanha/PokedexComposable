package com.example.pokedexcompose.data.repository

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.PokemonListDomain
import com.example.pokedexcompose.domain.model.ResultListDomain
import kotlinx.coroutines.flow.Flow

internal interface PokemonRepository {
    suspend fun getPokemonList(): Flow<PagingData<ResultListDomain>>
}
