package com.example.pokedexcompose.ui.list.di

import com.example.pokedexcompose.data.repository.PokemonRepositoryImpl
import com.example.pokedexcompose.domain.repository.PokemonRepository
import com.example.pokedexcompose.domain.usecases.GetPokemonDetailsUseCase
import com.example.pokedexcompose.ui.list.ListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.example.pokedexcompose.data.mappers.PokemonDtotoDomain
import com.example.pokedexcompose.data.mappers.PokemonStatsDtoToDomain
import com.example.pokedexcompose.data.mappers.PokemonTypeDtoToDomain

val viewModel = module {
    viewModelOf(::ListScreenViewModel)
}

val domainModule = module {
    factoryOf(::GetPokemonDetailsUseCase)
}

val dataModule = module {
    factoryOf(::PokemonRepositoryImpl) { bind<PokemonRepository>() }
    factoryOf(::PokemonDtotoDomain)
    factoryOf(::PokemonStatsDtoToDomain)
    factoryOf(::PokemonTypeDtoToDomain)
}