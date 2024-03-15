package com.pokedexcompose.network.di

import com.pokedexcompose.network.client.KtorHttpClient
import com.pokedexcompose.network.client.PokeApiHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf<KtorHttpClient>(::PokeApiHttpClient)
}
