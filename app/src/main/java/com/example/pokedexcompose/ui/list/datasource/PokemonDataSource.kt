package com.example.pokedexcompose.ui.list.datasource

import com.example.pokedexcompose.data.model.Pokemon

internal interface PokemonDataSource {
  suspend fun getList():List<Pokemon>
}
