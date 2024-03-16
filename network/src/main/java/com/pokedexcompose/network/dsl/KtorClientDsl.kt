package com.pokedexcompose.network.dsl

import com.pokedexcompose.network.dsl.model.KtorClientDslModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.host
import io.ktor.client.request.parameter
import io.ktor.client.request.port
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.http.URLBuilder
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import java.lang.Exception

@DslMarker
annotation class KtorClientDsl

@KtorClientDsl
suspend inline fun <reified T, reified Body> HttpClient.request(
    setup: KtorClientDslModel<Body>.() -> Unit
): T {
    val configuration = KtorClientDslModel<Body>()
    configuration.setup()

    return when (configuration.method) {
        HttpMethod.Get -> getRequest<Body, T>(configuration)

        HttpMethod.Post -> postRequest<Body, T>(configuration)

        else -> getRequest<Body, T>(configuration)
    }
}

suspend inline fun <reified Body, reified T> HttpClient.postRequest(
    configuration: KtorClientDslModel<Body>
): T =
    this.post(configuration.url) {
        contentType(configuration.contentType)
        url { pathSegments(configuration.pathSegments) }
        parameters(configuration.parameters)
        headers(configuration.headers)
        if (configuration.host.isNotEmpty()) host = configuration.host
        if (configuration.port != -1) port = configuration.port
        configuration.body?.let { setBody(it) }
    }.body()

suspend inline fun <reified Body, reified T> HttpClient.getRequest(
    configuration: KtorClientDslModel<Body>
): T =
    this.get(configuration.url) {
        url { pathSegments(configuration.pathSegments) }
        parameters(configuration.parameters)
        headers(configuration.headers)
        if (configuration.host.isNotEmpty()) host = configuration.host
        if (configuration.port != -1) port = configuration.port
    }.body()

fun HttpRequestBuilder.parameters(
    parameters: List<Pair<String, String>>
): HttpRequestBuilder {
    parameters.forEach { this.parameter(it.first, it.second) }
    return this
}

fun HttpRequestBuilder.headers(
    headers: List<Pair<String, String>>
): HttpRequestBuilder {
    headers.forEach { this.header(it.first, it.second) }
    return this
}

fun URLBuilder.pathSegments(
    segments: List<Pair<String, String>>
): URLBuilder {
    segments.forEach { appendPathSegments(it.first, it.second) }
    return this
}