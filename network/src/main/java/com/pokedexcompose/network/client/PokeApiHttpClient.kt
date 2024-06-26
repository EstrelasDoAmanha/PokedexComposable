package com.pokedexcompose.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class PokeApiHttpClient : KtorHttpClient {
    override val baseUrl: String
        get() = "https://pokeapi.co/api/v2/"

    @OptIn(ExperimentalSerializationApi::class)
    override val httpClient: HttpClient
        get() {
            return HttpClient(Android) {
                install(Logging) { level = LogLevel.ALL }
                install(DefaultRequest) {
                    url(baseUrl)
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                }
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            ignoreUnknownKeys = true
                            explicitNulls = true
                            coerceInputValues = false
                        }
                    )
                }
                install(HttpRequestRetry) {
                    retryOnServerErrors(maxRetries = 3)
                    retryIf { _, response ->
                        !response.status.isSuccess()
                    }
                    exponentialDelay()
                }
            }
        }

    override val koinName: String
        get() = "pokeApiKtorHttpClient"
}
