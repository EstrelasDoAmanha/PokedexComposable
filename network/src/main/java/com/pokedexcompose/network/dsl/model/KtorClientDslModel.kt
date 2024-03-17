package com.pokedexcompose.network.dsl.model

import io.ktor.http.ContentType
import io.ktor.http.HttpMethod

data class KtorClientDslModel<Body>(
    var method: HttpMethod = HttpMethod.Get,
    var host: String = String(),
    var port: Int = -1,
    var url: String = String(),
    var headers: List<Pair<String, String>> = listOf(),
    var parameters: List<Pair<String, String>> = listOf(),
    var pathSegments: List<Pair<String, String>> = listOf(),
    var body: Body? = null,
    var contentType: ContentType = ContentType.Application.Json
)
