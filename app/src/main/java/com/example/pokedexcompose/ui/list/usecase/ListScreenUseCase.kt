package com.example.pokedexcompose.ui.list.usecase

import com.example.pokedexcompose.data.model.PokemonDto

internal interface ListScreenUseCase {
   suspend fun getPokemon():PokemonDto
}
