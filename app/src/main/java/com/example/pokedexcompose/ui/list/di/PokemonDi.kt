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
import com.example.pokedexcompose.domain.usecase.PokemonListInteract
import com.example.pokedexcompose.domain.usecase.pokemonlist.GetListUseCase
import com.example.pokedexcompose.domain.usecase.pokemonlist.ListUseCaseImpl
import com.example.pokedexcompose.domain.usecase.storagestate.ReceiverStateCaseImpl
import com.example.pokedexcompose.domain.usecase.storagestate.ReceiverStateUseCase
import com.example.pokedexcompose.domain.usecase.storagestate.SaveStateCaseImpl
import com.example.pokedexcompose.domain.usecase.storagestate.SaveStateUseCase
import com.example.pokedexcompose.domain.usecase.typelist.GetTypesListUseCase
import com.example.pokedexcompose.domain.usecase.typelist.TypesListUseCaseImpl
import com.example.pokedexcompose.ui.details.presentation.PokemonDetailsViewModel
import com.example.pokedexcompose.ui.list.presentation.PokemonListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModel = module {
    viewModelOf(::PokemonListViewModel)
    viewModelOf(::PokemonDetailsViewModel)
}

val domainModule = module {
    factoryOf(::GetPokemonDetailsUseCase)
    factory<GetListUseCase> { ListUseCaseImpl(get()) }
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
        ).build()
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
    factory { Dispatchers }
    factoryOf(::SaveStateCaseImpl) { bind<SaveStateUseCase>() }
    factoryOf(::ReceiverStateCaseImpl) { bind<ReceiverStateUseCase>() }
    factoryOf(::PokemonListInteract)
    factoryOf(::ListUseCaseImpl) { bind<GetListUseCase>() }
    factoryOf(::TypesListUseCaseImpl) { bind<GetTypesListUseCase>() }
    factoryOf(::TypeListDtoToDomain)
    factoryOf(::PokemonListByFilterDtoToDomain)
    factoryOf(::PokemonDtoToDomain)
    factoryOf(::PokemonStatsDtoToDomain)
    factoryOf(::PokemonTypeDtoToDomain)
    factoryOf(::PokemonListDtoToDomain)
    factoryOf(::PokemonDataSourceImpl) { bind<PokemonDataSource>() }
    singleOf(::PokemonRepositoryImpl) { bind<PokemonRepository>() }
}
