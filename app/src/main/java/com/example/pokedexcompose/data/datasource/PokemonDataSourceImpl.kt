package com.example.pokedexcompose.data.datasource

import com.example.pokedexcompose.data.model.PokemonListDto
import com.pokedexcompose.network.client.KtorHttpClient
import com.pokedexcompose.network.dsl.request

internal class PokemonDataSourceImpl(
    private val pokemonClient: KtorHttpClient
) : PokemonDataSource {
    override suspend fun getPokemonList(offset: String, limit: String): PokemonListDto {
        val result = pokemonClient.httpClient.request<Any, PokemonListDto> {
            url = "${pokemonClient.baseUrl}pokemon"
            parameters = listOf("limit" to "$limit", "offset" to "$offset")
        }

        return result
    }
}
