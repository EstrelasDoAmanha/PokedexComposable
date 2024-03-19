package com.example.pokedexcompose.ui.list.repository

import com.example.pokedexcompose.data.model.Pokemon

interface ListScreenRepository {
    suspend fun  getList():List<Pokemon>
}
