package com.example.pokedexcompose.ui.list.usecase

import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.ui.list.repository.ListScreenRepository

class ListScreenUseCaseImpl(val repository: ListScreenRepository): ListScreenUseCase {
    override suspend fun getPokemon():PokemonDto = repository.getPokemon()

}
