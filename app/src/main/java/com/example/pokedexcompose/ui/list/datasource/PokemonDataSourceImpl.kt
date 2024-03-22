package com.example.pokedexcompose.ui.list.datasource

import android.util.Log
import com.example.pokedexcompose.data.model.PokemonDto
import com.pokedexcompose.network.client.KtorHttpClient
import com.pokedexcompose.network.dsl.request

internal class PokemonDataSourceImpl(
    private val pokemonClient: KtorHttpClient
) : PokemonDataSource {
    override suspend fun getPokemon() =
        pokemonClient.httpClient.request<PokemonDto, Any> {
        url = "${pokemonClient.baseUrl}pokemon"
    }

}
