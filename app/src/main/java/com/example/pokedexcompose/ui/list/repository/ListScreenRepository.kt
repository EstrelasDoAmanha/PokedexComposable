package com.example.pokedexcompose.ui.list.repository

import com.example.pokedexcompose.data.model.PokemonDto

interface ListScreenRepository {
    suspend fun  getPokemon():PokemonDto
}
