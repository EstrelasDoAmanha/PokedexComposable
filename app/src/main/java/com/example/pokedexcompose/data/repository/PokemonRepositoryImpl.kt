package com.example.pokedexcompose.data.repository

import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.mappers.PokemonDtotoDomain
import com.example.pokedexcompose.domain.mapper.MapperPokemonDomainImpl
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.PokemonListDomain
import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonDtotoDomain: PokemonDtotoDomain,
    private val pokemonListToDomain: MapperPokemonDomainImpl
) : PokemonRepository {

    override suspend fun getPokemonList(): PokemonListDomain {
        return pokemonListToDomain.map(pokemonDataSource.getPokemonList())
    }

    override suspend fun getPokemonDetail(pokemonId: Int): Flow<PokemonInfo> {
        return flow {
            try {
                emit(
                    pokemonDtotoDomain.map(
                        pokemonDataSource.getPokemonDetail(pokemonId)
                    )
                )
            } catch (e: Exception) {
                throw e
            }
        }
    }
}
