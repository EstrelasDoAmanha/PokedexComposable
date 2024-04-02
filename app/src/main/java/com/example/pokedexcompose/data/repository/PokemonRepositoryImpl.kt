package com.example.pokedexcompose.data.repository

import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.mappers.PokemonDtotoDomain
import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.domain.mapper.MapperPokemonDomainImpl
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.PokemonListDomain
import com.example.pokedexcompose.domain.repository.PokemonRepository
import com.pokedexcompose.network.dsl.request
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonDtotoDomain: PokemonDtotoDomain
) : PokemonRepository {
    private val mapper = MapperPokemonDomainImpl()
    override suspend fun getPokemonList(): PokemonListDomain {
        return mapper.map(pokemonDataSource.getPokemonList())
    }

    override suspend fun getPokemonDetail(pokemonId: Int): Flow<PokemonInfo> {
        return flowOf(
            pokemonDtotoDomain.map(
                pokemonClient.httpClient.request<PokemonDto, Any> {
                    url = "${pokemonClient.baseUrl}pokemon/$pokemonId"
                }
            )
        )
    }
}
