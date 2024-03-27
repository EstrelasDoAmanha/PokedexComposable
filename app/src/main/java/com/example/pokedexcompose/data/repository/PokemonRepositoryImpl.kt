package com.example.pokedexcompose.data.repository

import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.model.PokemonListDto
import com.example.pokedexcompose.domain.mapper.Mapper
import com.example.pokedexcompose.domain.mapper.MapperPokemonDomainImpl
import com.example.pokedexcompose.domain.model.PokemonListDomain

internal class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource,
) : PokemonRepository {
    private val mapper = MapperPokemonDomainImpl()
    override suspend fun getPokemonList(): PokemonListDomain {
        return mapper.map(pokemonDataSource.getPokemonList())
    }
}
