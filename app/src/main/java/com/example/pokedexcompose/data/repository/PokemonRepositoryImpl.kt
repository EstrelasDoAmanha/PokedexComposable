package com.example.pokedexcompose.data.repository

import com.example.pokedexcompose.data.mappers.PokemonDtotoDomain
import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.repository.PokemonRepository
import com.pokedexcompose.network.client.KtorHttpClient
import com.pokedexcompose.network.dsl.request
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PokemonRepositoryImpl(
    private val pokemonClient: KtorHttpClient,
    private val pokemonDtotoDomain: PokemonDtotoDomain
) : PokemonRepository {
    override suspend fun getPokemonDetail(pokemonId: Int): Flow<PokemonInfo> {
        return flowOf(
            pokemonDtotoDomain.map(
                pokemonClient.httpClient.request<PokemonDto, Any> {
                    url = "${pokemonClient.baseUrl}pokemon/$pokemonId"
                }
            )
        )
    }
}