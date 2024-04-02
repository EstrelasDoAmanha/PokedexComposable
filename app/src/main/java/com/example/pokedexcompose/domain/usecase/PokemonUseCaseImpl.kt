package com.example.pokedexcompose.domain.usecase

import androidx.paging.PagingData
import com.example.pokedexcompose.data.repository.PokemonRepository
import com.example.pokedexcompose.domain.model.ResultListDomain
import kotlinx.coroutines.flow.Flow

internal class PokemonUseCaseImpl(private val repository: PokemonRepository) : PokemonUseCase {
    override suspend fun getPokemonList(): Flow<PagingData<ResultListDomain>> =
        repository.getPokemonList()
}
