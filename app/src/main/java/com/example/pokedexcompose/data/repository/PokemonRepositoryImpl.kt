package com.example.pokedexcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.mappers.PokemonDtoToDomain
import com.example.pokedexcompose.data.mappers.TypeListDtoToDomain
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.model.TypeListDomain
import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonDetailsToDomain: PokemonDtoToDomain,
    private val typeListToDomain: TypeListDtoToDomain,
    private val pager: Pager<PagingConfig, ResultListDomain>,
) : PokemonRepository {

    override suspend fun getPokemonList(query: String): Flow<PagingData<ResultListDomain>> {
       return pager.flow
    }

    override suspend fun getPokemonDetail(pokemonId: Int): Flow<PokemonInfo> {
        return flowOf(
            pokemonDetailsToDomain.map(
                pokemonDataSource.getPokemonDetail(pokemonId)
            )
        )
    }

    override suspend fun getTypeList(): TypeListDomain {
        return typeListToDomain.map(pokemonDataSource.typeList())
    }
}
