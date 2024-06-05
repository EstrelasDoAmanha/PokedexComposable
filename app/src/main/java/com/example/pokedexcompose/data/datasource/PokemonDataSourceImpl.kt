package com.example.pokedexcompose.data.datasource

import com.example.pokedexcompose.data.model.PokemonByTypesDto
import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.data.model.PokemonListDto
import com.example.pokedexcompose.data.model.TypeListDto
import com.pokedexcompose.network.client.KtorHttpClient
import com.pokedexcompose.network.dsl.request

class PokemonDataSourceImpl(
    private val pokemonClient: KtorHttpClient
) : PokemonDataSource {

    override suspend fun getPokemonList(offset: String, limit: String) =
        pokemonClient.httpClient.request<Any, PokemonListDto> {
            url = "${pokemonClient.baseUrl}pokemon"
            parameters = listOf("limit" to "$limit", "offset" to "$offset")
        }

    override suspend fun getPokemonListWithFilter(query: String) =
        pokemonClient.httpClient.request<Any, PokemonByTypesDto> {
            url = "${pokemonClient.baseUrl}type/$query"
        }

    override suspend fun typeList() = with(pokemonClient.httpClient) {
        request<Any, TypeListDto> {
            url = "${pokemonClient.baseUrl}type"
            parameters = listOf("limit" to "99", "offset" to "0")
        }
    }

    override suspend fun getPokemonDetail(pokemonId: Int) =
        pokemonClient.httpClient.request<Any, PokemonDto> {
            url = "${pokemonClient.baseUrl}pokemon/$pokemonId"
        }
}
