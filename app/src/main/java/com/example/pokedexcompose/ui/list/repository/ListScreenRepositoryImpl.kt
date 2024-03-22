package com.example.pokedexcompose.ui.list.repository

import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.ui.list.datasource.PokemonDataSource

internal class ListScreenRepositoryImpl(
    val pokemonDataSource: PokemonDataSource
): ListScreenRepository {
    override suspend fun getPokemon():PokemonDto = pokemonDataSource.getPokemon()
}
