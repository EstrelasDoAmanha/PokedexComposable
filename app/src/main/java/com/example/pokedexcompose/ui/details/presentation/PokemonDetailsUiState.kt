package com.example.pokedexcompose.ui.details.presentation

import com.example.pokedexcompose.domain.model.PokemonInfo

data class PokemonDetailsUiState(
    val isLoading: Boolean = false,
    val pokemonInfo: PokemonInfo = PokemonInfo()
)
