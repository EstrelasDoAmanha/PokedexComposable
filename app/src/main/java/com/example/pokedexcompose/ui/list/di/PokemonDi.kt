package com.example.pokedexcompose.ui.list.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.paging.Pager
import androidx.paging.PagingConfig
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
import com.example.pokedexcompose.domain.pagging.PokemonListPagingSource
import com.example.pokedexcompose.domain.repository.PokemonRepository
import com.example.pokedexcompose.domain.usecase.GetPokemonDetailsUseCase
import com.example.pokedexcompose.ui.details.presentation.PokemonDetailsViewModel
import com.example.pokedexcompose.ui.list.presentation.PokemonListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.pokedexcompose.domain.usecase.PokemonInteract
import com.example.pokedexcompose.domain.usecase.typelist.TypesListUseCase
import com.example.pokedexcompose.domain.usecase.typelist.TypesListUseCaseImpl
import com.example.pokedexcompose.domain.usecase.storagestate.StorageStateCaseImpl
import com.example.pokedexcompose.domain.usecase.storagestate.StorageStateUseCase
import com.example.pokedexcompose.domain.usecase.pokemonlist.ListUseCase
import com.example.pokedexcompose.domain.usecase.pokemonlist.ListUseCaseImpl
import kotlinx.coroutines.Dispatchers

val viewModel = module {
    viewModelOf(::PokemonListViewModel)
    viewModelOf(::PokemonDetailsViewModel)
}

val domainModule = module {
    factoryOf(::GetPokemonDetailsUseCase)
    factory<ListUseCase> { ListUseCaseImpl(get()) }
}

val paging = module {
    factoryOf(::PokemonListPagingSource)
    single {
        PagingConfig(pageSize = 20)
    }
    single {
        Pager(config = get(), pagingSourceFactory = { get<PokemonListPagingSource>() })
    }
}

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            PokemonDataBase::class.java,
            "pokemon.db"
        ).allowMainThreadQueries().build()
    }
    single<PokemonDao> {
        val database = get<PokemonDataBase>()
        database.pokemonDao()
    }
}

val storageModule = module {
    single {
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().preferencesDataStoreFile("pokemon_cache") }
        )
    }
}

val dataModule = module {
    factory{
        Dispatchers
    }
    factoryOf(::StorageStateCaseImpl){ bind<StorageStateUseCase>() }
    factoryOf(::PokemonInteract)
    factoryOf(::ListUseCaseImpl) { bind<ListUseCase>() }
    factoryOf(::TypesListUseCaseImpl) { bind<TypesListUseCase>() }
    factoryOf(::TypeListDtoToDomain)
    factoryOf(::PokemonListByFilterDtoToDomain)
    factoryOf(::PokemonDtoToDomain)
    factoryOf(::PokemonStatsDtoToDomain)
    factoryOf(::PokemonTypeDtoToDomain)
    factoryOf(::PokemonListDtoToDomain)
    factoryOf(::PokemonDataSourceImpl) { bind<PokemonDataSource>() }
    singleOf(::PokemonRepositoryImpl) { bind<PokemonRepository>() }
}
