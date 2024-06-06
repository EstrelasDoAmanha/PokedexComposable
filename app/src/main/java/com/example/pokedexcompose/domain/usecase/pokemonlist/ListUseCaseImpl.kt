package com.example.pokedexcompose.domain.usecase.pokemonlist

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

internal class ListUseCaseImpl(
    private val repository: PokemonRepository
) : GetListUseCase {
    override suspend fun invoke(query: String): Flow<PagingData<ResultListDomain>> =
        repository.getPokemonList(query)
}
