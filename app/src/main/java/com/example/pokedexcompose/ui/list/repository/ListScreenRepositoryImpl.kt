package com.example.pokedexcompose.ui.list.repository

import com.example.pokedexcompose.data.model.Pokemon
import com.example.pokedexcompose.ui.list.datasource.PokemonDataSource

internal class ListScreenRepositoryImpl(
    val pokemonDataSource: PokemonDataSource
): ListScreenRepository {
    override suspend fun getList():List<Pokemon> = pokemonDataSource.getList()
}
