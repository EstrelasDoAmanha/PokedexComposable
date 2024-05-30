package com.example.pokedexcompose.ui.list.di

import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.datasource.PokemonDataSourceImpl
import com.example.pokedexcompose.data.mappers.PokemonDtotoDomain
import com.example.pokedexcompose.data.mappers.PokemonStatsDtoToDomain
import com.example.pokedexcompose.data.mappers.PokemonTypeDtoToDomain
import com.example.pokedexcompose.data.repository.PokemonRepositoryImpl
import com.example.pokedexcompose.domain.mapper.MapperPokemonDomainImpl
import com.example.pokedexcompose.domain.repository.PokemonRepository
import com.example.pokedexcompose.domain.usecase.GetPokemonDetailsUseCase
import com.example.pokedexcompose.domain.usecase.PokemonUseCase
import com.example.pokedexcompose.domain.usecase.PokemonUseCaseImpl
import com.example.pokedexcompose.ui.details.presentation.PokemonDetailsViewModel
import com.example.pokedexcompose.ui.list.presentation.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModel = module {
    viewModelOf(::PokemonListViewModel)
    viewModelOf(::PokemonDetailsViewModel)
}

val domainModule = module {
    factoryOf(::GetPokemonDetailsUseCase)
    factory<PokemonUseCase> { PokemonUseCaseImpl(get()) }
}

val dataModule = module {
    factoryOf(::PokemonRepositoryImpl) { bind<PokemonRepository>() }
    factoryOf(::PokemonDtotoDomain)
    factoryOf(::PokemonStatsDtoToDomain)
    factoryOf(::PokemonTypeDtoToDomain)
    factory { MapperPokemonDomainImpl() }
    factoryOf(::PokemonDataSourceImpl) { bind<PokemonDataSource>() }
}
