package com.example.pokedexcompose.ui.list.usecase

import com.example.pokedexcompose.data.model.Pokemon
import com.example.pokedexcompose.ui.list.repository.ListScreenRepository

class ListScreenUseCaseImpl(val repository: ListScreenRepository): ListScreenUseCase {
    override suspend fun getList():List<Pokemon> = repository.getList()

}
