package com.pokedexcompose.network.client

import io.ktor.client.HttpClient

interface KtorHttpClient {
    val baseUrl: String
    val httpClient: HttpClient
    val koinName: String
}
