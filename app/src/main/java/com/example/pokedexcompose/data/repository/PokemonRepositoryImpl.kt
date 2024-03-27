package com.example.pokedexcompose.data.repository

import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.domain.mapper.MapperPokemonDomain
import com.example.pokedexcompose.domain.model.PokemonListDomain

internal class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource,
    private val mapper: MapperPokemonDomain
) : PokemonRepository {
    override suspend fun getPokemonList(): PokemonListDomain {
        return mapper.map(pokemonDataSource.getPokemonList())
    }
}
