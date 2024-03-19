package com.example.pokedexcompose.ui.list.datasource

import android.util.Log
import com.example.pokedexcompose.data.model.Pokemon
import com.pokedexcompose.network.client.KtorHttpClient
import com.pokedexcompose.network.dsl.request

internal class PokemonDataSourceImpl(
    private val ktorHttpClient: KtorHttpClient
) : PokemonDataSource {
    override suspend fun getList():List<Pokemon> {

        val result = ktorHttpClient.httpClient.request<List<Pokemon>, Exception> {
            url = "https://pokeapi.co/api/v2/pokemon"
        }

        Log.d("TAG", "getList: ${result}")

        return emptyList()
    }


}
