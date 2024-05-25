package com.example.pokedexcompose.ui.list.di

import androidx.room.Room
import com.example.pokedexcompose.data.database.PokemonDao
import com.example.pokedexcompose.data.database.PokemonDataBase
import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.datasource.PokemonDataSourceImpl
import com.example.pokedexcompose.data.mappers.PokemonDtoToDomain
import com.example.pokedexcompose.data.mappers.PokemonListByFilterDtoToDomain
import com.example.pokedexcompose.data.mappers.PokemonStatsDtoToDomain
import com.example.pokedexcompose.data.mappers.PokemonTypeDtoToDomain
import com.example.pokedexcompose.data.mappers.TypeListDtoToDomain
import com.example.pokedexcompose.data.repository.PokemonRepositoryImpl
import com.example.pokedexcompose.domain.mapper.PokemonListDtoToDomain
import com.example.pokedexcompose.domain.repository.PokemonRepository
import com.example.pokedexcompose.domain.usecase.GetPokemonDetailsUseCase
import com.example.pokedexcompose.domain.usecase.PokemonUseCase
import com.example.pokedexcompose.domain.usecase.PokemonUseCaseImpl
import com.example.pokedexcompose.ui.list.presentation.PokemonListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModel = module {
    viewModelOf(::PokemonListViewModel)
}

val domainModule = module {
    factoryOf(::GetPokemonDetailsUseCase)
    factory<PokemonUseCase> { PokemonUseCaseImpl(get()) }
}

val roomModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            PokemonDataBase::class.java,
            "pokemon.db"
        ).allowMainThreadQueries()
            .build()
    }

    single<PokemonDao> {
        val database = get<PokemonDataBase>()
        database.pokemonDao()
    }
}

val dataModule = module {
    factoryOf(::TypeListDtoToDomain)
    factoryOf(::PokemonListByFilterDtoToDomain)
    factoryOf(::PokemonRepositoryImpl) { bind<PokemonRepository>() }
    factoryOf(::PokemonDtoToDomain)
    factoryOf(::PokemonStatsDtoToDomain)
    factoryOf(::PokemonTypeDtoToDomain)
    factoryOf(::PokemonListDtoToDomain)
    factoryOf(::PokemonDataSourceImpl) { bind<PokemonDataSource>() }
}
