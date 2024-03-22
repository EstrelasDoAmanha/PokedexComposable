package com.example.pokedexcompose.ui.list.presentation

import com.example.pokedexcompose.data.model.PokemonDto

data class ListUiState(
    val loading:Boolean = false,
    val pokemonDto: PokemonDto? = null
)