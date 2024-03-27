package com.example.pokedexcompose.ui.list.di

import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.datasource.PokemonDataSourceImpl
import com.example.pokedexcompose.data.repository.PokemonRepository
import com.example.pokedexcompose.data.repository.PokemonRepositoryImpl
import com.example.pokedexcompose.domain.mapper.Mapper
import com.example.pokedexcompose.domain.mapper.MapperPokemonDomainImpl
import com.example.pokedexcompose.domain.usecase.PokemonUseCase
import com.example.pokedexcompose.domain.usecase.PokemonUseCaseImpl
import com.example.pokedexcompose.ui.list.presentation.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModel = module {
    viewModelOf(::PokemonListViewModel)
}

internal val dataSource = module {
    factory<PokemonDataSource> { PokemonDataSourceImpl(get()) }
    factory<PokemonRepository> { PokemonRepositoryImpl(get()) }
    factory<PokemonUseCase> { PokemonUseCaseImpl(get()) }
}
