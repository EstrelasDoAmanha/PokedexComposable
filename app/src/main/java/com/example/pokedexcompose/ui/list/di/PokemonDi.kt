package com.example.pokedexcompose.ui.list.di

import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.datasource.PokemonDataSourceImpl
import com.example.pokedexcompose.data.mappers.PokemonDtotoDomain
import com.example.pokedexcompose.data.mappers.PokemonStatsDtoToDomain
import com.example.pokedexcompose.data.mappers.PokemonTypeDtoToDomain
import com.example.pokedexcompose.data.repository.PokemonRepositoryImpl
import com.example.pokedexcompose.domain.repository.PokemonRepository
import com.example.pokedexcompose.domain.usecase.PokemonUseCase
import com.example.pokedexcompose.domain.usecase.PokemonUseCaseImpl
import com.example.pokedexcompose.domain.usecases.GetPokemonDetailsUseCase
import com.example.pokedexcompose.ui.list.presentation.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModel = module {
    viewModelOf(::PokemonListViewModel)
}

internal val dataSource = module {
    factory<PokemonDataSource> { PokemonDataSourceImpl(get()) }
    factory<PokemonRepository> { PokemonRepositoryImpl(get()) }
    factory<PokemonUseCase> { PokemonUseCaseImpl(get()) }
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