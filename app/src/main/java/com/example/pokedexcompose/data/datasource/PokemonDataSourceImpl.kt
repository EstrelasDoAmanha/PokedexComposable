package com.example.pokedexcompose.data.datasource

import com.example.pokedexcompose.data.mappers.PokemonListByFilterDtoToDomain
import com.example.pokedexcompose.data.model.ListPokemonTypesDto
import com.example.pokedexcompose.data.model.PokemonByTypesDto
import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.data.model.PokemonListDto
import com.pokedexcompose.network.client.KtorHttpClient
import com.pokedexcompose.network.dsl.request

class PokemonDataSourceImpl(
    private val pokemonClient: KtorHttpClient,
    private val listPokemonByFilterToDomain: PokemonListByFilterDtoToDomain
) : PokemonDataSource {

    override suspend fun getPokemonList(
        offset: String,
        limit: String,
        query: String
    ): PokemonListDto {
        return if (query.isBlank()) {
            pokemonClient.httpClient.request<Any, PokemonListDto> {
                url = "${pokemonClient.baseUrl}pokemon"
                parameters = listOf("limit" to "$limit", "offset" to "$offset")
            }
        } else {
            val response = pokemonClient.httpClient.request<Any, PokemonByTypesDto> {
                url = "${pokemonClient.baseUrl}type/$query"
            }
            PokemonListDto(result = listPokemonByFilterToDomain.map(response))
        }
    }

    override suspend fun typeList() = with(pokemonClient.httpClient) {
        request<Any, ListPokemonTypesDto> {
            url = "${pokemonClient.baseUrl}type"
            parameters = listOf("limit" to "99", "offset" to "0")
        }
    }

    override suspend fun getPokemonDetail(pokemonId: Int) =
        pokemonClient.httpClient.request<Any, PokemonDto> {
            url = "${pokemonClient.baseUrl}pokemon/$pokemonId"
        }
}
