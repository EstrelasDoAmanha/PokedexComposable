package com.example.pokedexcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.domain.mapper.MapperPokemonDomainImpl
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.pagging.PokemonPagingSource
import kotlinx.coroutines.flow.Flow

internal class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource,
    val mapper: MapperPokemonDomainImpl
) : PokemonRepository {
    override suspend fun getPokemonList(): Flow<PagingData<ResultListDomain>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { PokemonPagingSource(pokemonDataSource, mapper) }
        ).flow
    }
}
