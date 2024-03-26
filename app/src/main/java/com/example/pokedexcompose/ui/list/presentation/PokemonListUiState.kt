package com.example.pokedexcompose.ui.list.presentation

import com.example.pokedexcompose.domain.model.PokemonListDomain

data class PokemonListUiState(
    val loading: Boolean = false,
    val pokemonDomain: PokemonListDomain? = null
)
