package com.example.pokedexcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.mappers.PokemonDtoToDomain
import com.example.pokedexcompose.data.mappers.ListOfPokemonTypesDtoToDomain
import com.example.pokedexcompose.data.mappers.ListPokemonByFilterDtoToDomain
import com.example.pokedexcompose.domain.mapper.PokemonListDtoToDomain
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.model.ListOfPokemonTypesDomain
import com.example.pokedexcompose.domain.pagging.PokemonListPagingSource
import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonDetailsToDomain: PokemonDtoToDomain,
    private val pokemonListToDomain: PokemonListDtoToDomain,
    private val listPokemonTypesToDomain: ListOfPokemonTypesDtoToDomain
) : PokemonRepository {

    override suspend fun getPokemonList(filter: String): Flow<PagingData<ResultListDomain>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                PokemonListPagingSource(
                    pokemonDataSource,
                    pokemonListToDomain,
                    filter
                )
            }
        ).flow
    }

    override suspend fun getPokemonDetail(pokemonId: Int): Flow<PokemonInfo> {
        return flowOf(
            pokemonDetailsToDomain.map(
                pokemonDataSource.getPokemonDetail(pokemonId)
            )
        )
    }

    override suspend fun ListOfPokemonTypes(): ListOfPokemonTypesDomain {
        return listPokemonTypesToDomain.map(pokemonDataSource.listPokemonType())
    }

}
