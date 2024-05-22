package com.example.pokedexcompose.domain.repository

import androidx.paging.PagingData
import com.example.pokedexcompose.data.model.ListPokemonByTypesDto
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.ListOfPokemonTypesDomain
import com.example.pokedexcompose.domain.model.ResultListDomain
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonDetail(pokemonId: Int): Flow<PokemonInfo>
    suspend fun ListOfPokemonTypes():ListOfPokemonTypesDomain
    suspend fun getPokemonList(): Flow<PagingData<ResultListDomain>>
    suspend fun listPokemonByFilter(filter: String): List<ResultListDomain>
}
