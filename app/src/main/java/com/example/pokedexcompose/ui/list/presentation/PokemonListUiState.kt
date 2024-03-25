package com.example.pokedexcompose.ui.list.presentation

import com.example.pokedexcompose.data.model.PokemonListDomain

data class PokemonListUiState(
    val loading: Boolean = false,
    val pokemonDomain: PokemonListDomain? = null
)
