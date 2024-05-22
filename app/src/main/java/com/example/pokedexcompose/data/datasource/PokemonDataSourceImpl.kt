package com.example.pokedexcompose.data.datasource

import com.example.pokedexcompose.data.model.ListPokemonByTypesDto
import com.example.pokedexcompose.data.model.ListPokemonTypesDto
import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.data.model.PokemonListDto
import com.pokedexcompose.network.client.KtorHttpClient
import com.pokedexcompose.network.dsl.request

class PokemonDataSourceImpl(
    private val pokemonClient: KtorHttpClient
) : PokemonDataSource {

    override suspend fun getPokemonList(offset: String, limit: String) = with(pokemonClient.httpClient) {
        request<Any, PokemonListDto> {
            url = "${pokemonClient.baseUrl}pokemon"
            parameters = listOf("limit" to "$limit", "offset" to "$offset")
        }
    }

    override suspend fun listPokemonType() = with(pokemonClient.httpClient) {
        request<Any, ListPokemonTypesDto> {
            url = "${pokemonClient.baseUrl}type"
            parameters = listOf("limit" to "99", "offset" to "0")
        }
    }

    override suspend fun listPokemonByFilter(type:String) = with(pokemonClient.httpClient) {
        request<Any, ListPokemonByTypesDto> {
            url = "${pokemonClient.baseUrl}type/${type}"
        }
    }

    override suspend fun getPokemonDetail(pokemonId: Int) =
        pokemonClient.httpClient.request<Any, PokemonDto> {
            url = "${pokemonClient.baseUrl}pokemon/$pokemonId"
        }
}
