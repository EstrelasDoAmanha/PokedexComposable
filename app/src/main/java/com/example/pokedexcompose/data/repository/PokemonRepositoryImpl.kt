package com.example.pokedexcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.mappers.PokemonDtotoDomain
import com.example.pokedexcompose.domain.mapper.MapperPokemonDomainImpl
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.pagging.PokemonPagingSource
import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonDetailsToDomain: PokemonDtotoDomain,
    private val pokemonListToDomain: MapperPokemonDomainImpl
) : PokemonRepository {

    override suspend fun getPokemonList(): Flow<PagingData<ResultListDomain>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { PokemonPagingSource(pokemonDataSource, pokemonListToDomain) }
        ).flow
    }

    override suspend fun getPokemonDetail(pokemonId: Int): Flow<PokemonInfo> {
        return flow {
            try {
                emit(
                    pokemonDetailsToDomain.map(
                        pokemonDataSource.getPokemonDetail(pokemonId)
                    )
                )
            } catch (e: Exception) {
                throw e
            }
        }
    }
}
