package com.example.pokedexcompose.ui.list.datasource

import com.example.pokedexcompose.data.model.PokemonDto

internal interface PokemonDataSource {
  suspend fun getPokemon():PokemonDto
}
