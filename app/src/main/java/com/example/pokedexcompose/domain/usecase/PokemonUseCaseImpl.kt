package com.example.pokedexcompose.domain.usecase

import androidx.paging.PagingData
import com.example.pokedexcompose.data.model.ListPokemonByTypesDto
import com.example.pokedexcompose.domain.model.ListOfPokemonTypesDomain
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

internal class PokemonUseCaseImpl(private val repository: PokemonRepository) : PokemonUseCase {
    override suspend fun getPokemonList(): Flow<PagingData<ResultListDomain>> =
        repository.getPokemonList()

    override suspend fun ListOfPokemonTypes(): ListOfPokemonTypesDomain {
        return repository.ListOfPokemonTypes()
    }

    override suspend fun listPokemonByFilter(type:String): List<ResultListDomain> {
        return repository.listPokemonByFilter(type)
    }
}
