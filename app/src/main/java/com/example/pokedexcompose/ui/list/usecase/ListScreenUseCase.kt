package com.example.pokedexcompose.ui.list.usecase

import com.example.pokedexcompose.data.model.Pokemon

internal interface ListScreenUseCase {
   suspend fun getList():List<Pokemon>
}
