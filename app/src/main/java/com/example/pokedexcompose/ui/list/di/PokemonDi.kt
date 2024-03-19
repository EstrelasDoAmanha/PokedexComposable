package com.example.pokedexcompose.ui.list.di

import com.example.pokedexcompose.ui.list.datasource.PokemonDataSource
import com.example.pokedexcompose.ui.list.datasource.PokemonDataSourceImpl
import com.example.pokedexcompose.ui.list.presentation.ListScreenViewModel
import com.example.pokedexcompose.ui.list.repository.ListScreenRepository
import com.example.pokedexcompose.ui.list.repository.ListScreenRepositoryImpl
import com.example.pokedexcompose.ui.list.usecase.ListScreenUseCase
import com.example.pokedexcompose.ui.list.usecase.ListScreenUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModel = module {
    viewModelOf(::ListScreenViewModel)
}

internal val dataSource = module {
    factory<PokemonDataSource> { PokemonDataSourceImpl(get()) }
    factory<ListScreenRepository> { ListScreenRepositoryImpl(get()) }
    factory<ListScreenUseCase> { ListScreenUseCaseImpl(get()) }
}