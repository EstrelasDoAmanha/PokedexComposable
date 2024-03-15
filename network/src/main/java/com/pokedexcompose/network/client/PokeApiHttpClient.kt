package com.pokedexcompose.network.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.host
import io.ktor.client.request.port
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PokeApiHttpClient : KtorHttpClient {
    override val baseUrl: String
        get() = "https://pokeapi.co/api/v2/"

    override val httpClient: HttpClient
        get() {
            return HttpClient(Android) {
                install(Logging) { level = LogLevel.ALL }
                install(DefaultRequest) {
                    url(baseUrl)
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                }
                install(ContentNegotiation) { json(Json) }
            }
        }

    fun bla () {
        httpClient.get {
            url {

            }
        }.body<>()
    }


    override val koinName: String
        get() = "pokeApiKtorHttpClient"
}
