package com.example.pokedexcompose.data.datasource

import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.data.model.PokemonListDto
import com.pokedexcompose.network.client.KtorHttpClient
import com.pokedexcompose.network.dsl.request

class PokemonDataSourceImpl(
    private val pokemonClient: KtorHttpClient
) : PokemonDataSource {
    override suspend fun getPokemonList() = pokemonClient.httpClient.request<Any, PokemonListDto> {
        url = "${pokemonClient.baseUrl}pokemon"
    }

    override suspend fun getPokemonDetail(pokemonId: Int) =
        pokemonClient.httpClient.request<Any, PokemonDto> {
            url = "${pokemonClient.baseUrl}pokemon/$pokemonId"
        }
}
