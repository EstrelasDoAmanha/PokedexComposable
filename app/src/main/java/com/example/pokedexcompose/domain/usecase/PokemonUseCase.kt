package com.example.pokedexcompose.domain.usecase

import androidx.paging.PagingData
import com.example.pokedexcompose.data.model.ListPokemonByTypesDto
import com.example.pokedexcompose.domain.model.ListOfPokemonTypesDomain
import com.example.pokedexcompose.domain.model.ResultListDomain
import kotlinx.coroutines.flow.Flow

internal interface PokemonUseCase {
    suspend fun getPokemonList(filter: String = ""): Flow<PagingData<ResultListDomain>>
    suspend fun ListOfPokemonTypes(): ListOfPokemonTypesDomain

}
