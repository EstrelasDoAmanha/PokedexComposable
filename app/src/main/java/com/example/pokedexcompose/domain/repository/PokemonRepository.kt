package com.example.pokedexcompose.domain.repository

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.TypeListDomain
import com.example.pokedexcompose.domain.model.ResultListDomain
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonDetail(pokemonId: Int): Flow<PokemonInfo>
    suspend fun getTypeList():TypeListDomain
    suspend fun getPokemonList(filter: String): Flow<PagingData<ResultListDomain>>
}
