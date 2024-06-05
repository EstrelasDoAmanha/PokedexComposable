package com.example.pokedexcompose.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.mappers.PokemonDtoToDomain
import com.example.pokedexcompose.data.mappers.TypeListDtoToDomain
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.model.TypeListDomain
import com.example.pokedexcompose.domain.pagging.PokemonListPagingSource
import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

var firstVisibleItemIndexState = intPreferencesKey("index_state")
var firstVisibleItemScrollOffset = intPreferencesKey("offset_state")

internal class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonDetailsToDomain: PokemonDtoToDomain,
    private val typeListToDomain: TypeListDtoToDomain,
    private val pokemonListPagingSource: PokemonListPagingSource,
    private val dataStore: DataStore<Preferences>
) : PokemonRepository {

    override suspend fun getPokemonList(query: String): Flow<PagingData<ResultListDomain>> {
        pokemonListPagingSource.query = query
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { pokemonListPagingSource }
        ).flow
    }

    override suspend fun getPokemonDetail(pokemonId: Int): Flow<PokemonInfo> {
        return flow {
            try {
                emit(
                    pokemonDetailsToDomain.map(
                        pokemonDataSource.getPokemonDetail(pokemonId)
                    )
                )
            } catch (e: Exception) {
                throw e
            }
        }
    }

    override suspend fun getTypeList(): TypeListDomain {
        val result = pokemonDataSource.typeList()
        return typeListToDomain.map(result)
    }

    override fun receiverPositionState() = dataStore.data.map { preference ->
        (preference[firstVisibleItemIndexState] ?: 0) to
            (preference[firstVisibleItemScrollOffset] ?: 0)
    }

    override suspend fun updatePositionState(position: Pair<Int, Int>) {
        dataStore.edit { dataStore ->
            dataStore[firstVisibleItemIndexState] = position.first
            dataStore[firstVisibleItemScrollOffset] = position.second
        }
    }
}
